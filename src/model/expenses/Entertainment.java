package model.expenses;

public class Entertainment extends Expense {
    protected String reportName = "Entertainment";

    // EFFECTS: constructs Expense
    public Entertainment(String reportName, float subtotal) {
        super(reportName,subtotal);
    }
}