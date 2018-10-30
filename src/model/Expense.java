package model;

import exceptions.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseCategory;
    private float expenseTotal;

    public static Expense food;
    public static Expense entertainment;
    public static Expense health;
    public static Expense transportation;
    public static Expense rent;
    public static Expense other;

    public static List<ExpenseItem> expenseItems = new ArrayList<>();

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
    public Expense(String expenseCategory, float expenseTotal) {
        this.expenseCategory = expenseCategory;
        this.expenseTotal = expenseTotal;
    }

    // EFFECTS: Returns the total expense
    public float getExpenseTotal() {
        return expenseTotal;
    }

    // EFFECTS: returns expense category name
    public String getExpenseCategory() {
        return expenseCategory;
    }

    // MODIFIES: this
    // EFFECTS: sets expense to given num
    public void setExpense(float num) {
        this.expenseTotal = num;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: Adds num to the total expense
    public void addExpense(float num) throws NegativeAmountException {
        if (num < 0) {
            throw new NegativeAmountException();
        }
        expenseTotal += num;
    }


    // MODIFIES: this
    // EFFECTS: sorts expense into expense category and adds it to the category expenseTotal
    public void sortSubExpense(ExpenseItem e) throws NegativeAmountException {
        float cost = e.getExpenseItemCost();
        switch (e.getExpenseItemCategory()) {
            case "Food":
                food.addExpense(cost);
                break;
            case "Entertainment":
                entertainment.addExpense(cost);
                break;
            case "Health":
                health.addExpense(cost);
                break;
            case "Rent":
                rent.addExpense(cost);
                break;
            case "Transportation":
                transportation.addExpense(cost);
                break;
            default:
                other.addExpense(cost);
                break;
        }
    }
}
