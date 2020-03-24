/******************************************************************************
*  A Teaching GA					  Developed by Hal Stringer & Annie Wu, UCF
*  Version 2, January 18, 2004
*******************************************************************************/

import java.io.*;
import java.util.*;
import java.text.*;

public class Search {

/*******************************************************************************
*                           INSTANCE VARIABLES                                 *
*******************************************************************************/

/*******************************************************************************
*                           STATIC VARIABLES                                   *
*******************************************************************************/

	public static Strategy[] member;
	public static Strategy[] testPop;
	public static Strategy[] child;
	public static double[] rawFitness;
	public static double[] sclFitness;
	public static double[] proFitness;

	//public static Strategy bestOfGenStrategy;
	public static int bestOfGenR;
	public static int bestOfGenG;
	//public static Strategy bestOfRunStrategy;
	public static int bestOfRunR;
	public static int bestOfRunG;
	//public static Strategy bestOverAllStrategy;
	public static int bestOverAllR;
	public static int bestOverAllG;
	public static Strategy[] bestStrategy; 	//0 = Gen, 1 = Run, 2 = OverAll

	public static double sumRawFitness;
	public static double sumRawFitness2;	// sum of squares of fitness
	public static double sumSclFitness;
	public static double sumProFitness;
	public static double defaultBest;
	public static double defaultWorst;

	public static double averageRawFitness;
	public static double stdevRawFitness;

	public static int G;
	public static int R;
	public static Random r = new Random();
	private static double randnum;

	private static int memberIndex[];
	private static double memberFitness[];
	private static int TmemberIndex;
	private static double TmemberFitness;

	private static double fitnessStats[][];  // 0=Avg, 1=Best
	private static int   composition[][][];

/*******************************************************************************
*                              CONSTRUCTORS                                    *
*******************************************************************************/


/*******************************************************************************
*                             MEMBER METHODS                                   *
*******************************************************************************/


/*******************************************************************************
*                             STATIC METHODS                                   *
*******************************************************************************/

