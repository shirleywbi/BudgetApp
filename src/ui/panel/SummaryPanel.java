package ui.panel;

import model.*;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static ui.ActionCommand.ADD_EXPENSE;
import static ui.ActionCommand.ADD_INCOME;
import static ui.ActionCommand.LOAD;

public class SummaryPanel implements Observer {

    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceAmountLabel;
    private JLabel incomeAmountLabel;
    private JLabel expenseAmountLabel;

    private Income income = Income.getInstance();
    public static Expense expense = ExpensePanel.expense;
    private UIFormat ui = new UIFormat();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 2, 5, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public SummaryPanel() {
        balanceLabel = new JLabel("Balance:");
        incomeLabel = new JLabel("Income:");
        expenseLabel = new JLabel("Expense:");
        balanceAmountLabel = new JLabel("0");
        incomeAmountLabel = new JLabel("0");
        expenseAmountLabel = new JLabel("0");

        balanceLabel.setFont(ui.getTitleFontDefault());
        incomeLabel.setFont(ui.getTitleFontDefault());
        expenseLabel.setFont(ui.getTitleFontDefault());
        balanceAmountLabel.setFont(ui.getSubtitleFontDefault());
        incomeAmountLabel.setFont(ui.getSubtitleFontDefault());
        expenseAmountLabel.setFont(ui.getSubtitleFontDefault());
        balanceAmountLabel.setForeground(ui.getBalanceColor());
        incomeAmountLabel.setForeground(ui.getBalanceColor());
        expenseAmountLabel.setForeground(ui.getBalanceColor());

        balanceAmountLabel.setPreferredSize(new Dimension(235, 20));
    }

    public JPanel createSummaryPanel() {
        JPanel balanceDisplay = new JPanel();
        balanceDisplay.setLayout(new GridBagLayout());
        balanceDisplay.setBackground(ui.getBackgroundColor());
        balanceDisplay.add(balanceLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(balanceAmountLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(incomeLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(incomeAmountLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(expenseLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        balanceDisplay.add(expenseAmountLabel, ui.textConstraints(gbc.gridx, gbc.gridy++));
        return balanceDisplay;
    }

    //EFFECTS: updates summary panel balance, income and expense when values are updated
    @Override
    public void update(Observable o, Object arg) {
        double balance = income.getIncomeTotal() - expense.getExpenseTotal();
        if (arg.equals(ADD_INCOME.getAction()) || arg.equals(LOAD.getAction())) {
            balanceAmountLabel.setText(ui.decimalFormat(balance));
            incomeAmountLabel.setText(ui.decimalFormat(income.getIncomeTotal()));
        }
        if (arg.equals(ADD_EXPENSE.getAction()) || arg.equals(LOAD.getAction())) {
            balanceAmountLabel.setText(ui.decimalFormat(balance));
            expenseAmountLabel.setText(ui.decimalFormat(expense.getExpenseTotal()));
        }
        balanceFontColor(balance);
    }

    //EFFECTS: changes to red font when balance is negative, black when >= 0
    private void balanceFontColor(double balance) {
        if (balance < 0) {
            balanceAmountLabel.setForeground(Color.red);
        } else {
            balanceAmountLabel.setForeground(ui.getBalanceColor());
        }
    }
}


//GridBagLayout from https://stackoverflow.com/questions/16411197/java-layout-formatting-gridbaglayout
//Resources:
//https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/index.html#TextSamplerDemo
//http://www.java2s.com/Code/JavaAPI/java.awt/FontBOLD.htm
//Print Console to JTextArea from http://unserializableone.blogspot.com/2009/01/redirecting-systemout-and-systemerr-to.html