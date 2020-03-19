/**
 * Class containing the random strategy.
 * @author	081028AW
 */

public class StrategyRandom extends Strategy
   {
  /**
   * Encoding for a strategy.
   */
  // 0 = defect, 1 = cooperate
  
   public StrategyRandom()
      {
      name = "Random";
      }  /* StrategyRandom */

   public int nextMove()
      {
      if (Math.random() < 0.5)  return 1;
      return 0;
      }  /* nextMove */
   public void copytoChild(Strategy child){
	   child = new StrategyRandom();
   }
   public void crossover(int pnum2, Strategy parent2, Strategy child1, Strategy child2){
	   child1 = new StrategyRandom();
	   child2 = new StrategyRandom();
   }
   }  /* class StrategyRandom */
