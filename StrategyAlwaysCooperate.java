/**
 * Class containing the always-cooperate strategy.
 * @author	081028AW
 */
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

   public void copytoChild(Strategy child){
	   child = new StrategyAlwaysCooperate();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy child1, Strategy child2){
	   child1 = new StrategyAlwaysCooperate();
	   child2 = new StrategyAlwaysCooperate();
   }
   public void doPrintGenes(FileWriter output){
	   output.write("StrategyAlwaysCooperate\n");
   }

   }  /* class StrategyAlwaysCooperate */
