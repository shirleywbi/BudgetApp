package ui;

import exceptions.NegativeAmountException;
import model.*;
import reports.ExpenseReport;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

public class SelectionPanel extends JPanel implements Observer, ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;


    private Income income = Income.getInstance();
    private Expense expense = new Expense();

    private JLabel image;

    private JTextField incomeField;
    private JTextField expenseNameField;
    private JTextField expenseCostField;

    private JComboBox expenseCatComboBox;

    private JButton incomeAddButton;
    private JButton expenseAddButton;
    private JButton expenseListReportButton;
    private JButton expenseTypeReportButton;
    private JButton expensePercentReportButton;
    private JButton expensePieChartButton;

    private JLabel balanceAmountLabel;
    private JLabel incomeAmountLabel;
    private JLabel expenseAmountLabel;

    private final JScrollPane reportPane;
    private final JTextArea reportText;

    //formatting
    private int insetDefault = 30;
    private Font fontDefault = new Font(null, Font.BOLD, 13);

    //private ExpenseCategory expenseCategory = new ExpenseCategory();

    public SelectionPanel() {
        setSize(WIDTH, HEIGHT);
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

        //sets Report buttons
        expenseListReportButton = new JButton("List of Expenses");
        expenseTypeReportButton = new JButton("Expense Breakdown");
        expensePercentReportButton = new JButton("Expense Percentage Breakdown");
//        expensePieChartButton = new JButton("");

        //sets image
        try {
            image = new JLabel();
            image.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\swwbi\\Pictures\\Saved Pictures\\CCI20180321_0014.JPG"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //sets Expense Category combo box
        expenseCatComboBox = new JComboBox(ExpenseType.values());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        //section 1: Balance, Income, Expense
        add(balanceLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(balanceAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(incomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(incomeAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(expenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(expenseAmountLabel, textConstraints(gbc.gridx, gbc.gridy++));

        //section 2: Add Income
        add(addIncomeLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(incomeField, fieldConstraints(gbc.gridx, gbc.gridy++));
        add(incomeAddButton, addButtonConstraints(gbc.gridx, gbc.gridy++));
        incomeAddButton.setActionCommand("add income");
        incomeAddButton.addActionListener(this);

        //section 3: Add Expense
        add(addExpenseLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(expenseCatLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(expenseCatComboBox, fieldConstraints(gbc.gridx, gbc.gridy++));
        add(expenseNameLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(expenseNameField, fieldConstraints(gbc.gridx, gbc.gridy++));
        add(expenseCostLabel, textConstraints(gbc.gridx, gbc.gridy++));
        add(expenseCostField, fieldConstraints(gbc.gridx, gbc.gridy++));
        add(expenseAddButton, addButtonConstraints(gbc.gridx, gbc.gridy++));
        expenseAddButton.setActionCommand("add expense");
        expenseAddButton.addActionListener(this);

        //section 4: Reports
        add(reportLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        add(expenseListReportButton, reportButtonConstraints(gbc.gridx,gbc.gridy++));
        add(expenseTypeReportButton, reportButtonConstraints(gbc.gridx,gbc.gridy++));
        add(expensePercentReportButton,reportButtonConstraints(gbc.gridx,gbc.gridy++));
        expenseListReportButton.setActionCommand("expense list");
        expenseListReportButton.addActionListener(this);
        expenseTypeReportButton.setActionCommand("expense type");
        expenseTypeReportButton.addActionListener(this);
        expensePercentReportButton.setActionCommand("expense percent");
        expensePercentReportButton.addActionListener(this);
//        expensePieChartButton.setActionCommand("pie chart");
//        expensePieChartButton.addActionListener(this);


        //section 5: Report Block
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.insets = new Insets(insetDefault, insetDefault/2, insetDefault, insetDefault);
        gbc.gridheight = GridBagConstraints.REMAINDER;
        JPanel reportBlock = new JPanel();
        reportBlock.setSize(WIDTH *3/5,500);
        reportText = new JTextArea("",41,50);
        reportPane = new JScrollPane(reportText);
        reportText.setEditable(false);
        reportBlock.add(reportPane);
        add(reportBlock, gbc);
        reportPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        redirectSystemStreams();
        addIncomeUpdate(e);
        addExpenseUpdate(e);
        showExpenseList(e);
        showExpenseTypeBreakdown(e);
        if (e.getActionCommand().equals("expense percent")){
            reportText.setText("");
            calculateAllCategoryPercent();
        }
        if (e.getActionCommand().equals("pie chart")){
            reportText.setText("");
        }

        float newBalance = income.getIncomeTotal() - expense.getExpenseAmount();
        balanceAmountLabel.setText(String.valueOf(newBalance));
    }

    //EFFECTS: calculates and prints percentage of money spent in each expense category
    public void calculateAllCategoryPercent() {
        float foodPercent = calculateOneCategoryPercent(expense.getFood().getExpenseAmount());
        float entertainmentPercent = calculateOneCategoryPercent(expense.getEntertainment().getExpenseAmount());
        float healthPercent = calculateOneCategoryPercent(expense.getHealth().getExpenseAmount());
        float transportPercent = calculateOneCategoryPercent(expense.getTransportation().getExpenseAmount());
        float rentPercent = calculateOneCategoryPercent(expense.getRent().getExpenseAmount());
        float otherPercent = calculateOneCategoryPercent(expense.getOther().getExpenseAmount());
        System.out.println("PERCENT BREAKDOWN:");
        System.out.printf(expense.getFood().getExpenseCategoryName() + ": %.2f %% %n", foodPercent);
        System.out.printf(expense.getEntertainment().getExpenseCategoryName() + ": %.2f %% %n", entertainmentPercent);
        System.out.printf(expense.getHealth().getExpenseCategoryName() + ": %.2f %% %n", healthPercent);
        System.out.printf(expense.getTransportation().getExpenseCategoryName() + ": %.2f %% %n", transportPercent);
        System.out.printf(expense.getRent().getExpenseCategoryName() + ": %.2f %% %n", rentPercent);
        System.out.printf(expense.getOther().getExpenseCategoryName() + ": %.2f %% %n", otherPercent);
    }

    // EFFECTS: returns percentage of cost to total expense
    private float calculateOneCategoryPercent(float cost) {
        return cost / expense.getExpenseAmount() * 100;
    }

    private void showExpenseTypeBreakdown(ActionEvent e) {
        if (e.getActionCommand().equals("expense type")){
            reportText.setText("");
            System.out.println("EXPENSE CATEGORIES:");
            getReport(expense.getFood().getExpenseCategoryName(), expense.getFood().getExpenseAmount());
            getReport(expense.getEntertainment().getExpenseCategoryName(), expense.getEntertainment().getExpenseAmount());
            getReport(expense.getHealth().getExpenseCategoryName(), expense.getHealth().getExpenseAmount());
            getReport(expense.getTransportation().getExpenseCategoryName(), expense.getTransportation().getExpenseAmount());
            getReport(expense.getRent().getExpenseCategoryName(), expense.getRent().getExpenseAmount());
            getReport(expense.getOther().getExpenseCategoryName(), expense.getOther().getExpenseAmount());
        }
    }

    // EFFECTS: prints and returns report name and total expense
    public void getReport(String reportName, float expenseTotal) {
        String report = reportName + ": $" + String.format("%.2f", expenseTotal);
        System.out.println(report);
    }

    //EFFECT: shows ExpenseList when button pressed
    private void showExpenseList(ActionEvent e) {
        if (e.getActionCommand().equals("expense list")) {
            reportText.setText("");
            System.out.println("EXPENSE LIST:");
            for (Integer i = 0; i < expense.getExpenseItems().size(); i++) {
                System.out.printf(expense.getExpenseItems().get(i).getExpenseItemCategory() + " "
                        + expense.getExpenseItems().get(i).getExpenseItemName()
                        + " $%.2f" + "%n", expense.getExpenseItems().get(i).getExpenseItemCost());
            }
        }
    }

    //EFFECTS: if Add income is pressed, update balance and income amount
    private void addIncomeUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("add income")) {
            float newAmount = Float.parseFloat(incomeField.getText());
            income.addIncome(newAmount);
            incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
            incomeField.setText("");
        }
    }

    //EFFECTS: if Add expense is pressed, update balance and expense amount
    private void addExpenseUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("add expense")) {
            //deals with expense type
            String expCategoryInput = String.valueOf(expenseCatComboBox.getSelectedItem());

            //deals with expense name
            String expNameInput = expenseNameField.getText();

            //deals with expense cost
            float expCostInput = Float.parseFloat(expenseCostField.getText());
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

            //update expense
            expenseAmountLabel.setText(String.valueOf(expense.getExpenseAmount()));

            //reset box
            expenseCatComboBox.setSelectedIndex(0);
            expenseNameField.setText("");
            expenseCostField.setText("");
        }
    }

    // EFFECTS: sets label constraints and displays in given grid position (x,y)
    private GridBagConstraints labelConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(insetDefault, insetDefault, 5, insetDefault);
        return gbc;
    }

    // EFFECTS: sets text constraints and displays in given grid position (x,y)
    private GridBagConstraints textConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, insetDefault, 2, insetDefault);
        return gbc;
    }

    // EFFECTS: sets field constraints and displays in given grid position (x,y)
    private GridBagConstraints fieldConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, insetDefault, 5, insetDefault);
        return gbc;
    }

    // EFFECTS: sets add button constraints and displays in given grid position (x,y)
    private GridBagConstraints addButtonConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, insetDefault, 20, insetDefault);
        return gbc;
    }

    // EFFECTS: sets report button constraints and displays in given grid position (x,y)
    private GridBagConstraints reportButtonConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, insetDefault, 5, insetDefault);
        return gbc;
    }

    @Override
    public void update(Observable o, Object arg) {
        balanceAmountLabel.setText(String.valueOf(income.getIncomeTotal() - expense.getExpenseAmount()));
        incomeAmountLabel.setText(String.valueOf(income.getIncomeTotal()));
        expenseAmountLabel.setText(String.valueOf(expense.getExpenseAmount()));
    }

    //CONSOLE PRINTER??
//The following codes set where the text get redirected.
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                reportText.append(text);
            }
        });
    }

    //Followings are The Methods that do the Redirect, you can simply Ignore them.
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

}


//GridBagLayout from https://stackoverflow.com/questions/16411197/java-layout-formatting-gridbaglayout
//Resources:
//https://www.math.uni-hamburg.de/doc/java/tutorial/uiswing/components/example-1dot4/index.html#TextSamplerDemo
//http://www.java2s.com/Code/JavaAPI/java.awt/FontBOLD.htm
//Print Console to JTextArea from http://unserializableone.blogspot.com/2009/01/redirecting-systemout-and-systemerr-to.html