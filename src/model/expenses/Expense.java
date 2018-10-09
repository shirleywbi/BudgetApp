package model.expenses;

// abstract class Expense with method getSubtotal() and addSubExpense()
// REQUIRES: addSubExpense(): num >= 0
// EFFECTS: getSubtotal(): returns the expense sub-subtotal
//          addSubExpense(): Adds num to the expense sub-subtotal
public abstract class Expense {
    public String reportName;
    public float subtotal;

    // EFFECTS: constructs expense
    public Expense(String reportName,float subtotal) {
        this.reportName = reportName;
        this.subtotal = subtotal;
    }

    // EFFECTS: prints and returns report name and subtotal
    public String getReport(String reportName, float subtotal) {
        String report = reportName + ": $" + String.format("%.2f",subtotal);
        System.out.println(report);
        return report;
    }

    // EFFECTS: Returns the expense subtotal
    public float getSubExpense() {
        return subtotal;
    }

    // REQUIRES: num >= 0
    // EFFECTS: Adds num to the expense subtotal
    public float addSubExpense(float num) {
        subtotal += num;
        return subtotal;
    }

    // EFFECTS: returns report name
    protected abstract String getReportName();

}
