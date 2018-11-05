package ui;

import exceptions.NegativeAmountException;
import exceptions.InvalidEntryException;
import model.Expense;
import model.ExpenseItem;
import model.Income;
import reports.ReportPrinter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {
    //TODO: create a condition for when to show Menu
    //private boolean showMenu = true;
    public static Income income;
    public static Expense expense;
    public Scanner entry = new Scanner(System.in);
    private Map<String, String> expenseCategories = new HashMap<>();

    public void setupBudgetTracker() {
        income = new Income();
        expense = new Expense();
    }

    // MODIFIES: this, balances, reports
    // EFFECTS: loads a file then prompts user to select one of 4 options:
    //              - input 1: requests for user's income and adds income to total income
    //              - input 2: requests for user's expense and add expense to total expense,
    //                stores item name, cost, expense category, and purchase date, and displays the information
    //              - input 3: shows report of total income, total expense, expense list of names and costs,
    //                and subtotal
    //              - input 4: saves and exits
    //              - other input: requests for a valid input of 1-4
    public void runBudgetTracker() throws IOException {
        Operations op = new Operations();
        ReportPrinter report = new ReportPrinter();
        setupExpenseCategories();
        try {
            op.load("budgetinput.txt");
        } catch (NegativeAmountException e) {
            System.out.println("File contains negative expenses.");
        }
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
                        System.out.println("Current Income: " + income.getIncomeTotal());
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
            if (expenseCostEntry < 0) {
                throw new NegativeAmountException();
            }
            ExpenseItem newExpense = new ExpenseItem(expenseNameEntry, expenseCategory,expenseCostEntry);
            expense.addExpenseItem(newExpense);
            expense.addExpenseAmount(expenseCostEntry);
            expense.sortToExpenseSubcategory(newExpense);
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
    public String categorizeExpense() throws InvalidEntryException {
        System.out.printf("Select Category: %n" +
                "[1] " + expenseCategories.get("1") + "%n" +
                "[2] " + expenseCategories.get("2") + "%n" +
                "[3] " + expenseCategories.get("3") + "%n" +
                "[4] " + expenseCategories.get("4") + "%n" +
                "[5] " + expenseCategories.get("5") + "%n" +
                "[6] " + expenseCategories.get("6") + "%n");
        String selection = entry.nextLine();
            String category = expenseCategories.get(selection);
            if (category != null) {
                return category;
            }
            throw new InvalidEntryException();
    }

    // EFFECTS: sets up expense categories:
    //          where 1 = Food, 2 = Entertainment, 3 = Health, 4 = Transportation, 5 = Rent, 6 = Other
    private void setupExpenseCategories() {
        expenseCategories.put("1","Food");
        expenseCategories.put("2","Entertainment");
        expenseCategories.put("3","Health");
        expenseCategories.put("4","Transportation");
        expenseCategories.put("5","Rent");
        expenseCategories.put("6","Other");
    }
}


//overall format of BudgetTracker referenced from LoggingCalculator
//converting numbers to string referenced from https://docs.oracle.com/javase/tutorial/java/data/converting.html
//save() and splitOnSpace() methods originally referenced from FileReaderWriter
//parsing float referenced from https://www.geeksforgeeks.org/check-if-a-given-string-is-a-valid-number-integer-or-floating-point-in-java/