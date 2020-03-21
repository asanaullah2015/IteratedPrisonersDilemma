/**
 * Class containing the always-cooperate strategy.
 * @author	081028AW
 */
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

   public void copytoChild(Strategy child){
	   child = new StrategyAlwaysDefect();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy child1, Strategy child2){
	   child1 = new StrategyAlwaysDefect();
	   child2 = new StrategyAlwaysDefect();
   }
   }  /* class StrategyAlwaysDefect */
