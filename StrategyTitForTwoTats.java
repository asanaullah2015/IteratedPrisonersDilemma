/**
 * Class containing the tit-for-two-tats strategy.
 * @author	081028AW
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyTitForTwoTats extends Strategy
   {
  /**
   * Encoding for tit-for-tat strategy.
   */

   int numDefects;

  // 0 = defect, 1 = cooperate

   public StrategyTitForTwoTats()
      {
      name = "Tit for Two Tats";
      opponentLastMove = 1;
      numDefects = 0;
      }  /* StrategyTitForTwoTats */

   public int nextMove()
      {
      if (opponentLastMove == 0)  numDefects++;

      if (opponentLastMove == 1)
         {
         numDefects = 0;
         return 1;
         }
      else
         {
         if (opponentLastMove == 0 && numDefects < 2)
            return 1;
         else  
            {
            return 0;
            }
         }

      }  /* nextMove */
   
   public void doMutation(){};
   
   public void copytoChild(Strategy child){
	   child = new StrategyTitForTwoTats();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy child1, Strategy child2){
	   child1 = new StrategyTitForTwoTats();
	   child2 = new StrategyTitForTwoTats();
   }

   public void doPrintGenes(FileWriter output)throws java.io.IOException{
	   output.write("StrategyTitForTwoTats\n");
   }
   }  /* class StrategyTitForTwoTats */
