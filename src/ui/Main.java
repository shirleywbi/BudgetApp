package ui;

import model.WebPageReader;

import java.io.IOException;

public class Main {
    //EFFECTS: runs the program
    public static void main(String[] args) throws IOException {
        WebPageReader webPageReader = new WebPageReader();
        webPageReader.readWebPage("https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html");
        BudgetTracker budgetTracker = new BudgetTracker();
        budgetTracker.setupBudgetTracker();
        budgetTracker.runBudgetTracker();
    }
}
