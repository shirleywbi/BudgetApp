package reports;

import model.Balances;

// class IncomeReport with method getReport()
public class IncomeReport extends Report {

    // EFFECTS: Constructs income report
    public IncomeReport() {
        reportName = "Income";
        balance = String.format("%.2f", Balances.bal.getIncome());
    }

}