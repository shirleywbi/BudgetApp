package ui;

import exceptions.NegativeAmountException;
import exceptions.InvalidEntryException;
import model.Income;
import model.expenses.*;
import reports.ReportPrinter;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    //TODO: create a condition for when to show Menu
    //private boolean showMenu = true;
    public Scanner entry = new Scanner(System.in);
    public static Expense expense = new Expense();
    public static Income income = new Income();

    // REQUIRES: initial prompt entry between 1-4 (inclusive) and monetary values as numbers
    // MODIFIES: this, balances, reports
    // EFFECTS: loads a file then prompts user to select one of 4 options:
    //              - if 1 is entered, requests for user's income and adds income to total income
    //              - if 2 is entered, requests for user's expense and add expense to total expense,
    //                stores and displays expense name and cost
    //              - if 3 is entered, show report of total income, total expense, expense list of names and costs,
    //                and subtotal
    //              - if 4 is entered, save and exit
    public void runBudgetTracker() throws IOException, NegativeAmountException {
        Operations op = new Operations();
        ReportPrinter report = new ReportPrinter();
        op.load("budgetinput.txt");
        loop:
        while (true) {
            mainMenuPrompt();
            String option = entry.nextLine();
            switch (option) {
                case "1":
                    try {
                        logIncome();
                    } catch (InvalidEntryException e) {
                        System.out.println("Invalid input. Please try again.");
                    } finally {
                        System.out.println("Current Income: " + income.getIncome());
                    }
                    break;
                case "2":
                    try {
                        logExpense();
                    } catch (InvalidEntryException e) {
                        System.out.println("Invalid input. Please select a number from 1 to 6.");
                    }
                    break;
                case "3":
                        report.runReports();
                    break;
                case "4":
                    op.save("budgetoutput.txt");
                    break loop;
            }
        }
    }

    //EFFECTS: prints menu of options - [1] add income, [2] add expense, [3] show report, [4] save and exit
    private void mainMenuPrompt() {
        System.out.printf("What would you like to do? %n" +
                "[1] Add income %n" +
                "[2] Add expense %n" +
                "[3] Show report %n" +
                "[4] Exit %n");
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and adds user inputted income to total income
    public void logIncome() throws NegativeAmountException {
        System.out.println("Enter your income:");
        try {
            float pendingIncome = Float.parseFloat(entry.nextLine());
            if (pendingIncome < 0) {
                throw new NegativeAmountException();
            } else {
                income.addIncome(pendingIncome);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and saves user input for expense category, name and cost, and displays it back to the user
    private void logExpense() throws InvalidEntryException {
        String expenseCategory = categorizeExpense();
        System.out.println("Enter name of expense:");
        String expenseNameEntry = entry.nextLine();
        System.out.println("Enter the cost:");
        try {
            float expenseCostEntry = Float.parseFloat(entry.nextLine());
            expense.addExpense(expenseCostEntry);
            expense.addExpenseItem(expenseNameEntry, expenseCostEntry, expenseCategory);
            expense.sortSubExpense(expenseCategory, expenseCostEntry);
            System.out.printf("Item: " + expenseNameEntry + "%nCategory: " + expenseCategory + "%nCost: $ %.2f %n", expenseCostEntry);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (NegativeAmountException e) {
            System.out.println("Invalid input. Please enter a positive number.");
        }
    }

    // MODIFIES: this
    // EFFECTS: converts inputted numbers 1-6 to expense categories,
    //          where 1 = Food, 2 = Entertainment, 3 = Health, 4 = Transportation, 5 = Rent, 6 = Other
    //          if input is other than 1-6, the expense is categorized as "Other"
    public String categorizeExpense() throws NumberFormatException, InvalidEntryException {
        System.out.printf("Select Category: %n" +
                "[1] Food %n" +
                "[2] Entertainment %n" +
                "[3] Health %n" +
                "[4] Transportation %n" +
                "[5] Rent %n" +
                "[6] Other %n");
        String category = entry.nextLine();
        switch (category) {
            case "1":
                return "Food";
            case "2":
                return "Entertainment";
            case "3":
                return "Health";
            case "4":
                return "Transportation";
            case "5":
                return "Rent";
            case "6":
                return "Other";
            default:
                throw new InvalidEntryException();
        }
    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html
//save() and splitOnSpace() methods originally referenced from FileReaderWriter
//parsing float referenced from https://www.geeksforgeeks.org/check-if-a-given-string-is-a-valid-number-integer-or-floating-point-in-java/