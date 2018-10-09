package model.expenses;

public class Transportation extends Expense {

    // EFFECTS: constructs Expense
    public Transportation(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

    @Override
    protected String getReportName() {
        return "Transportation";
    }
}

