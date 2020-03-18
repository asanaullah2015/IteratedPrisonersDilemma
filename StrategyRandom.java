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

   public void doMutation(){};
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};
   }  /* class StrategyRandom */
