
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyShehryarAhsanAlex extends Strategy {
	public Queue<Integer> history;
	public int historyLength;
	public double historySum;
        public StrategyShehryarAhsanAlex() {
	    history = new LinkedList<>();
	    historyLength = 4;
	    history.add(0);
	    history.add(0);
	    history.add(1);
	    history.add(1);
	    historySum = 2;
            name = "Shehryar, Ahsan, Alex";
        }

        public int nextMove() {
            if (historySum/(double)historyLength<0.5){
                return 0;
            }
            return 1;
        }

	public void saveOpponentMove(int move) {
		historySum -= history.remove();
		history.add(move);
		historySum += move;
		opponentLastMove = move;
	}
}
