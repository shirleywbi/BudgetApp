package model.expenses;

public class Rent extends Expense {
    protected String reportName = "Rent";

    // EFFECTS: constructs Expense
    public Rent(String reportName, float subtotal) {
        super(reportName,subtotal);
    }
}