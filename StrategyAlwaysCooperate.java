/**
 * Class containing the always-cooperate strategy.
 * @author	081028AW
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyAlwaysCooperate extends Strategy
   {
  /**
   * Encoding for a strategy.
   */

  // 0 = defect, 1 = cooperate

   public StrategyAlwaysCooperate()
      {
      name = "Always cooperate";
      }  /* StrategyAlwaysCooperate */

   public int nextMove()
      {
      return 1;
      }  /* nextMove */
   
   public void doMutation(){};

   public void copytoChild(Strategy[] children, int cnum){
	   children[cnum] = new StrategyAlwaysCooperate();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy [] children, int cnum1, int cnum2){
	   children[cnum1] = new StrategyAlwaysCooperate();
	   children[cnum2] = new StrategyAlwaysCooperate();
   }
   public void doPrintGenes(FileWriter output) throws java.io.IOException{
	   output.write("StrategyAlwaysCooperate\n");
   }

   }  /* class StrategyAlwaysCooperate */
