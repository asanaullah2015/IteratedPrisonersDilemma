import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyMovingAverage extends Strategy {

    public Queue<Integer> initialHistory;
    public Queue<Integer> history;
    public int historyLength;
    public double historySum;
    /**
     * Encoding for a strategy.
     */
    // 0 = defect, 1 = cooperate

    public StrategyMovingAverage() {
	initialHistory = new LinkedList<>();
	history = new LinkedList<>();
        name = "Moving Average";
        historyLength = Parameters.historyBegin + Search.r.nextInt(Parameters.historyEnd-Parameters.historyBegin);
	int sum = 0;
	for (int i = 0; i<historyLength; i++){
		int th = 0;
		if (Search.r.nextDouble()<0.5){
			th = 1;
			sum++;
		}
		initialHistory.add(th);
		history.add(th);
	}
	historySum = sum;

    }

    public int nextMove() {
        if (historySum/(double)historyLength < 0.5) return 0;
        return 1;
    }

    public void doMutation() {
	initialHistory = new LinkedList<>();
	history = new LinkedList<>();
        historyLength = Parameters.historyBegin + Search.r.nextInt(Parameters.historyEnd-Parameters.historyBegin);
	int sum = 0;
	for (int i = 0; i<historyLength; i++){
		int th = 0;
		if (Search.r.nextDouble()<0.5){
			th = 1;
			sum++;
		}
		initialHistory.add(th);
		history.add(th);
	}
	historySum = sum;
    }
    public void copyToChild(StrategyMovingAverage [] children, int cnum) {
        children[cnum] = new StrategyMovingAverage();
	children[cnum].initialHistory = new LinkedList<>();
	children[cnum].history = new LinkedList<>();
	children[cnum].historyLength = historyLength;
	int sum = 0;
	for (int i = 0; i<historyLength; i++){
		int th = initialHistory.remove();
		sum += th;
		children[cnum].initialHistory.add(th);
		children[cnum].history.add(th);
	}
	children[cnum].historySum = sum;
    }

    public void crossover(int pnum2, StrategyMovingAverage parent2, StrategyMovingAverage [] children, int cnum1, int cnum2) {
	int l = Math.min(historyLength, parent2.historyLength);
	int crossoverPoint = Search.r.nextInt(l);

        // Create children
        children[cnum1] = new StrategyMovingAverage();
        children[cnum2] = new StrategyMovingAverage();

	children[cnum1].initialHistory = new LinkedList<>();
	children[cnum1].history = new LinkedList<>();
	children[cnum2].initialHistory = new LinkedList<>();
	children[cnum2].history = new LinkedList<>();

	children[cnum1].historySum = 0;
	for (int i = 0; i<crossoverPoint; i++){
		int th1 = initialHistory.remove();
		int th2 = parent2.initialHistory.remove();
		children[cnum1].initialHistory.add(th1);
		children[cnum1].history.add(th1);
		children[cnum1].historySum += th1;

		children[cnum2].initialHistory.add(th2);
		children[cnum2].history.add(th2);
		children[cnum2].historySum += th2;
	}
	for (int i = crossoverPoint; i < l; i++){
		int th2 = initialHistory.remove();
		int th1 = parent2.initialHistory.remove();
		children[cnum1].initialHistory.add(th1);
		children[cnum1].history.add(th1);
		children[cnum1].historySum += th1;

		children[cnum2].initialHistory.add(th2);
		children[cnum2].history.add(th2);
		children[cnum2].historySum += th2;
	}
	if (historyLength>parent2.historyLength){
		for (int i = l; i<historyLength; i++){
			int th2 = initialHistory.remove();
			children[cnum2].initialHistory.add(th2);
			children[cnum2].history.add(th2);
			children[cnum2].historySum += th2;
		}
		children[cnum2].historyLength = historyLength;
	}
	else {
		for (int i = l; i < parent2.historyLength; i++){
			int th1 = parent2.initialHistory.remove();
			children[cnum1].initialHistory.add(th1);
			children[cnum1].history.add(th1);
			children[cnum1].historySum += th1;
		}
		children[cnum1].historyLength = parent2.historyLength;
	}




    }

    public void saveOpponentMove(int move)  { 
	    int sum = 0; 
	    sum -= history.remove();
	    history.add(move);
	    sum += move;
	    historySum += sum;
	    opponentLastMove = move; 
    }

    public void doPrintGenes(FileWriter output)throws java.io.IOException{
	    output.write("StrategyMovingAverage\n");
	    output.write("History Length = ");
	    Hwrite.right(historyLength, 8, output);
	    output.write("\n");
	    output.write("Initial History = " + initialHistory + "\n");
    }
}
