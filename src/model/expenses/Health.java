package model.expenses;

public class Health extends Expense {

    // EFFECTS: constructs Expense
    public Health(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

    @Override
    protected String getReportName() {
        return "Health";
    }
}