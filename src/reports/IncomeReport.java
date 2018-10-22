package reports;

import model.Income;
import ui.BudgetTracker;

// class IncomeReport with method getReport()
public class IncomeReport extends Report {


    // EFFECTS: Constructs income report
    public IncomeReport() {
        super("Income");
        balance = income;
    }

}