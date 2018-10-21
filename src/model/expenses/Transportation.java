package model.expenses;

public class Transportation extends Expense {
    protected String reportName = "Transportation";

    // EFFECTS: constructs Expense
    public Transportation(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

}

