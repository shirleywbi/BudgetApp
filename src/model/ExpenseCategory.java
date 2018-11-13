package model;

import exceptions.InvalidEntryException;
import exceptions.NegativeAmountException;

import java.util.HashMap;
import java.util.Map;

import static model.Expense.*;

public class ExpenseCategory {
    private Map<String, String> expenseCategories = new HashMap<>();

    // EFFECTS: sets up expense categories:
    //          where 1 = Food, 2 = Entertainment, 3 = Health, 4 = Transportation, 5 = Rent, 6 = Other
    public void setupExpenseCategories() {
        expenseCategories.put("1","Food");
        expenseCategories.put("2","Entertainment");
        expenseCategories.put("3","Health");
        expenseCategories.put("4","Transportation");
        expenseCategories.put("5","Rent");
        expenseCategories.put("6","Other");
    }

    //getters
    public String getExpenseCategoryKeyToValue(String key) throws InvalidEntryException{
        if (expenseCategories.containsKey(key)) {
            return expenseCategories.get(key);
        }
        throw new InvalidEntryException();
    }

    public Map<String,String> getExpenseCategories(){
        return expenseCategories;
    }

    // MODIFIES: this
    // EFFECTS: sorts expense into expense category and adds it to the category expenseTotal
    public void sortToExpenseCategory(ExpenseItem e) throws NegativeAmountException {
        float cost = e.getExpenseItemCost();
        switch (e.getExpenseItemCategory()) {
            case "Food":
                food.addExpenseAmount(cost);
                break;
            case "Entertainment":
                entertainment.addExpenseAmount(cost);
                break;
            case "Health":
                health.addExpenseAmount(cost);
                break;
            case "Rent":
                rent.addExpenseAmount(cost);
                break;
            case "Transportation":
                transportation.addExpenseAmount(cost);
                break;
            default:
                other.addExpenseAmount(cost);
                break;
        }
    }

}
