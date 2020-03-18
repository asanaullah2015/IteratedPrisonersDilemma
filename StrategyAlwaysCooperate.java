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
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};
   }  /* class StrategyAlwaysCooperate */
