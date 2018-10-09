package model;

import model.expenses.*;

import java.util.ArrayList;

public class Balances {
    public static float income = 0;
    public static float expenses = 0;
    public static float balance = 0;

    public static Expense food = new Food("Food",0);
    public static Expense entertainment = new Entertainment("Entertainment",0);
    public static Expense health = new Health("Health",0);
    public static Expense transportation = new Transportation("Transportation",0);
    public static Expense rent = new Rent("Rent",0);
    public static Expense other = new Other("Other",0);
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

    // EFFECTS: returns the total subtotal of the income
    public float getIncome() {
        return income;
    }

    // EFFECTS: returns the total subtotal of the expenses
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
        switch (category) {
            case "Food":
                food.addSubExpense(cost);
                return food.getSubExpense();
            case "Entertainment":
                entertainment.addSubExpense(cost);
                return entertainment.getSubExpense();
            case "Health":
                health.addSubExpense(cost);
                return health.getSubExpense();
            case "Rent":
                rent.addSubExpense(cost);
                return rent.getSubExpense();
            case "Transportation":
                transportation.addSubExpense(cost);
                return transportation.getSubExpense();
        }
        other.addSubExpense(cost);
        return other.getSubExpense();
    }

}

