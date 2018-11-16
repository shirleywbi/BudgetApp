package ui;

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

public class SelectionPanel extends JPanel implements Observer {
    private Income income = BudgetTracker.income;
    private Expense expense = BudgetTracker.expense;
    private ExpenseCategory expCat = new ExpenseCategory();

    private JLabel image;
    private JTextField incomeField;
    private JTextField expenseNameField;
    private JTextField expenseCostField;
    private JComboBox expenseCatComboBox;
    private JButton incomeAddButton;
    private JButton expenseAddButton;

    private int insetDefault = 30;

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

        //sets balance, income, expense amounts as labels
        JLabel balanceAmountLabel = new JLabel();
        JLabel incomeAmountLabel = new JLabel();
        JLabel expenseAmountLabel = new JLabel();

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
            image.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\swwbi\\Pictures\\Saved Pictures\\AJR-TheClick.PNG"))));
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
        add(incomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(expenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));

        //section 2: Add Income
        add(addIncomeLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(incomeField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(incomeAddButton, addButtonConstraints(gbc.gridx,gbc.gridy++));

        //section 3: Add Expense
        add(addExpenseLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCatLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCatComboBox, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseNameLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(expenseNameField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCostLabel, labelConstraints(gbc.gridx,gbc.gridy++));
        add(expenseCostField, fieldConstraints(gbc.gridx,gbc.gridy++));
        add(expenseAddButton, addButtonConstraints(gbc.gridx,gbc.gridy++));

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 0.6666666666666667;
        gbc.weighty = 1f;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        add(image, gbc);

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

    }
}


//GridBagLayout from https://stackoverflow.com/questions/16411197/java-layout-formatting-gridbaglayout
//Resources:
//https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/index.html#TextSamplerDemo
//http://www.java2s.com/Code/JavaAPI/java.awt/FontBOLD.htm