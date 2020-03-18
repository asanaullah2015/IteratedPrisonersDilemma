/**
 * Class containing the tit-for-tat strategy.
 * @author	081028AW
 */
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
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};
   }  /* class StrategyTitForTat */
