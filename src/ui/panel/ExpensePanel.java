package ui.panel;

import exceptions.InvalidEntryException;
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

public class ExpensePanel extends java.util.Observable implements ActionListener {
    public static Expense expense = BudgetTrackerUI.expense;
    JLabel addExpenseLabel = new JLabel("Add Expense");
    JLabel expenseCatLabel = new JLabel("Type");
    JLabel expenseNameLabel = new JLabel("Name");
    JLabel expenseCostLabel = new JLabel("Cost");

    private JTextField expenseNameField;
    private JTextField expenseCostField;
    private JComboBox expenseCatComboBox;
    private JButton expenseAddButton;
    private UIFormat ui = new UIFormat();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public ExpensePanel() {

        expenseNameField = new JTextField(21);
        expenseCostField = new JTextField(21);
        expenseAddButton = new JButton("Add");
        expenseCatComboBox = new JComboBox(ExpenseType.values());
        addExpenseLabel.setFont(ui.getTitleFontDefault());
        expenseAddButton.setFont(ui.getButtonFontDefault());
        expenseCatLabel.setFont(ui.getSubtitleFontDefault());
        expenseNameLabel.setFont(ui.getSubtitleFontDefault());
        expenseCostLabel.setFont(ui.getSubtitleFontDefault());
        expenseCatComboBox.setFont(ui.getSubtitleFontDefault());
        expenseCostField.setFont(ui.getSubtitleFontDefault());
        expenseNameField.setFont(ui.getSubtitleFontDefault());

        ui.setButtonColor(expenseAddButton);

    }

    public JPanel createExpensePanel() {
        JPanel expensePanel = new JPanel();
        expensePanel.setBackground(ui.getBackgroundColor());
        expensePanel.setLayout(new GridBagLayout());
        expensePanel.add(addExpenseLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCatLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCatComboBox, ui.fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseNameLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseNameField, ui.fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCostLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseCostField, ui.fieldConstraints(gbc.gridx, gbc.gridy++));
        expensePanel.add(expenseAddButton, ui.addButtonConstraints(gbc.gridx, gbc.gridy++));
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
            try {
                String expCategoryInput = String.valueOf(expenseCatComboBox.getSelectedItem());
                String expNameInput = expenseNameField.getText();
                if (expNameInput.equals("")){
                    throw new InvalidEntryException();
                }
                double expCostInput = Double.parseDouble(expenseCostField.getText());
                if (expCostInput < 0) {
                    throw new NegativeAmountException();
                }
                expense.addExpenseAmount(expCostInput);
                ExpenseItem expItem = new ExpenseItem(expNameInput, expCategoryInput, expCostInput);
                expense.addExpenseItem(expItem);
                expense.sortExpense(expItem);
                setChanged();
                notifyObservers("expenseTotal");
                resetBoxAndFields();
            } catch (InvalidEntryException ex) {
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: reset fields and expense combo box
    private void resetBoxAndFields() {
        expenseCatComboBox.setSelectedIndex(0);
        expenseNameField.setText("");
        expenseCostField.setText("");
    }
}
