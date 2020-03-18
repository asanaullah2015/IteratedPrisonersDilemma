/**
 * Class containing an type of Strategy.
 * @author	081028AW
 */
public class Strategy extends Object
   {
  /**
   * Encoding for a strategy.
   */

   int opponentLastMove = 1;
   int myLastMove;
   String name;

  // 0 = defect, 1 = cooperate

   public Strategy()
      {
      }  /* Strategy */

   public int nextMove()
      {
      return 0;
      }  /* nextMove */


   public void saveOpponentMove(int move)  { opponentLastMove = move; }
   public int getOpponentLastMove()  { return opponentLastMove; }
   public void saveMyMove(int move)  { myLastMove = move; }
   public int getMyLastMove()  { return myLastMove; }
   public String getName()  { return name; }
   public void doMutation(){};
   public static int selectParent() { return 0;}
   public void mateParents(int pnum1, int pnum2, Strategy parent1, Strategy parent2, Strategy child1, Strategy child2) {};
   public void mateParents(int pnum, Strategy parent, Strategy Child) {};
   }  /* class Strategy */
