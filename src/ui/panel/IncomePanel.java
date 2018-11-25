package ui.panel;

import model.Income;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class IncomePanel extends Observable implements ActionListener {

    private JLabel incomeAmountLabel;
    private JLabel addIncomeLabel;
    private JTextField incomeField;
    private JButton incomeAddButton;

    private Income income = Income.getInstance();
    private UIFormat ui = new UIFormat();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public IncomePanel() {
        //sets up label, field, add
        incomeAmountLabel = new JLabel("0");
        addIncomeLabel = new JLabel("Add Income");
        addIncomeLabel.setFont(ui.getTitleFontDefault());
        incomeField = new JTextField(21);
        incomeField.setFont(ui.getSubtitleFontDefault());
        incomeAddButton = new JButton("Add");
        incomeAddButton.setFont(ui.getButtonFontDefault());
        ui.setButtonColor(incomeAddButton);
    }

    public JPanel createIncomePanel() {
        JPanel incomePanel = new JPanel();
        incomePanel.setBackground(ui.getBackgroundColor());
        incomePanel.setLayout(new GridBagLayout());

        //adds label, field, addbutton
        incomePanel.add(addIncomeLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        incomePanel.add(incomeField, ui.fieldConstraints(gbc.gridx, gbc.gridy++));
        incomePanel.add(incomeAddButton, ui.addButtonConstraints(gbc.gridx, gbc.gridy++));

        //sets incomeAddButton actions
        incomeAddButton.setActionCommand("add income");
        incomeAddButton.addActionListener(this);
        return incomePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addIncomeUpdate(e);
        setChanged();
        notifyObservers("add income");
    }

    //EFFECTS: if Add income is pressed, update balance and income amount
    private void addIncomeUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("add income")) {
            try {
                double newAmount = Double.parseDouble(incomeField.getText());
                income.addIncome(newAmount);
                incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
                if (newAmount >= 0) {
                    incomeField.setText("");
                }
            } catch (NumberFormatException ex) {
            }

        }
    }
}
