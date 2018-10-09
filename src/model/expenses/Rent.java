package model.expenses;

public class Rent extends Expense {

    // EFFECTS: constructs Expense
    public Rent(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

    @Override
    protected String getReportName() {
        return "Rent";
    }
}