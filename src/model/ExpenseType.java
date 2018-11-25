package model;

// Types of expenses
public enum ExpenseType {
    FOOD("FOOD"),
    ENTERTAINMENT("ENTERTAINMENT"),
    HEALTH("HEALTH"),
    TRANSPORTATION("TRANSPORTATION"),
    RENT("RENT"),
    OTHER("OTHER");

    private String expenseType;

    ExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseType() {
        return expenseType;
    }

    
}
