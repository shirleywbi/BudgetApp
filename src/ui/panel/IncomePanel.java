package ui.panel;

import model.Income;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomePanel extends UIFormat implements ActionListener {

    private JLabel incomeAmountLabel;
    private JLabel addIncomeLabel;
    private JTextField incomeField;
    private JButton incomeAddButton;

    private Income income = Income.getInstance();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public IncomePanel() {
        //sets up label, field, add
        incomeAmountLabel = new JLabel("0");
        addIncomeLabel = new JLabel("Add Income");
        addIncomeLabel.setFont(getFontDefault());
        incomeField = new JTextField(20);
        incomeAddButton = new JButton("Add");
        setButtonColor(incomeAddButton);
    }

    public JPanel createIncomePanel() {
        JPanel incomePanel = new JPanel();
        incomePanel.setBackground(getBackgroundColor());
        incomePanel.setLayout(new GridBagLayout());

        //adds label, field, addbutton
        incomePanel.add(addIncomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        incomePanel.add(incomeField, fieldConstraints(gbc.gridx, gbc.gridy++));
        incomePanel.add(incomeAddButton, addButtonConstraints(gbc.gridx,gbc.gridy++));

        //sets incomeAddButton actions
        incomeAddButton.setActionCommand("add income");
        incomeAddButton.addActionListener(this);
        return incomePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addIncomeUpdate(e);
    }

    //EFFECTS: if Add income is pressed, update balance and income amount
    private void addIncomeUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("add income")) {
            double newAmount = Double.parseDouble(incomeField.getText());
            income.addIncome(newAmount);
            incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
            incomeField.setText("");
        }
    }
}
