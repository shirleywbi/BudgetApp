package model.expenses;

public class Other extends Expense {

    // EFFECTS: constructs Expense
    public Other(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

    @Override
    protected String getReportName() {
        return "Other";
    }
}

