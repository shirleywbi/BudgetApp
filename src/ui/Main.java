package ui;

import java.io.IOException;

public class Main {
    //EFFECTS: runs the program
    public static void main(String[] args) throws IOException {
        BudgetTracker budgetTracker = new BudgetTracker();
        budgetTracker.setupBudgetTracker();
        budgetTracker.runBudgetTracker();
    }
}
