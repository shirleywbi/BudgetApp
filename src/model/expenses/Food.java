package model.expenses;

public class Food extends Expense {
    protected String reportName = "Food";

    // EFFECTS: constructs Expense
    public Food(String reportName, float subtotal) {
        super(reportName,subtotal);
    }
}

