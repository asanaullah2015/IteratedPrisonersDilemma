/**
 * Class containing the always-cooperate strategy.
 * @author	081028AW
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyAlwaysDefect extends Strategy
   {
  /**
   * Encoding for a strategy.
   */

  // 0 = defect, 1 = cooperate

   public StrategyAlwaysDefect()
      {
      name = "Always defect";
      }  /* StrategyAlwaysDefect */

   public int nextMove()
      {
      return 0;
      }  /* nextMove */
      
   public void doMutation(){};

   public void copytoChild(Strategy [] children, int cnum){
	   children[cnum] = new StrategyAlwaysDefect();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy[] children, int cnum1, int cnum2){
	   children[cnum1] = new StrategyAlwaysDefect();
	   children[cnum2] = new StrategyAlwaysDefect();
   }
   public void doPrintGenes(FileWriter output)throws java.io.IOException{
	   output.write("StrategyAlwaysDefect");
   }
   }  /* class StrategyAlwaysDefect */
