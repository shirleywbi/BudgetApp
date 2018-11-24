package ui.panel;

import model.Expense;
import ui.UIFormat;

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

public class ReportPanel implements ActionListener, Observer {
    private static final int BLOCK_WIDTH = 800;
    private static final int BLOCK_HEIGHT = 808;
    private boolean isReportExpenseList = false;
    private boolean isReportExpenseBreakdown = false;
    private boolean isReportExpensePercent = false;
    private JButton expenseListReportButton;
    private JButton expenseTypeReportButton;
    private JButton expensePercentReportButton;
    private JButton expensePieChartButton;

    private JLabel defaultImage = new JLabel();
    private JLabel reportLabel = new JLabel("Reports");
    private JPanel reportBlock;
    private JScrollPane reportPane;
    private JTextArea reportText;
    private Expense expense = ExpensePanel.expense;
    private UIFormat ui = new UIFormat();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public ReportPanel() {
        expenseListReportButton = new JButton("List of Expenses");
        expenseTypeReportButton = new JButton("Expense Breakdown");
        expensePercentReportButton = new JButton("Expense Percentage Breakdown");
        expenseListReportButton.setFont(ui.getButtonFontDefault());
        expenseTypeReportButton.setFont(ui.getButtonFontDefault());
        expensePercentReportButton.setFont(ui.getButtonFontDefault());

//        expensePieChartButton = new JButton("");
        ui.setButtonColor(expenseListReportButton);
        ui.setButtonColor(expenseTypeReportButton);
        ui.setButtonColor(expensePercentReportButton);
        reportLabel.setFont(ui.getTitleFontDefault());
    }

    //EFFECTS: create the report block that will show the report
    public JPanel createReportBlock() {
        reportBlock = new JPanel();
        reportBlock.setPreferredSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT));
        reportBlock.setBackground(ui.getBackgroundColor());
        try {
            defaultImage.setIcon(new ImageIcon(ImageIO.read(new File(
                    "C:\\Users\\swwbi\\Dropbox\\Education (Sept 2018-)\\Term 1\\CPSC210\\projectw1_team995\\budget.jpg"))));
        } catch (IOException ex) {
        }
        reportBlock.add(defaultImage, gbc);
        reportText = new JTextArea();
        reportPane = new JScrollPane(reportText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reportPane.setBackground(ui.getBackgroundColor());
        reportPane.setPreferredSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT));
        reportText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,18));
        reportText.setEditable(false);
        reportText.setVisible(false);
        reportPane.setVisible(false);
        reportBlock.add(reportPane, gbc);
        return reportBlock;
    }

    //EFFECTS: create the report panel and buttons
    public JPanel createReportPanel() {
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(ui.getBackgroundColor());
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.add(reportLabel, ui.labelConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expenseListReportButton, ui.reportButtonConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expenseTypeReportButton, ui.reportButtonConstraints(gbc.gridx, gbc.gridy++));
        reportPanel.add(expensePercentReportButton, ui.reportButtonConstraints(gbc.gridx, gbc.gridy++));
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
            defaultImage.setVisible(false);
            reportPane.setVisible(true);
            reportText.setVisible(true);
        }
        if (e.getActionCommand().equals("expense list")) {
            showExpenseList();
            isReportExpenseList = true;
            isReportExpenseBreakdown = false;
            isReportExpensePercent = false;
        } else if (e.getActionCommand().equals("expense type")) {
            showExpenseTypeBreakdown();
            isReportExpenseList = false;
            isReportExpenseBreakdown = true;
            isReportExpensePercent = false;
        } else if (e.getActionCommand().equals("expense percent")) {
            showExpensePercent();
            isReportExpenseList = false;
            isReportExpenseBreakdown = false;
            isReportExpensePercent = true;
        } else if (e.getActionCommand().equals("pie chart")) {
            showPieChart();
        }
    }

    //TODO: HOW DO YOU DO THIS? CHECK OUT JAVAFX
    //EFFECTS: shows a pie chart
    private void showPieChart() {
        resetPanel();
    }

    //EFFECTS: clears text box and shows expense breakdown into percentages
    private void showExpensePercent() {
        resetPanel();
        expense.printExpensePercentage();
    }

    //EFFECTS: clears text box and shows expense breakdown into types
    private void showExpenseTypeBreakdown() {
        resetPanel();
        expense.printExpenseBreakdown();
    }

    //EFFECTS: clears text box and shows list of expenses
    private void showExpenseList() {
        resetPanel();
        expense.printExpenseList();
    }

    //EFFECTS: when an expense has been added, update the report accordingly
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("expenseTotal")) {
            reportText.setText("");
            if (isReportExpenseBreakdown) {
                showExpenseTypeBreakdown();
            } else if (isReportExpenseList) {
                showExpenseList();
            } else if (isReportExpensePercent) {
                showExpensePercent();
            }
        }
    }

    //EFFECTS: clears text panel
    private void resetPanel() {
        reportText.setText("");
    }

}

// image taken from https://www.impactbnd.com/blog/what-to-include-in-your-marketing-budget-1