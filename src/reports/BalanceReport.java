package reports;

import exceptions.SavingsBeingUsedException;

public class BalanceReport extends Report {

    // EFFECTS: Constructs balance report
    public BalanceReport() {
        super("BALANCE");
        balance = getBalance();
    }

    // MODIFIES: this
    // EFFECTS: returns income - expenses
    public float getBalance() {
        return income - expense;
    }

    // EFFECTS: Prints and returns the subtotal
    @Override
    public String getReport(String reportName, float balance) throws SavingsBeingUsedException {
        this.balance = balance;
        String report = reportName + ": $" + String.format("%.2f",balance);
        System.out.println(report);
        if (balance < 0) {
            throw new SavingsBeingUsedException();
        }
        return report;
    }

}
