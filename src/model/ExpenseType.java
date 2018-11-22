package model;

// Types of expenses
public enum ExpenseType {
    FOOD("Food"),
    ENTERTAINMENT("Entertainment"),
    HEALTH("Health"),
    TRANSPORTATION("Transportation"),
    RENT("Rent"),
    OTHER("Other");

    private String expenseType;

    ExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseType() {
        return expenseType;
    }

    
}
