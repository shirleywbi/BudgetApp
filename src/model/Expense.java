package model;

import exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseCategoryName;
    private double expenseTotal;
    private List<Expense> expenses = new ArrayList();

    public static Expense food;
    public static Expense entertainment;
    public static Expense health;
    public static Expense transportation;
    public static Expense rent;
    public static Expense other;

    private List<ExpenseItem> expenseItems = new ArrayList<>();

    // EFFECTS: constructs expense with 0 expenses and initializes expense categories:
    // food, entertainment, health, transportation, rent and other
    public Expense() {
        this.expenseTotal = 0;
        food = new Expense("Food", 0);
        entertainment = new Expense("Entertainment", 0);
        health = new Expense("Health", 0);
        transportation = new Expense("Transportation", 0);
        rent = new Expense("Rent", 0);
        other = new Expense("Other", 0);
        expenses.add(food);
        expenses.add(entertainment);
        expenses.add(health);
        expenses.add(transportation);
        expenses.add(rent);
        expenses.add(other);
    }

    // EFFECTS: constructs expense for subExpenses
    public Expense(String expenseCategoryName, double expenseTotal) {
        this.expenseCategoryName = expenseCategoryName;
        this.expenseTotal = expenseTotal;
    }

    // getters
    public double getExpenseTotal() {
        return this.expenseTotal;
    }
    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }
    public List<ExpenseItem> getExpenseItems() {
        return expenseItems;
    }

    // MODIFIES: this
    // EFFECTS: sets expense to given num
    public void setExpense(double num) {
        this.expenseTotal = num;
    }

    // MODIFIES: this
    // EFFECTS: Adds num to the total expense
    public void addExpenseAmount(double num) throws NegativeAmountException {
        if (num < 0) {
            throw new NegativeAmountException();
        }
        expenseTotal += num;
    }

    // MODIFIES: this
    // EFFECTS: adds expense item to list of expenses so far
    public void addExpenseItem(ExpenseItem e) {
        expenseItems.add(e);
    }

    // MODIFIES: this
    // EFFECTS: sorts expense into expense category and adds it to the category expenseTotal
    public void sortExpense(ExpenseItem e) throws NegativeAmountException {
        double cost = e.getExpenseItemCost();
        switch (e.getExpenseItemCategory()) {
            case "FOOD":
                food.addExpenseAmount(cost);
                break;
            case "ENTERTAINMENT":
                entertainment.addExpenseAmount(cost);
                break;
            case "HEALTH":
                health.addExpenseAmount(cost);
                break;
            case "RENT":
                rent.addExpenseAmount(cost);
                break;
            case "TRANSPORTATION":
                transportation.addExpenseAmount(cost);
                break;
            default:
                other.addExpenseAmount(cost);
                break;
        }
    }

    // EFFECTS: prints list of expenses
    public void printExpenseList() {
        System.out.println("EXPENSE LIST:");
        for (ExpenseItem expenseItem : expenseItems) {
            System.out.printf(expenseItem.getExpenseItemCategory() + " " + expenseItem.getExpenseItemName() + " "
                    + decimalFormat(expenseItem.getExpenseItemCost()) + "%n");
        }
    }

    // EFFECTS: calculates and prints percentage of money spent in each expense category
    public void printExpensePercentage() {
        System.out.println("PERCENT BREAKDOWN:");
        double allExpenseTotal = this.expenseTotal;
        for (Expense expense : expenses) {
            double percent = expense.getExpenseTotal() / allExpenseTotal * 100;
            if (allExpenseTotal == 0) {
                percent = 0;
            }
            System.out.printf(expense.getExpenseCategoryName() + ": "
                    + decimalFormat(percent) + "%% %n");
        }
    }

    // EFFECTS: prints expense breakdown for each expense type
    public void printExpenseBreakdown() {
        System.out.println("EXPENSE CATEGORIES:");
        for (Expense expense : expenses) {
            String report = expense.getExpenseCategoryName() + ": $" + decimalFormat(expense.getExpenseTotal());
            System.out.println(report);
        }
    }

    // EFFECTS: converts double to string in currency format X.XX
    private String decimalFormat(double num) {
        return String.format("%.2f", num);
    }
}
