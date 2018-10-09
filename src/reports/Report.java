package reports;

// abstract class Report with method getReport()
public abstract class Report {
    protected String reportName;
    protected String balance;

    public Report() {
        reportName = "";
        balance = "";
    }

    // EFFECTS: Prints and returns the subtotal
    public String getReport(String reportName, float balance) {
        String report = reportName + ": $" + String.format("%.2f",balance);
        System.out.println(report);
        return report;
    }
}