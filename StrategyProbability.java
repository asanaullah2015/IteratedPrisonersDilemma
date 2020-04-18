import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyProbability extends Strategy {

    public double probabilityDefect = 0.5;

    /**
     * Encoding for a strategy.
     */
    // 0 = defect, 1 = cooperate

    public StrategyProbability() {
        name = "Probability";
        probabilityDefect = Search.r.nextDouble();
    }

    public int nextMove() {
        if (Search.r.nextDouble() < probabilityDefect) return 0;
        return 1;
    }

    public void doMutation() {
        if (Search.r.nextDouble() < Parameters.mutationRate) {
            probabilityDefect = Search.r.nextDouble();
        }
    }
    public void copyToChild(StrategyProbability [] children, int cnum) {
        children[cnum] = new StrategyProbability();
        children[cnum].probabilityDefect = this.probabilityDefect;
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

    public void doPrintGenes(FileWriter output)throws java.io.IOException{
	    output.write("StrategyProbability\n");
	    output.write("Probability Defect = ");
	    Hwrite.right(probabilityDefect, 8, output);
	    output.write("\n");
    }
}
