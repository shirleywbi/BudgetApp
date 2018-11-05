package model;

import exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseCategoryName;
    private float expenseTotal;

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
    }

    // EFFECTS: constructs expense for subExpenses
    public Expense(String expenseCategoryName, float expenseTotal) {
        this.expenseCategoryName = expenseCategoryName;
        this.expenseTotal = expenseTotal;
    }

    // getters
    public float getExpenseAmount() {
        return expenseTotal;
    }

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public List<ExpenseItem> getExpenseItems() {
        return expenseItems;
    }

    // MODIFIES: this
    // EFFECTS: sets expense to given num
    public void setExpense(float num) {
        this.expenseTotal = num;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: Adds num to the total expense
    public void addExpenseAmount(float num) throws NegativeAmountException {
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
    public void sortToExpenseSubcategory(ExpenseItem e) throws NegativeAmountException {
        float cost = e.getExpenseItemCost();
        switch (e.getExpenseItemCategory()) {
            case "Food":
                food.addExpenseAmount(cost);
                break;
            case "Entertainment":
                entertainment.addExpenseAmount(cost);
                break;
            case "Health":
                health.addExpenseAmount(cost);
                break;
            case "Rent":
                rent.addExpenseAmount(cost);
                break;
            case "Transportation":
                transportation.addExpenseAmount(cost);
                break;
            default:
                other.addExpenseAmount(cost);
                break;
        }
    }
}
