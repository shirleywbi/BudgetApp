package test;

import model.Income;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TEST FOR REFLEXIVE BI-DIRECTIONAL RELATIONSHIP BETWEEN PERSON AND INCOME
public class PersonTest {
    Person testPerson1, testPerson2, testPerson3;
    Income testIncome, testIncome2;

    @BeforeEach
    public void setup() {
        testPerson1 = new Person("testPerson1");
        testPerson2 = new Person("testPerson2");
        testPerson3 = new Person("testPerson3");
        testIncome = new Income();
        testIncome2 = new Income();
    }

    @Test
    public void testPersonSetIncomeFromNothing() {
        testIncome.setIncome(1000);
        assertEquals(testIncome.getIncomeTotal(), 1000);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 0.0);
        testPerson1.setIncome(testIncome);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 1000);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testIncome.getPersons().get(0), testPerson1);
    }

    @Test
    public void testPersonSetIncomeMultiple() {
        testPersonSetIncomeFromNothing();
        testPerson2.setIncome(testIncome);
        assertEquals(testPerson2.getIncome().getIncomeTotal(), 1000);
        assertEquals(testIncome.getPersons().size(),2);
        assertEquals(testIncome.getPersons().get(0), testPerson1);
        assertEquals(testIncome.getPersons().get(1), testPerson2);
    }

    @Test
    public void testPersonSetIncomeNewFromOld() {
        testPersonSetIncomeFromNothing();
        testIncome2.setIncome(2000);
        assertEquals(testIncome2.getIncomeTotal(), 2000);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 1000);
        testPerson1.setIncome(testIncome2);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 2000);
        assertEquals(testIncome.getPersons().size(),0);
        assertEquals(testIncome2.getPersons().size(),1);
    }

    @Test
    public void testPersonRemoveIncome() {
        testIncome.setIncome(1000);
        testPerson1.setIncome(testIncome);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 1000);
        testPerson1.removeIncome(testIncome);
        assertEquals(testPerson1.getIncome().getIncomeTotal(),0);
        assertEquals(testIncome.getPersons().size(),0);
    }

    @Test
    public void testPersonRemoveIncomeDifferentIncome() {
        testIncome.setIncome(1000);
        testPerson1.setIncome(testIncome);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 1000);
        testPerson1.removeIncome(testIncome2);
        assertEquals(testPerson1.getIncome().getIncomeTotal(),1000);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testIncome2.getPersons().size(),0);
    }

    @Test
    public void testIncomeSetPerson() {
        testIncome.setIncome(1000);
        assertEquals(testIncome.getPersons().size(),0);
        assertEquals(testPerson1.getIncome().getIncomeTotal(),0);
        testIncome.addPersons(testPerson1);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testIncome.getPersons().get(0), testPerson1);
        assertEquals(testPerson1.getIncome().getIncomeTotal(),1000);
    }

    @Test
    public void testIncomeRemovePersonAlreadyThere() {
        testPersonSetIncomeMultiple();
        assertEquals(testIncome.getPersons().size(), 2);
        testIncome.removePersons(testPerson1);
        assertEquals(testIncome.getPersons().size(),1);
        assertEquals(testIncome.getPersons().get(0), testPerson2);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 0);
        assertEquals(testPerson2.getIncome().getIncomeTotal(), 1000);
    }

    @Test
    public void testIncomeRemovePersonNotThere() {
        testPersonSetIncomeMultiple();
        assertEquals(testIncome.getPersons().size(), 2);
        testIncome.removePersons(testPerson3);
        assertEquals(testIncome.getPersons().size(),2);
        assertEquals(testIncome.getPersons().get(0), testPerson1);
        assertEquals(testIncome.getPersons().get(1), testPerson2);
        assertEquals(testPerson1.getIncome().getIncomeTotal(), 1000);
        assertEquals(testPerson2.getIncome().getIncomeTotal(), 1000);
    }
}
