/**
 * Class containing an type of Strategy.
 * @author	081028AW
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class Strategy extends Object
   {
  /**
   * Encoding for a strategy.
   */

   int opponentLastMove = 1;
   int myLastMove;
   String name;

  // 0 = defect, 1 = cooperate

   public Strategy()
      {
      }  /* Strategy */

   public int nextMove()
      {
      return 0;
      }  /* nextMove */


   public void saveOpponentMove(int move)  { opponentLastMove = move; }
   public int getOpponentLastMove()  { return opponentLastMove; }
   public void saveMyMove(int move)  { myLastMove = move; }
   public int getMyLastMove()  { return myLastMove; }
   public String getName()  { return name; }
   public void doMutation(){};
   
   public static int selectParent() {
	   double rWheel = 0;
	   int j = 0;
	   int k = 0;

	   switch (Parameters.selectType){

		   case 1:     // Proportional Selection
			   double randnum = Search.r.nextDouble();
			   for (j=0; j<Parameters.popSize; j++){
				   rWheel = rWheel + Search.proFitness[j];
				   if (randnum < rWheel) return(j);
			   }
			   break;

		   case 3:     // Random Selection
			   randnum = Search.r.nextDouble();
			   j = (int) (randnum * Parameters.popSize);
			   return(j);

		   case 2:     //  Tournament Selection
			int temp;
			int candidate[] = new int[Parameters.tournamentSize];
			for (int i = 0; i < Parameters.tournamentSize; ++i)
				candidate[i] = Search.r.nextInt(Parameters.popSize);
			for (int i = Parameters.tournamentSize-1; i > 0; i--) {
				for (j = 0; j < i; j++) {
					if (Search.proFitness[candidate[j]] > Search.proFitness[candidate[j + 1]]) {
						temp = candidate[j];
						candidate[j] = candidate[j + 1];
						candidate[j + 1] = temp;
					}
				}
			}
			for (int i = Parameters.tournamentSize; i > 0; i--)
				if (Search.r.nextDouble() < 0.6)
					return candidate[i];
			return candidate[0];

		   default:
			   System.out.println("ERROR - No selection method selected");
	   }
	   return(-1);
   }
   public static void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy[] children, int cnum1, int cnum2) {
	if (parent1.getClass().equals(parent2.getClass())){
		parent1.crossover(pnum2, parent2, children, cnum1, cnum2);
	}
	else {
		int x = Search.r.nextInt(2);
		int y = Search.r.nextInt(2);
		if (x == 0){
			parent1.copytoChild(children, cnum1);
		}
		else{
			parent2.copytoChild(children, cnum1);
		}
		if (y == 0){
			parent1.copytoChild(children, cnum2);
		}
		else {
			parent2.copytoChild(children, cnum2);
		}
	}
   }
   public void copytoChild(Strategy[] child, int cnum) {};
   public void crossover(int pnum2, Strategy parent2, Strategy[] children, int cnum1, int cnum2) {};
   public void doPrintGenes(FileWriter output)throws java.io.IOException{}
   }  /* class Strategy */
