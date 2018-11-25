package ui;

public enum ActionCommand {
    ADD_EXPENSE("add expense"),
    ADD_INCOME("add income"),
    LOAD("load"),
    SAVE("save"),
    EXPENSE_LIST("expense list"),
    EXPENSE_TYPE("expense type"),
    EXPENSE_PERCENT("expense percent")
    ;

    private String action;

    ActionCommand(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
