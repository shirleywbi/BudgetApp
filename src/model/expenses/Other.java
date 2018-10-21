package model.expenses;

public class Other extends Expense {
protected String reportName = "Other";

    // EFFECTS: constructs Expense
    public Other(String reportName, float subtotal) {
        super(reportName,subtotal);
    }
}

