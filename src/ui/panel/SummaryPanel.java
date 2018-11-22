package ui.panel;

import model.*;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class SummaryPanel extends UIFormat implements Observer, ActionListener {

    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceAmountLabel;
    private JLabel incomeAmountLabel;
    private JLabel expenseAmountLabel;

    private Income income = Income.getInstance();
    public static Expense expense = ExpensePanel.expense;
    JPanel balanceDisplay = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public SummaryPanel() {
        balanceLabel = new JLabel("Balance:");
        incomeLabel = new JLabel("Income:");
        expenseLabel = new JLabel("Expense:");

        balanceLabel.setFont(getFontDefault());
        incomeLabel.setFont(getFontDefault());
        expenseLabel.setFont(getFontDefault());

        balanceAmountLabel = new JLabel("0");
        incomeAmountLabel = new JLabel("0");
        expenseAmountLabel = new JLabel("0");
    }

    public JPanel createSummaryPanel() {
        balanceDisplay.setLayout(new GridBagLayout());
        balanceDisplay.setBackground(getBackgroundColor());
        balanceDisplay.add(balanceLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(balanceAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(incomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(incomeAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(expenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(expenseAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        return balanceDisplay;
    }

    //EFFECTS: updates balance when ... is pressed
    public void actionPerformed(ActionEvent e) {
        double newBalance = income.getIncomeTotal() - expense.getExpenseTotal();
        balanceAmountLabel.setText(String.valueOf(newBalance));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("incomeTotal")) {
            balanceAmountLabel.setText(decimalFormat(income.getIncomeTotal() - expense.getExpenseTotal()));
            incomeAmountLabel.setText(decimalFormat(income.getIncomeTotal()));
        } else if (arg.equals("expenseTotal")) {
            balanceAmountLabel.setText(decimalFormat(income.getIncomeTotal() - expense.getExpenseTotal()));
            expenseAmountLabel.setText(decimalFormat(expense.getExpenseTotal()));
        }
    }

}


//GridBagLayout from https://stackoverflow.com/questions/16411197/java-layout-formatting-gridbaglayout
//Resources:
//https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/index.html#TextSamplerDemo
//http://www.java2s.com/Code/JavaAPI/java.awt/FontBOLD.htm
//Print Console to JTextArea from http://unserializableone.blogspot.com/2009/01/redirecting-systemout-and-systemerr-to.html