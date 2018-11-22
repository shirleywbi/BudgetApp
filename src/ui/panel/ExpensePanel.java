package ui.panel;

import exceptions.NegativeAmountException;
import model.Expense;
import model.ExpenseItem;
import model.ExpenseType;
import ui.BudgetTrackerUI;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpensePanel extends UIFormat implements ActionListener {
    public static Expense expense = BudgetTrackerUI.expense;
    JLabel addExpenseLabel = new JLabel("Add Expense");
    JLabel expenseCatLabel = new JLabel("Type");
    JLabel expenseNameLabel = new JLabel("Name");
    JLabel expenseCostLabel = new JLabel("Cost");

    private JTextField expenseNameField;
    private JTextField expenseCostField;
    private JComboBox expenseCatComboBox;
    private JButton expenseAddButton;
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public ExpensePanel() {
        addExpenseLabel.setFont(getFontDefault());
        expenseNameField = new JTextField(20);
        expenseCostField = new JTextField(20);
        expenseAddButton = new JButton("Add");
        setButtonColor(expenseAddButton);

        //sets Expense Category combo box
        expenseCatComboBox = new JComboBox(ExpenseType.values());
    }

    public JPanel createExpensePanel() {
        JPanel expensePanel = new JPanel();
        expensePanel.setBackground(getBackgroundColor());
        expensePanel.setLayout(new GridBagLayout());
        expensePanel.add(addExpenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCatLabel, textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCatComboBox, fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseNameLabel, textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseNameField, fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCostLabel, textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCostField, fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseAddButton, addButtonConstraints(gbc.gridx, gbc.gridy++));
        expenseAddButton.setActionCommand("add expense");
        expenseAddButton.addActionListener(this);
        return expensePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addExpenseUpdate(e);
    }

    //EFFECTS: if Add expense is pressed, update balance and expense amount
    private void addExpenseUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("add expense")) {
            //deals with expense type
            String expCategoryInput = String.valueOf(expenseCatComboBox.getSelectedItem());

            //deals with expense name
            String expNameInput = expenseNameField.getText();

            //deals with expense cost
            double expCostInput = Double.parseDouble(expenseCostField.getText());
            try {
                if (expCostInput < 0) {
                    throw new NegativeAmountException();
                }
                expense.addExpenseAmount(expCostInput);
                ExpenseItem expItem = new ExpenseItem(expNameInput, expCategoryInput, expCostInput);
                expense.addExpenseItem(expItem);
                expense.sortExpense(expItem);
            } catch (NegativeAmountException e1) {
                System.out.println("Invalid input. Please enter a positive number.");
            }

            //reset box
            expenseCatComboBox.setSelectedIndex(0);
            expenseNameField.setText("");
            expenseCostField.setText("");
        }
    }
}
