package ui;

import model.Balances;
import model.Expenses;
import model.Reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    private Balances balances = Balances.bal;
    private Operations op = new Operations();
    private Scanner entry = new Scanner(System.in);

    // REQUIRES: initial prompt entry between 1-4 (inclusive) and monetary values as numbers
    // MODIFIES: this, balances, reports
    // EFFECTS: loads a file then prompts user to select one of 4 options:
    //              - if 1 is entered, requests for user's income and adds income to total income
    //              - if 2 is entered, requests for user's expense and add expense to total expense,
    //                stores and displays expense name and cost
    //              - if 3 is entered, show report of total income, total expense, expense list of names and costs,
    //                and balance
    //              - if 4 is entered, save and exit
    public BudgetTracker() throws IOException {
        System.out.println("Loading...");
        op.load("budgetinput.txt");
        System.out.println("Loaded.");
        Reports report = Reports.report;
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
                report.runReports();
            } else if (option.equals("4")) {
                System.out.println("Saving...");
                op.save("budgetoutput.txt");
                System.out.println("Saved.");
                break;
            }
        }
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and adds user inputted income to total income
    private void logIncome() {
        System.out.println("Enter your income:");
        balances.addIncome(Float.valueOf(entry.nextLine()));
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and saves user input for expense category, name and cost, and displays it back to the user
    private void logExpense() {
        String expenseCategory = categorizeExpense();
        System.out.println("Enter name of expense:");
        String expenseNameEntry = entry.nextLine();
        System.out.println("Enter the cost:");
        float expenseCostEntry = Float.valueOf(entry.nextLine());
        balances.addExpense(expenseCostEntry);
        balances.addExpenseEntry(expenseNameEntry, expenseCostEntry, expenseCategory);
        balances.addSubExpense(expenseCategory, expenseCostEntry);
        System.out.printf("Item: " + expenseNameEntry + "%nCategory: " + expenseCategory + "%nCost: $ %.2f %n", expenseCostEntry);
    }

    // MODIFIES: this
    // EFFECTS: converts inputted numbers 1-4 to expense categories,
    //          where 1 = Food, 2 = Rent, 3 = Transportation, 4 = Other
    //          if input is other than 1-4, the expense is categorized as "Other"
    private String categorizeExpense() {
        System.out.printf("Select Category: %n" +
                "[1] Food %n" +
                "[2] Rent %n" +
                "[3] Transportation %n" +
                "[4] Other %n");
        String category = entry.nextLine();
        if (category.equals("1")) {
            return "Food";
        } else if (category.equals("2")) {
            return "Rent";
        } else if (category.equals("3")) {
            return "Transportation";
        } else {
            return "Other";
        }
    }


    //EFFECTS: runs the program
    public static void main(String[] args) throws IOException {
        new BudgetTracker();

    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html
//save() and splitOnSpace() methods originally referenced from FileReaderWriter