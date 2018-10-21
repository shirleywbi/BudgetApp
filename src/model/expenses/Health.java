package model.expenses;

public class Health extends Expense {
    protected String reportName = "Health";

    // EFFECTS: constructs Expense
    public Health(String reportName, float subtotal) {
        super(reportName,subtotal);
    }
}