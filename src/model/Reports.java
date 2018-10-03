package model;

public class Reports {
    public static Reports report = new Reports();

    // EFFECTS: Displays total balance, total income, total expenses, and list of expenses
    public void runReports() {
        Report incomeReport = new IncomeReport();
        ExpenseReport expenseReport = new ExpenseReport();
        Report balanceReport = new BalanceReport();

        System.out.printf("*********************************%n");
        balanceReport.getReport();
        incomeReport.getReport();
        expenseReport.getReport();
        expenseReport.getExpenseList();
        expenseReport.getExpenseBreakdown();
        System.out.println("*********************************");
    }

    // interface Report with method getReport()
    // EFFECTS: getReport(): Prints and returns the balance
    interface Report {
        String getReport();
    }

    // class IncomeReport with method getReport()
    class IncomeReport implements Report {
        private String reportName = "Income";
        private String balance = String.format("%.2f", Balances.bal.getIncome());

        // EFFECTS: Prints and returns the balance
        public String getReport() {
            String report = reportName + ": $" + balance;
            System.out.println(report);
            return report;
        }
    }

    // class ExpenseReport with method getReport() and getExpenseList()
    class ExpenseReport implements Report {
        private String reportName = "Expenses";
        private String balance = String.format("%.2f", Balances.bal.getExpenses());
        private Balances expenseList = Balances.bal;
        private Report foodReport = Balances.foodTotal;
        private Report rentReport = Balances.rentTotal;
        private Report transportationReport = Balances.transportationTotal;
        private Report otherReport = Balances.otherTotal;

        // EFFECTS: Prints and returns the balance
        public String getReport() {
            String report = reportName + ": $" + balance;
            System.out.println(report);
            return report;
        }

        // EFFECTS: Displays list of expenses; if no expenses, shows nothing
        public void getExpenseList() {
            for (Integer i = 0; i < expenseList.nameList.size(); i++) {
                System.out.printf("         " + expenseList.categoryList.get(i) + " "
                        + expenseList.nameList.get(i) + " $%.2f" + "%n", expenseList.costList.get(i));
            }
        }

        // EFFECTS: Displays subtotals of each expense category
        public void getExpenseBreakdown() {
            System.out.println("Expense Categories:");
            foodReport.getReport();
            rentReport.getReport();
            transportationReport.getReport();
            otherReport.getReport();
        }
    }

    // class BalanceReport with method getReport()
    class BalanceReport implements Report {
        private String reportName = "Balance";
        private String balance = String.format("%.2f", Balances.bal.getBalance());

        // EFFECTS: Prints and returns the balance
        public String getReport() {
            String report = reportName + ": $" + balance;
            System.out.println(report);
            return report;
        }
    }

}
