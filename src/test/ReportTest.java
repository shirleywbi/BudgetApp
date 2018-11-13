package test;

import model.Income;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reports.BalanceReport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {
    BalanceReport balanceReport;
    Income income;
    Expense expense;

    @BeforeEach
    public void setup() {
        balanceReport = new BalanceReport();
        income = new Income();
        expense = new Expense();
    }


    @Test
    public void testGetBalanceZero() {
        income.setIncome(0);
        expense.setExpense(0);
        float balance = income.getIncomeTotal() - expense.getExpenseAmount();
        assertEquals(balanceReport.getBalance(), balance);

    }

    @Test
    public void testGetBalance() {
        income.setIncome(500);
        expense.setExpense(300);
        assertEquals(income.getIncomeTotal(),500);
        assertEquals(expense.getExpenseAmount(),300);
        assertEquals(balanceReport.getBalance(), 200);

    }

}