	public static void main(String[] args) throws java.io.IOException{

		Calendar dateAndTime = Calendar.getInstance(); 
		Date startTime = dateAndTime.getTime();

	//  Read Parameter File
		System.out.println("\nParameter File Name is: " + args[0] + "\n");
		Parameters parmValues = new Parameters(args[0]);

	//  Write Parameters To Summary Output File
		String summaryFileName = Parameters.expID + "_summary.txt";
		FileWriter summaryOutput = new FileWriter(summaryFileName);
		parmValues.outputParameters(summaryOutput);
		FileWriter summaryComposition = new FileWriter("composition.txt");
		summaryComposition.write("   AC   AD    R  TFT TFTT    P MVAV\n");

	//	Set up Fitness Statistics matri1x
		fitnessStats = new double[2][Parameters.generations];
		for (int i=0; i<Parameters.generations; i++){
			fitnessStats[0][i] = 0;
			fitnessStats[1][i] = 0;
		}
	//	Set up Composition matrix
		composition = new int[7][Parameters.numRuns][Parameters.generations];
		for (int i = 0; i<7; i++){
			for(int j = 0; j<Parameters.numRuns; j++){
				for (int k=0; k<Parameters.generations; k++){
					composition[i][j][k] = 0;
				}
			}
		}

	//	Initialize testPop
		testPop = new Strategy[1000];
		for (int i=0; i<1000; i++){
			//--------------------------
			//TODO: Make actual random generation.
			int str = r.nextInt(7); //Ignoring ours for now for testing purposes
			switch(str){
				case 0:
					testPop[i] = new StrategyAlwaysCooperate();
					break;
				case 1:
					testPop[i] = new StrategyAlwaysDefect();
					break;
				case 2:
					testPop[i] = new StrategyRandom();
					break;
				case 3:
					testPop[i] = new StrategyTitForTat();
					break;
				case 4:
					testPop[i] = new StrategyTitForTwoTats();
					break;
				case 5:
					testPop[i] = new StrategyProbability();
					break;
				case 6:
					testPop[i] = new StrategyMovingAverage();
					break;
			}
		}

	//	Problem Specific Setup - For new new fitness function problems, create
	//	the appropriate class file (extending FitnessFunction.java) and add
	//	an else_if block below to instantiate the problem.
 
		if (!Parameters.problemType.equals("IPD")){
			System.out.println("Invalid Problem Type");
		}

	//	Initialize RNG, array sizes and other objects
		r.setSeed(Parameters.seed);
		memberIndex = new int[Parameters.popSize];
		memberFitness = new double[Parameters.popSize];
		member = new Strategy[Parameters.popSize];
		child = new Strategy[Parameters.popSize];

		if (Parameters.minORmax.equals("max")){
			defaultBest = 0;
			defaultWorst = 999999999999999999999.0;
		}
		else{
			defaultBest = 999999999999999999999.0;
			defaultWorst = 0;
		}

		bestStrategy = new Strategy[3];
		bestStrategy[0] = new Strategy();
		double bestOfGenStrategyrawFitness = defaultBest;
		bestStrategy[1] = new Strategy();
		double bestOfRunStrategyrawFitness = defaultBest;
		bestStrategy[2] = new Strategy();
		double bestOverAllStrategyrawFitness = defaultBest;

		//  Start program for multiple runs
		for (R = 1; R <= Parameters.numRuns; R++){

			bestOfRunStrategyrawFitness = defaultBest;
			System.out.println();

			//	Initialize First Generation
			for (int i=0; i<Parameters.popSize; i++){
				//--------------------------
				//TODO: Make actual random generation.
				int str = r.nextInt(7); //Ignoring ours for now for testing purposes
				switch(str){
					case 0:
						member[i] = new StrategyAlwaysCooperate();
						break;
					case 1:
						member[i] = new StrategyAlwaysDefect();
						break;
					case 2:
						member[i] = new StrategyRandom();
						break;
					case 3:
						member[i] = new StrategyTitForTat();
						break;
					case 4:
						member[i] = new StrategyTitForTwoTats();
						break;
					case 5:
						member[i] = new StrategyProbability();
						break;
					case 6:
						member[i] = new StrategyMovingAverage();
						break;
				}
				child[i] = new Strategy();
			}

			//	Begin Each Run
			for (G=0; G<Parameters.generations; G++){

				sumProFitness = 0;
				sumSclFitness = 0;
				sumRawFitness = 0;
				sumRawFitness2 = 0;
				bestOfGenStrategyrawFitness = defaultBest;

				rawFitness = new double[Parameters.popSize];
				sclFitness = new double[Parameters.popSize];
				proFitness = new double[Parameters.popSize];

				for (int i = 0; i<Parameters.popSize; i++){
					rawFitness[i] = 0;
					sclFitness[i] = 0;
					proFitness[i] = 0;
				}

				//	Test Fitness of Each Member
				int numSteps = Parameters.getNextSteps();
				for (int i = 0; i<Parameters.popSize; i++){
					for (int j = 0; j<1000; j++){
						IteratedPD ipd = new IteratedPD(member[i], testPop[j]);
						ipd.runSteps(numSteps);
						rawFitness[i] += ipd.player1Score()/numSteps;
					}
				}


				for (int i=0; i<Parameters.popSize; i++){
					
					sumRawFitness = sumRawFitness + rawFitness[i];
					sumRawFitness2 = sumRawFitness2 +
						rawFitness[i] * rawFitness[i];

					if (Parameters.minORmax.equals("max")){
						if (rawFitness[i] > bestOfGenStrategyrawFitness){
							bestOfGenStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 0);
							bestOfGenR = R;
							bestOfGenG = G;
						}
						if (rawFitness[i] > bestOfRunStrategyrawFitness){
							bestOfRunStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 1);
							bestOfRunR = R;
							bestOfRunG = G;
						}
						if (rawFitness[i] > bestOverAllStrategyrawFitness){
							bestOverAllStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 2);
							bestOverAllR = R;
							bestOverAllG = G;
						}
					}
					else {
						if (rawFitness[i] < bestOfGenStrategyrawFitness){
							bestOfGenStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 0);
							bestOfGenR = R;
							bestOfGenG = G;
						}
						if (rawFitness[i] < bestOfRunStrategyrawFitness){
							bestOfRunStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 1);
							bestOfRunR = R;
							bestOfRunG = G;
						}
						if (rawFitness[i] < bestOverAllStrategyrawFitness){
							bestOverAllStrategyrawFitness = rawFitness[i];
							member[i].copytoChild(bestStrategy, 2);
							bestOverAllR = R;
							bestOverAllG = G;
						}
					}
				}

				// Accumulate fitness statistics
				fitnessStats[0][G] += sumRawFitness / Parameters.popSize;
				fitnessStats[1][G] += bestOfGenStrategyrawFitness;

				averageRawFitness = sumRawFitness / Parameters.popSize;
				stdevRawFitness = Math.sqrt(
							Math.abs(sumRawFitness2 - 
							sumRawFitness*sumRawFitness/Parameters.popSize)
							/
							(Parameters.popSize-1)
							);

				//Accumulate composition statistics
				for (int i = 0; i<Parameters.popSize; i++){
					if (member[i] instanceof StrategyAlwaysCooperate){
						composition[0][R-1][G]++;
					}
					if (member[i] instanceof StrategyAlwaysDefect){
						composition[1][R-1][G]++;
					}
					if (member[i] instanceof StrategyRandom){
						composition[2][R-1][G]++;
					}
					if (member[i] instanceof StrategyTitForTat){
						composition[3][R-1][G]++;
					}
					if (member[i] instanceof StrategyTitForTwoTats){
						composition[4][R-1][G]++;
					}
					if (member[i] instanceof StrategyProbability){
						composition[5][R-1][G]++;
					}
					if (member[i] instanceof StrategyMovingAverage){
						composition[6][R-1][G]++;
					}
				}

				// Output generation statistics to screen
				System.out.println(R + "\t" + G +  "\t" + (int)bestOfGenStrategyrawFitness + "\t" + averageRawFitness + "\t" + stdevRawFitness);

				// Output generation statistics to summary file
				summaryOutput.write(" R ");
				Hwrite.right(R, 3, summaryOutput);
				summaryOutput.write(" G ");
				Hwrite.right(G, 3, summaryOutput);
				Hwrite.right((int)bestOfGenStrategyrawFitness, 7, summaryOutput);
				Hwrite.right(averageRawFitness, 11, 3, summaryOutput);
				Hwrite.right(stdevRawFitness, 11, 3, summaryOutput);
				summaryOutput.write("\n");
				
				//Output generation statistics to summary file
				for (int i =0; i<7; i++){
					Hwrite.right(composition[i][R-1][G], 5, summaryComposition);
				}
				summaryComposition.write("\n");

		// *********************************************************************
		// **************** SCALE FITNESS OF EACH MEMBER AND SUM ***************
		// *********************************************************************

				switch(Parameters.scaleType){

				case 0:     // No change to raw fitness
					for (int i=0; i<Parameters.popSize; i++){
						sclFitness[i] = rawFitness[i] + .000001;
						sumSclFitness += sclFitness[i];
					}
					break;

				case 1:     // Fitness not scaled.  Only inverted.
					for (int i=0; i<Parameters.popSize; i++){
						sclFitness[i] = 1/(rawFitness[i] + .000001);
						sumSclFitness += sclFitness[i];
					}
					break;

				case 2:     // Fitness scaled by Rank (Maximizing fitness)

					//  Copy genetic data to temp array
					for (int i=0; i<Parameters.popSize; i++){
						memberIndex[i] = i;
						memberFitness[i] = rawFitness[i];
					}
					//  Bubble Sort the array by floating point number
					for (int i=Parameters.popSize-1; i>0; i--){
						for (int j=0; j<i; j++){
							if (memberFitness[j] > memberFitness[j+1]){
								TmemberIndex = memberIndex[j];
								TmemberFitness = memberFitness[j];
								memberIndex[j] = memberIndex[j+1];
								memberFitness[j] = memberFitness[j+1];
								memberIndex[j+1] = TmemberIndex;
								memberFitness[j+1] = TmemberFitness;
							}
						}
					}
					//  Copy ordered array to scale fitness fields
					for (int i=0; i<Parameters.popSize; i++){
						sclFitness[memberIndex[i]] = i;
						sumSclFitness += sclFitness[memberIndex[i]];
					}

					break;

				case 3:     // Fitness scaled by Rank (minimizing fitness)

					//  Copy genetic data to temp array
					for (int i=0; i<Parameters.popSize; i++){
						memberIndex[i] = i;
						memberFitness[i] = rawFitness[i];
					}
					//  Bubble Sort the array by floating point number
					for (int i=1; i<Parameters.popSize; i++){
						for (int j=(Parameters.popSize - 1); j>=i; j--){
							if (memberFitness[j-i] < memberFitness[j]){
								TmemberIndex = memberIndex[j-1];
								TmemberFitness = memberFitness[j-1];
								memberIndex[j-1] = memberIndex[j];
								memberFitness[j-1] = memberFitness[j];
								memberIndex[j] = TmemberIndex;
								memberFitness[j] = TmemberFitness;
							}
						}
					}
					//  Copy array order to scale fitness fields
					for (int i=0; i<Parameters.popSize; i++){
						sclFitness[memberIndex[i]] = i;
						sumSclFitness += sclFitness[memberIndex[i]];
					}

					break;

				default:
					System.out.println("ERROR - No scaling method selected");
				}


		// *********************************************************************
		// ****** PROPORTIONALIZE SCALED FITNESS FOR EACH MEMBER AND SUM *******
		// *********************************************************************

				for (int i=0; i<Parameters.popSize; i++){
					proFitness[i] = sclFitness[i]/sumSclFitness;
					sumProFitness = sumProFitness + proFitness[i];
				}

		// *********************************************************************
		// ************ CROSSOVER AND CREATE NEXT GENERATION *******************
		// *********************************************************************

				int parent1 = -1;
				int parent2 = -1;

				//  Assumes always two offspring per mating
				for (int i=0; i<Parameters.popSize; i=i+2){

					//	Select Two Parents
					parent1 = Strategy.selectParent();
					parent2 = parent1;
					while (parent2 == parent1){
						parent2 = Strategy.selectParent();
					}

					//	Crossover Two Parents to Create Two Children
					randnum = r.nextDouble();
					if (randnum < Parameters.xoverRate){
						Strategy.mateParents(parent1, parent2, member[parent1], member[parent2], child, i, i+1);
					}
					else {
						member[parent1].copytoChild(child, i);
						member[parent2].copytoChild(child, i+1);
					}
				} // End Crossover

				//	Mutate Children
				for (int i=0; i<Parameters.popSize; i++){
					child[i].doMutation();
				}

				//	Swap Children with Last Generation
				for (int i=0; i<Parameters.popSize; i++){
					child[i].copytoChild(member, i);
				}

			} //  Repeat the above loop for each generation

			Hwrite.left(bestOfRunR, 4, summaryOutput);
			Hwrite.right(bestOfRunG, 4, summaryOutput);
			summaryOutput.write("\n");

			bestStrategy[1].doPrintGenes(summaryOutput);

			summaryOutput.write(R + "\t" + "B" + "\t"+ (int)bestOfRunStrategyrawFitness+"\n\n");
			System.out.println(R + "\t" + "B" + "\t"+ (int)bestOfRunStrategyrawFitness);

			summaryComposition.write("\n");
		} //End of a Run

		summaryOutput.write("\n");
		summaryOutput.write("B" + "\t"+ (int)bestOverAllStrategyrawFitness+"\n");

		bestStrategy[2].doPrintGenes(summaryOutput);
		summaryOutput.write("\n");

		//	Output Fitness Statistics matrix
		summaryOutput.write("Gen                 AvgFit              BestFit \n");
		for (int i=0; i<Parameters.generations; i++){
			Hwrite.left(i, 15, summaryOutput);
			Hwrite.left(fitnessStats[0][i]/Parameters.numRuns, 20, 2, summaryOutput);
			Hwrite.left(fitnessStats[1][i]/Parameters.numRuns, 20, 2, summaryOutput);
			summaryOutput.write("\n");
		}

		summaryOutput.write("\n");
		summaryOutput.close();
		summaryComposition.close();

		System.out.println();
		System.out.println("Start:  " + startTime);
		dateAndTime = Calendar.getInstance(); 
		Date endTime = dateAndTime.getTime();
		System.out.println("End  :  " + endTime);

	} // End of Main Class

}   // End of Search.Java ******************************************************
