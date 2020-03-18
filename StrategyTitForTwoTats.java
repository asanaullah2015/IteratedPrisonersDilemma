/**
 * Class containing the tit-for-two-tats strategy.
 * @author	081028AW
 */
public class StrategyTitForTwoTats extends Strategy
   {
  /**
   * Encoding for tit-for-tat strategy.
   */

   int numDefects;

  // 0 = defect, 1 = cooperate

   public StrategyTitForTwoTats()
      {
      name = "Tit for Two Tats";
      opponentLastMove = 1;
      numDefects = 0;
      }  /* StrategyTitForTwoTats */

   public int nextMove()
      {
      if (opponentLastMove == 0)  numDefects++;

      if (opponentLastMove == 1)
         {
         numDefects = 0;
         return 1;
         }
      else
         {
         if (opponentLastMove == 0 && numDefects < 2)
            return 1;
         else  
            {
            return 0;
            }
         }

      }  /* nextMove */
   
   public void doMutation(){};
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};

   }  /* class StrategyTitForTwoTats */
