public class StrategyProbability extends Strategy {

    public double probabilityDefect = 0.5;

    /**
     * Encoding for a strategy.
     */
    // 0 = defect, 1 = cooperate

    public StrategyProbability() {
        name = "Probability Strategy";
    }

    public int nextMove() {
        if (Search.r.nextDouble() < probabilityDefect) return 0;
        return 1;
    }

    public void doMutation() {
        if (Search.r.nextDouble() < Parameters.mutationRate) {
            probabilityDefect = Math.random();
        }
    }
    public void copyToChild(Strategy child) {
        child = new StrategyProbability();
    }

    public void crossover(int pnum2, StrategyProbability parent2, StrategyProbability child1, StrategyProbability child2) {
        // Create children
        child1 = new StrategyProbability();
        child2 = new StrategyProbability();

        // Generate random numbers to determine how to crossover
        int randnum1 = Search.r.nextInt(2);
        int randnum2 = Search.r.nextInt(2);

        if (randnum1 == 0) { // Subtract parents to create child1
            int subtract1from2 = Search.r.nextInt(2); // Subtraction is not commutative, randomly subtract one from the other
            if (subtract1from2 == 0) {
                if ((this.probabilityDefect - parent2.probabilityDefect) < 0.0) { // Keep difference above 0
                    child1.probabilityDefect = ((this.probabilityDefect - parent2.probabilityDefect) + 1.0); 
                }
                else {
                    child1.probabilityDefect = (this.probabilityDefect - parent2.probabilityDefect);
                }
            }
            else {
                if ((parent2.probabilityDefect - this.probabilityDefect) < 0.0) { // Keep difference above 0
                    child1.probabilityDefect = ((parent2.probabilityDefect - this.probabilityDefect) + 1.0); 
                }
                else {
                    child1.probabilityDefect = (parent2.probabilityDefect - this.probabilityDefect);
                }
            }
        }
        else { // Add parents to create child1
            if ((this.probabilityDefect + parent2.probabilityDefect) > 1.0) { // Keep sum below 1
                child1.probabilityDefect = ((this.probabilityDefect + parent2.probabilityDefect) - 1.0);
            }
            else {
                child1.probabilityDefect = (this.probabilityDefect + parent2.probabilityDefect);
            }
        }
        if (randnum2 == 0) { // Subtract parents to create child2
            int subtract1from2 = Search.r.nextInt(2); // Subtraction is not commutative, randomly subtract one from the other
            if (subtract1from2 == 0) {
                if ((this.probabilityDefect - parent2.probabilityDefect) < 0.0) { // Keep difference above 0
                    child2.probabilityDefect = ((this.probabilityDefect - parent2.probabilityDefect) + 1.0); 
                }
                else {
                    child2.probabilityDefect = (this.probabilityDefect - parent2.probabilityDefect);
                }
            }
            else {
                if ((parent2.probabilityDefect - this.probabilityDefect) < 0.0) { // Keep difference above 0
                    child2.probabilityDefect = ((parent2.probabilityDefect - this.probabilityDefect) + 1.0); 
                }
                else {
                    child2.probabilityDefect = (parent2.probabilityDefect - this.probabilityDefect);
                }
            }
        }
        else { // Add parents to create child2
            if ((parent2.probabilityDefect + this.probabilityDefect) > 1.0) { // Keep sum below 1
                child2.probabilityDefect = ((parent2.probabilityDefect + this.probabilityDefect) - 1.0);
            }
            else {
                child2.probabilityDefect = (parent2.probabilityDefect + this.probabilityDefect);
            }
        }

    }
}
