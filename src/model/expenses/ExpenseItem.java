package model.expenses;

import java.text.SimpleDateFormat;

public class ExpenseItem {
    private String name;
    private String category;
    private float cost;
    private SimpleDateFormat date;

    public ExpenseItem(String name, String category, float cost, SimpleDateFormat date){
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.date = date;
    }
}
