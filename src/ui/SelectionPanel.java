package ui;

import exceptions.NegativeAmountException;
import model.Expense;
import model.ExpenseCategory;
import model.Income;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class SelectionPanel extends JPanel implements Observer, ActionListener {
    private Income income = Income.getInstance();
    private Expense expense = BudgetTracker.expense;
    private ExpenseCategory expCat = new ExpenseCategory();

    private JLabel image;
    private JTextField incomeField;
    private JTextField expenseNameField;
    private JTextField expenseCostField;
    private JComboBox expenseCatComboBox;
    private JButton incomeAddButton;
    private JButton expenseAddButton;

    private JLabel balanceAmountLabel;
    private JLabel incomeAmountLabel;
    private JLabel expenseAmountLabel;

    //formatting
    private int insetDefault = 30;
    private Font fontDefault = new Font(null,Font.BOLD,13);

    private ExpenseCategory expenseCategory = new ExpenseCategory();
    private String[] expenseCategories = {"Food"}; //TODO: how to connect to model? Can I call hash map for this?

    public SelectionPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        //sets labels
        JLabel balanceLabel = new JLabel("Balance:");
        JLabel incomeLabel = new JLabel("Income:");
        JLabel expenseLabel = new JLabel("Expense:");
        JLabel addIncomeLabel = new JLabel("Add Income");
        JLabel addExpenseLabel = new JLabel("Add Expense");
        JLabel expenseCatLabel = new JLabel("Type");
        JLabel expenseNameLabel = new JLabel("Name");
        JLabel expenseCostLabel = new JLabel("Cost");
        JLabel reportLabel = new JLabel("Reports");

        //label formatting
        balanceLabel.setFont(fontDefault);
        incomeLabel.setFont(fontDefault);
        expenseLabel.setFont(fontDefault);
        addIncomeLabel.setFont(fontDefault);
        addExpenseLabel.setFont(fontDefault);
        reportLabel.setFont(fontDefault);


        //sets balance, income, expense amounts as labels
        balanceAmountLabel = new JLabel("0");
        incomeAmountLabel = new JLabel("0");
        expenseAmountLabel = new JLabel("0");

        //sets fields
        incomeField = new JTextField(20);
        expenseNameField = new JTextField(20);
        expenseCostField = new JTextField(20);

        //sets Add buttons
        incomeAddButton = new JButton("Add");
        expenseAddButton = new JButton("Add");


        //sets image
        try {
            image = new JLabel();
            image.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\swwbi\\Pictures\\Saved Pictures\\CCI20180321_0014.JPG"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //sets Expense Category combo box
        expenseCatComboBox = new JComboBox(expenseCategories);
        expenseCatComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expCatChoice = (String) expenseCatComboBox.getSelectedItem();
                //some action
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.weightx = 0.3333333333333333f;
//        gbc.weighty = 0.25f;

        //section 1: Balance, Income, Expense
        add(balanceLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(balanceAmountLabel, textConstraints(gbc.gridx,gbc.gridy++));
        add(incomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(incomeAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(expenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(expenseAmountLabel, textConstraints(gbc.gridx,gbc.gridy++));

        //section 2: Add Income
        add(addIncomeLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(incomeField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(incomeAddButton, addButtonConstraints(gbc.gridx,gbc.gridy++));
        incomeAddButton.setActionCommand("add income");

        //section 3: Add Expense
        add(addExpenseLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCatLabel, textConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCatComboBox, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseNameLabel, textConstraints(gbc.gridx,gbc.gridy++));
        add(expenseNameField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCostLabel, textConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCostField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseAddButton, addButtonConstraints(gbc.gridx,gbc.gridy++));
        expenseAddButton.setActionCommand("add expense");

        //section 4: Reports
        add(reportLabel, labelConstraints(gbc.gridx,gbc.gridy++));


        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 0.6666666666666667;
        gbc.weighty = 1f;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        add(image, gbc);

    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add income")) {
            float newAmount = Float.parseFloat(incomeField.getText());
            income.addIncome(newAmount);
            incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
        }
        if (e.getActionCommand().equals("add expense")) {
            float newAmount = Float.parseFloat(expenseCostField.getText());
            try {
                expense.addExpenseAmount(newAmount);
            } catch (NegativeAmountException e1) {
                //TODO: FIX THIS
            }
            expenseAmountLabel.setText(String.valueOf(expense.getExpenseAmount()));
        }
        float newBalance = income.getIncomeTotal() - expense.getExpenseAmount();
        balanceAmountLabel.setText(String.valueOf(newBalance));
    }

    // EFFECTS: sets label constraints and displays in given grid position (x,y)
    private GridBagConstraints labelConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(insetDefault,insetDefault,5,insetDefault);
        return gbc;
    }

    // EFFECTS: sets text constraints and displays in given grid position (x,y)
    private GridBagConstraints textConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5,insetDefault,5,insetDefault);
        return gbc;
    }

    // EFFECTS: sets field constraints and displays in given grid position (x,y)
    private GridBagConstraints fieldConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,insetDefault,5,insetDefault);
        return gbc;
    }

    // EFFECTS: sets add button constraints and displays in given grid position (x,y)
    private GridBagConstraints addButtonConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5,insetDefault,20,insetDefault);
        return gbc;
    }

    @Override
    public void update(Observable o, Object arg) {
        balanceAmountLabel.setText(String.valueOf(income.getIncomeTotal()-expense.getExpenseAmount()));
        incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
        expenseAmountLabel.setText(String.valueOf(expense.getExpenseAmount()));
    }
}


//GridBagLayout from https://stackoverflow.com/questions/16411197/java-layout-formatting-gridbaglayout
//Resources:
//https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/index.html#TextSamplerDemo
//http://www.java2s.com/Code/JavaAPI/java.awt/FontBOLD.htm