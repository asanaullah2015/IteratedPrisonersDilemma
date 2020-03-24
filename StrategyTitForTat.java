/**
 * Class containing the tit-for-tat strategy.
 * @author	081028AW
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyTitForTat extends Strategy
   {
  /**
   * Encoding for tit-for-tat strategy.
   */

  // 0 = defect, 1 = cooperate

   public StrategyTitForTat()
      {
      name = "Tit for Tat";
      opponentLastMove = 1;
      }  /* StrategyTitForTat */

   public int nextMove()
      {
      return opponentLastMove;
      }  /* nextMove */

   public void doMutation(){};
      
   public void copytoChild(Strategy []children, int cnum){
	   children[cnum] = new StrategyTitForTat();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy [] children, int cnum1, int cnum2){
	   children[cnum1] = new StrategyTitForTat();
	   children[cnum2] = new StrategyTitForTat();
   }

   public void doPrintGenes(FileWriter output)throws java.io.IOException{
	   output.write("StrategyTitForTat\n");
   }
   }  /* class StrategyTitForTat */
