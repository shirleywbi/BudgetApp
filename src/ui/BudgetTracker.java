package ui;

import exceptions.NegativeAmountException;
import exceptions.InvalidEntryException;
import model.Expense;
import model.ExpenseCategory;
import model.ExpenseItem;
import model.Income;
import reports.ReportPrinter;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    //TODO: create a condition for when to show Menu
    private boolean showMenu = true;

    public static Income income;
    public static Expense expense;

    private Scanner entry = new Scanner(System.in);
    private ExpenseCategory expenseCategory = new ExpenseCategory();
    private Operations op;
    private ReportPrinter report;

    // MODIFIES: this, balances, reports
    // EFFECTS: loads a file and starts menu
    public void runBudgetTracker() throws IOException {
        op = new Operations();
        report = new ReportPrinter();
        expense.addObservers(report);
        income.addObservers(report);
        expenseCategory.setupExpenseCategories();
        try {
            op.load("budgetinput.txt");
        } catch (NegativeAmountException e) {
            System.out.println("Please check your file. Negative values found.");
        }
        mainMenu();
    }

    public void setupBudgetTracker() {
        income = new Income();
        expense = new Expense();
    }

    //EFFECTS: prompts user to select one of 4 options:
    //       - input 1: requests for user's income and adds income to total income
    //       - input 2: requests for user's expense and add expense to total expense,
    //         stores item name, cost, expense category, and purchase date, and displays the information
    //       - input 3: shows report of total income, total expense, expense list of names and costs,
    //         and subtotal
    //       - input 4: saves and exits
    //       - other input: requests for a valid input of 1-4
    private void mainMenu() throws IOException {
        loop:
        while (showMenu) {
            displayMainMenuPrompt();
            String option = entry.nextLine();
            switch (option) {
                case "1":
                    try {
                        logIncome();
                    } catch (InvalidEntryException e) {
                        System.out.println("Invalid input. Please try again.");
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
    private void displayMainMenuPrompt() {
        System.out.printf("What would you like to do? %n" +
                "[1] Add income %n" +
                "[2] Add expense %n" +
                "[3] Show report %n" +
                "[4] Exit %n");
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and adds user inputted income to total income
    private void logIncome() throws NegativeAmountException {
        System.out.println("Enter your income:");
        try {
            float pendingIncome = Float.parseFloat(entry.nextLine());
            if (pendingIncome < 0) {
                throw new NegativeAmountException();
            } else {
                income.addIncome(pendingIncome);
                income.notifyObservers(report);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // MODIFIES: this, balances
    // EFFECTS: prompts and saves user input for expense category, name and cost, and displays it back to the user
    private void logExpense() throws InvalidEntryException {
        displayExpenseCategoriesMenu();
        String expCategoryInput = expenseCategory.getExpenseCategoryKeyToValue(entry.nextLine());
        System.out.println("Enter name of expense:");
        String expNameInput = entry.nextLine();
        System.out.println("Enter the cost:");
        try {
            float expCostInput = Float.parseFloat(entry.nextLine());
            if (expCostInput < 0) {
                throw new NegativeAmountException();
            }
            ExpenseItem newExpense = new ExpenseItem(expNameInput, expCategoryInput, expCostInput);
            expense.addExpenseItem(newExpense);
            expense.addExpenseAmount(expCostInput);
            expenseCategory.sortToExpenseCategory(newExpense);
            expense.notifyObservers(report);
//            System.out.printf("Item: " + expNameInput + "%nCategory: " + expCategoryInput + "%nCost: $ %.2f %n", expCostInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (NegativeAmountException e) {
            System.out.println("Invalid input. Please enter a positive number.");
        }
    }

    // EFFECTS: display expenses category menu as [#] <ExpenseCategory> in the order of:
    //          1 = Food, 2 = Entertainment, 3 = Health, 4 = Transportation, 5 = Rent, 6 = Other
    private void displayExpenseCategoriesMenu() throws InvalidEntryException {
        System.out.println("Select Category:");
        for (int i = 1; i <= expenseCategory.getExpenseCategories().size(); i++) {
            String iAsString = Integer.toString(i);
            System.out.println("[" + iAsString + "] " +
                    expenseCategory.getExpenseCategoryKeyToValue(iAsString));
        }
    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html
//save() and splitOnSpace() methods originally referenced from FileReaderWriter
//parsing float referenced from https://www.geeksforgeeks.org/check-if-a-given-string-is-a-valid-number-integer-or-floating-point-in-java/