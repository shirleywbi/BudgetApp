package ui.panel;

import model.Expense;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

public class ReportPanel extends UIFormat implements ActionListener, Observer {
    private JButton expenseListReportButton;
    private JButton expenseTypeReportButton;
    private JButton expensePercentReportButton;
    private JButton expensePieChartButton;

    private JLabel reportLabel = new JLabel("Reports");
    private JPanel reportBlock;
    private JScrollPane reportPane;
    private JTextArea reportText;
    private Expense expense = ExpensePanel.expense;
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public ReportPanel() {
        //sets Report buttons
        expenseListReportButton = new JButton("List of Expenses");
        expenseTypeReportButton = new JButton("Expense Breakdown");
        expensePercentReportButton = new JButton("Expense Percentage Breakdown");
//        expensePieChartButton = new JButton("");
        setButtonColor(expenseListReportButton);
        setButtonColor(expenseTypeReportButton);
        setButtonColor(expensePercentReportButton);
        reportLabel.setFont(getFontDefault());
    }

    //EFFECTS: create the report block that will show the report
    public JPanel createReportBlock() {
        reportBlock = new JPanel();
        reportBlock.setBackground(getBackgroundColor());
        reportBlock.setPreferredSize(new Dimension(800, 750));
        reportText = new JTextArea();
        reportPane = new JScrollPane(reportText);
        reportPane.setBackground(getBackgroundColor());
        reportPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //TODO: SCROLL BAR NOT WORKING
        reportText.setPreferredSize(new Dimension(800 - 2, 750 - 25));
        reportText.setEditable(false);
        reportText.setVisible(false);
        reportBlock.add(reportPane, gbc);
        return reportBlock;
    }

    //EFFECTS: create the report panel and buttons
    public JPanel createReportPanel() {
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(getBackgroundColor());
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.add(reportLabel, labelConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expenseListReportButton, reportButtonConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expenseTypeReportButton, reportButtonConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expensePercentReportButton, reportButtonConstraints(gbc.gridx, gbc.gridy++));
        expenseListReportButton.setActionCommand("expense list");
        expenseListReportButton.addActionListener(this);
        expenseTypeReportButton.setActionCommand("expense type");
        expenseTypeReportButton.addActionListener(this);
        expensePercentReportButton.setActionCommand("expense percent");
        expensePercentReportButton.addActionListener(this);
        return reportPanel;
    }

    //MODIFIES: this
    //EFFECTS: redirects output on console to text area
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

    //MODIFIES: this
    //EFFECTS: adds output to text area
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                reportText.append(text);
            }
        });
    }

    //EFFECTS: when one of the report buttons has been pressed, it will print the appropriate report:
    //         list of expenses, expense breakdown by category and percentages
    @Override
    public void actionPerformed(ActionEvent e) {
        redirectSystemStreams();
        if (e.getActionCommand().equals("pie chart") ||
                e.getActionCommand().equals("expense percent") ||
                e.getActionCommand().equals("expense type") ||
                e.getActionCommand().equals("expense list")) {
            reportText.setVisible(true);
        }
        if (e.getActionCommand().equals("expense list")) {
            showExpenseList();
        } else if (e.getActionCommand().equals("expense type")) {
            showExpenseTypeBreakdown();
        } else if (e.getActionCommand().equals("expense percent")) {
            showExpensePercent();
        } else if (e.getActionCommand().equals("pie chart")) {
            showPieChart();
        }
    }

    //TODO: HOW DO YOU DO THIS? CHECK OUT JAVAFX
    //EFFECTS: shows a pie chart
    private void showPieChart() {
        reportText.setText("");
    }

    //EFFECTS: clears text box and shows expense breakdown into percentages
    private void showExpensePercent() {
        reportText.setText("");
        expense.printExpensePercentage();
    }

    //EFFECTS: clears text box and shows expense breakdown into types
    private void showExpenseTypeBreakdown() {
        reportText.setText("");
        expense.printExpenseBreakdown();
    }

    //EFFECTS: clears text box and shows list of expenses
    private void showExpenseList() {
        reportText.setText("");
        expense.printExpenseList();
    }

    //TODO: CHECK OUT PERCENT BREAKDOWN/BREAKDOWN, IT IS UPDATING ONE SLOWER THAN EXPECTED
    //EFFECTS: when an expense has been added, update the report accordingly
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("expenseItems")) {
            if (reportText.getText().contains("EXPENSE CATEGORIES:")) {
                showExpenseTypeBreakdown();
            } else if (reportText.getText().contains("EXPENSE LIST:")) {
                showExpenseList();
            } else if (reportText.getText().contains("PERCENT BREAKDOWN:")) {
                showExpensePercent();
            }
        }
    }
}
