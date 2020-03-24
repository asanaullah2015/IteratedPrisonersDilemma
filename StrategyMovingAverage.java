import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyMovingAverage extends Strategy {

    public Queue<int> initialHistory;
    public Queue<int> history;
    public int historyLength;
    public double historySum;
    /**
     * Encoding for a strategy.
     */
    // 0 = defect, 1 = cooperate

    public StrategyMovingAverage() {
	intialHistory = new Queue<int>;
	history = new Queue<int>;
        name = "Moving Average";
        historyLength = Parameters.historyBegin + Search.r.nextInt(Parameters.historyEnd-Parameters.historyBegin);
	int sum = 0;
	for (int i = 0; i<historyLength; i++){
		int th = 0;
		if (Parameters.nextDouble()<0.5){
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
	intialHistory = new Queue<int>;
	history = new Queue<int>;
        historyLength = Parameters.historyBegin + Search.r.nextInt(Parameters.historyEnd-Parameters.historyBegin);
	int sum = 0;
	for (int i = 0; i<historyLength; i++){
		int th = 0;
		if (Parameters.nextDouble()<0.5){
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
	children[cnum].intialHistory = new Queue<int>;
	children[cnum].history = new Queue<int>;
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

    public void crossover(int pnum2, StrategyProbability parent2, StrategyProbability [] children, int cnum1, int cnum2) {
        // Create children
        children[cnum1] = new StrategyProbability();
        children[cnum2] = new StrategyProbability();

        // Generate random numbers to determine how to crossover
        int randnum1 = Search.r.nextInt(2);
        int randnum2 = Search.r.nextInt(2);

        if (randnum1 == 0) { // Subtract parents to create children[cnum1]
            int subtract1from2 = Search.r.nextInt(2); // Subtraction is not commutative, randomly subtract one from the other
            if (subtract1from2 == 0) {
                if ((this.probabilityDefect - parent2.probabilityDefect) < 0.0) { // Keep difference above 0
                    children[cnum1].probabilityDefect = ((this.probabilityDefect - parent2.probabilityDefect) + 1.0); 
                }
                else {
                    children[cnum1].probabilityDefect = (this.probabilityDefect - parent2.probabilityDefect);
                }
            }
            else {
                if ((parent2.probabilityDefect - this.probabilityDefect) < 0.0) { // Keep difference above 0
                    children[cnum1].probabilityDefect = ((parent2.probabilityDefect - this.probabilityDefect) + 1.0); 
                }
                else {
                    children[cnum1].probabilityDefect = (parent2.probabilityDefect - this.probabilityDefect);
                }
            }
        }
        else { // Add parents to create child1
            if ((this.probabilityDefect + parent2.probabilityDefect) > 1.0) { // Keep sum below 1
                children[cnum1].probabilityDefect = ((this.probabilityDefect + parent2.probabilityDefect) - 1.0);
            }
            else {
                children[cnum1].probabilityDefect = (this.probabilityDefect + parent2.probabilityDefect);
            }
        }
        if (randnum2 == 0) { // Subtract parents to create children[cnum2]
            int subtract1from2 = Search.r.nextInt(2); // Subtraction is not commutative, randomly subtract one from the other
            if (subtract1from2 == 0) {
                if ((this.probabilityDefect - parent2.probabilityDefect) < 0.0) { // Keep difference above 0
                    children[cnum2].probabilityDefect = ((this.probabilityDefect - parent2.probabilityDefect) + 1.0); 
                }
                else {
                    children[cnum2].probabilityDefect = (this.probabilityDefect - parent2.probabilityDefect);
                }
            }
            else {
                if ((parent2.probabilityDefect - this.probabilityDefect) < 0.0) { // Keep difference above 0
                    children[cnum2].probabilityDefect = ((parent2.probabilityDefect - this.probabilityDefect) + 1.0); 
                }
                else {
                    children[cnum2].probabilityDefect = (parent2.probabilityDefect - this.probabilityDefect);
                }
            }
        }
        else { // Add parents to create children[cnum2]
            if ((parent2.probabilityDefect + this.probabilityDefect) > 1.0) { // Keep sum below 1
                children[cnum2].probabilityDefect = ((parent2.probabilityDefect + this.probabilityDefect) - 1.0);
            }
            else {
                children[cnum2].probabilityDefect = (parent2.probabilityDefect + this.probabilityDefect);
            }
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
