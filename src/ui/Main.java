package ui;

import exceptions.NegativeAmountException;

import java.io.IOException;

public class Main {
    //EFFECTS: runs the program
    public static void main(String[] args) throws IOException, NegativeAmountException {
        BudgetTracker budgetTracker = new BudgetTracker();
        budgetTracker.runBudgetTracker();
    }
}
