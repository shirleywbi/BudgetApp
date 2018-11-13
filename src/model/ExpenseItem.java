package model;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class ExpenseItem {
    private String name;
    private String category;
    private float cost;
    private SimpleDateFormat date;
    private Expense expense;

    //construct expense item using name, expense category, cost, and purchase date
    public ExpenseItem(String name, String category, float cost) {
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

    public float getExpenseItemCost() {
        return cost;
    }

    public SimpleDateFormat getExpenseItemDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseItem that = (ExpenseItem) o;
        return Float.compare(that.cost, cost) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(date, that.date) &&
                Objects.equals(expense, that.expense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, cost, date, expense);
    }
}

