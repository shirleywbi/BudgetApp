package model;

import java.util.ArrayList;

public class Balances {
    public static float income = 0;
    public static float expenses = 0;
    public static float balance = 0;

    public static Food foodTotal = new Food();
    public static Transportation transportationTotal = new Transportation();
    public static Rent rentTotal = new Rent();
    public static Other otherTotal = new Other();
    public static Balances bal = new Balances();

    public ArrayList<String> nameList = new ArrayList<>();
    public ArrayList<Number> costList = new ArrayList<>();
    public ArrayList<String> categoryList = new ArrayList<>();

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
    // EFFECTS: adds name of expense, its cost, and its type into three separate lists
    public void addExpenseEntry(String name, float cost, String category) {
        nameList.add(name);
        costList.add(cost);
        categoryList.add(category);
    }

    // MODIFIES: this
    // EFFECTS: adds expense into its category: food, rent, transportation, other
    public float addSubExpense(String category, float cost) {
        if (category.equals("Food")) {
            foodTotal.addSubExpense(cost);
            return foodTotal.getSubExpense();
        } else if (category.equals("Rent")) {
            rentTotal.addSubExpense(cost);
            return rentTotal.getSubExpense();
        } else if (category.equals("Transportation")) {
            transportationTotal.addSubExpense(cost);
            return transportationTotal.getSubExpense();
        } else {
            otherTotal.addSubExpense(cost);
            return otherTotal.getSubExpense();
        }
    }
}
