package model;

import java.util.ArrayList;

public class Balances {
    private float income = 0;
    private float expenses = 0;
    private float balance = 0;

    public static Balances bal = new Balances();
    public ArrayList<String> nameList = new ArrayList<>();
    public ArrayList<Number> costList = new ArrayList<>();

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: adds num to income
    public float addIncome(float num) {
        income += num;
        return income;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: adds num to expenses
    public float addExpense(float num) {
        expenses += num;
        return expenses;
    }

    // EFFECTS: returns the total balance of the income
    public float getIncome() {
        return income;
    }

    // EFFECTS: returns the total balance of the expenses
    public float getExpenses() {
        return expenses;
    }

    // MODIFIES: this
    // EFFECTS: returns income - expenses
    public float getBalance() {
        balance = income - expenses;
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: adds name of expense and its cost into two separate lists
    public void addExpenseEntry(String name, float cost) {
        nameList.add(name);
        costList.add(cost);
    }
}
