package model;

import java.util.Scanner;

public class BudgetTracker {
    private Balances balances = Balances.bal;
    private Scanner entry = new Scanner(System.in);

    // REQUIRES: initial prompt entry between 1-4 (inclusive) and monetary values as numbers
    // MODIFIES: this, balances, reports
    // EFFECTS: prompts user to select one of 4 options:
    //              - if 1 is entered, requests for user's income and adds income to total income
    //              - if 2 is entered, requests for user's expense and add expense to total expense,
    //                stores and displays expense name and cost
    //              - if 3 is entered, show report of total income, total expense, expense list of names and costs,
    //                and balance
    //              - if 4 is entered, exit
    public BudgetTracker() {
        Reports reports = Reports.reports;
        while (true) {
            System.out.printf("What would you like to do? %n" +
                    "[1] Add income %n" +
                    "[2] Add expense %n" +
                    "[3] Show report %n" +
                    "[4] Exit %n");
            String option = entry.nextLine();
            if (option.equals("1")) {
                logIncome();
            } else if (option.equals("2")) {
                logExpense();
            } else if (option.equals("3")) {
                reports.getSummaryReport();
            } else if (option.equals("4")) {
                break;
            }
        }
    }

    // REQUIRES: Scanner entry == "1"
    // MODIFIES: this, balances
    // EFFECTS: prompts and adds user inputted income to total income
    private void logIncome() {
        System.out.println("Enter your income:");
        balances.addIncome(Float.valueOf(entry.nextLine()));
    }

    // REQUIRES: Scanner entry == "2"
    // MODIFIES: this, balances
    // EFFECTS: prompts and saves user input for expense name and cost, and displays it back to the user
    private void logExpense() {
        System.out.println("Enter name of expense:");
        String expenseNameEntry = entry.nextLine();
        System.out.println("Enter the cost:");
        float expenseCostEntry = Float.valueOf(entry.nextLine());
        balances.addExpense(expenseCostEntry);
        balances.addExpenseEntry(expenseNameEntry, expenseCostEntry);
        System.out.printf("Item: " + expenseNameEntry + "%nCost: $ %.2f %n",expenseCostEntry);
    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html