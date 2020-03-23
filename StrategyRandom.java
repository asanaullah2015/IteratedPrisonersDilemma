/**
 * Class containing the random strategy.
 * @author	081028AW
 */

import java.io.*;

public class StrategyRandom extends Strategy
   {
  /**
   * Encoding for a strategy.
   */
  // 0 = defect, 1 = cooperate
  
   public static double probabilityDefect = 0.5;

   public StrategyRandom()
      {
      name = "Random";
      }  /* StrategyRandom */

   public int nextMove()
      {
      if (Math.random() < probabilityDefect)  return 0;
      return 1;
      }  /* nextMove */

   public void doMutation(){};

   public void copytoChild(Strategy child){
	   child = new StrategyRandom();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy child1, Strategy child2){
	   child1 = new StrategyRandom();
	   child2 = new StrategyRandom();
   } 
   public void doPrintGenes(FileWriter output){
	   try {
         output.write("StrategyRandom, probabilityDefect=0.5\n");
      }
      catch (IOException e) {
      }
   }  /* class StrategyRandom */
   }