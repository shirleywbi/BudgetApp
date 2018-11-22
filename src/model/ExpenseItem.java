package model;

import java.text.SimpleDateFormat;

public class ExpenseItem {
    private String name;
    private String category;
    private double cost;
    private SimpleDateFormat date;
    private Expense expense;

    //EFFECTS: construct expense item using name, expense category, cost, and purchase date
    public ExpenseItem(String name, String category, double cost) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.date = date;
        //TODO: SimpleDateFormat ft = new SimpleDateFormat("E MM/DD/YYYY");
    }

    //getters
    public String getExpenseItemName() {
        return name;
    }

    public String getExpenseItemCategory() {
        return category;
    }

    public double getExpenseItemCost() {
        return cost;
    }

    public SimpleDateFormat getExpenseItemDate() {
        return date;
    }
}

