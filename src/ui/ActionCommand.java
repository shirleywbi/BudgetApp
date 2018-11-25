package ui;

import java.util.HashSet;
import java.util.Set;

public enum ActionCommand {
    EXPENSE_LIST("expense list"),
    EXPENSE_TYPE("expense type"),
    EXPENSE_PERCENT("expense percent");

    private Set reportCommands = new HashSet();

    private void initializeCommands() {
        reportCommands.add(EXPENSE_LIST.getAction());
        reportCommands.add(EXPENSE_PERCENT.getAction());
        reportCommands.add(EXPENSE_TYPE.getAction());
    }

    private String action;

    ActionCommand(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
