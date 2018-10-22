package test;

import model.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeTest {
    Income income;

    @BeforeEach
    public void setup() {
        income = new Income();
    }

    @Test
    public void testInitialIncome() {
        assertTrue(income.getIncome() == 0);
    }

    @Test
    public void testAddIncomeZero() {
        income.addIncome(0);
        assertTrue(income.getIncome() == 0);
    }

    @Test
    public void testAddIncomeOne() {
        income.addIncome(1);
        assertTrue(income.getIncome() == 1);
    }

    @Test
    public void testAddIncomeMore() {
        income.addIncome(1000);
        assertTrue(income.getIncome() == 1000);
        income.addIncome(500);
        assertTrue(income.getIncome() == 1500);
    }
}
