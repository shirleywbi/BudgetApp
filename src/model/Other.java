package model;

// class Other with method getSubExpense() and addSubExpense()
public class Other implements Expenses, Reports.Report{
    private String reportName = "         " + "Expenses";
    private float balance = 0;

    // EFFECTS: Prints and returns the expense sub-balance
    public String getReport() {
        String report = reportName + ": $" + balance;
        System.out.println(report);
        return report;
    }

    // EFFECTS: Returns the expense sub-balance
    public float getSubExpense() {
        return balance;
    }

    // REQUIRES: num >= 0
    // EFFECTS: Adds num to the expense sub-balance
    public float addSubExpense(float num) {
        balance += num;
        return balance;
    }
}
