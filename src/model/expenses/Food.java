package model.expenses;

public class Food extends Expense {

    // EFFECTS: constructs Expense
    public Food(String reportName, float subtotal) {
        super(reportName,subtotal);
    }

    @Override
    protected String getReportName() {
        return "Food";
    }
}

