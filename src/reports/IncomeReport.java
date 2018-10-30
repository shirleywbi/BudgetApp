package reports;

// class IncomeReport with method getReport()
public class IncomeReport extends Report {


    // EFFECTS: Constructs income report
    public IncomeReport() {
        super("Income");
        balance = income;
    }

}