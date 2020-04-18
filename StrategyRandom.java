/**
 * Class containing the random strategy.
 * @author	081028AW
 */

import java.io.*;
import java.util.*;
import java.text.*;
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

   public void copytoChild(Strategy [] child, int cnum){
	   child[cnum] = new StrategyRandom();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy [] children, int cnum1, int cnum2){
	   children[cnum1] = new StrategyRandom();
	   children[cnum2] = new StrategyRandom();
   } 
   public void doPrintGenes(FileWriter output)throws java.io.IOException{
	   output.write("StrategyRandom, probabilitDefect=0.5\n");
   }  /* class StrategyRandom */
   }
