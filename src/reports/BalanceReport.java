package reports;

import model.Balances;

public class BalanceReport extends Report {

    // EFFECTS: Constructs balance report
    public BalanceReport() {
        reportName = "Balance";
        balance = String.format("%.2f", Balances.bal.getBalance());
    }

}
