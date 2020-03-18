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
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};
   }  /* class StrategyAlwaysDefect */
