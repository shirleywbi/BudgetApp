package ui.panel;

import javafx.embed.swing.JFXPanel;
import model.Expense;
import ui.UIFormat;
import ui.chart.ExpenseBarChart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
    private JLabel defaultImage = new JLabel();
    private JLabel reportLabel = new JLabel("Reports");
    private JPanel reportBlock;
    private JScrollPane reportPane;
    private JTextArea reportFullText;
    private JTextArea reportHalfText;
    private JFXPanel barChartPanel;
    private ExpenseBarChart expenseBarChart = new ExpenseBarChart();
    private Expense expense = ExpensePanel.expense;
    private UIFormat ui = new UIFormat();
    private GridBagConstraints rpc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);
    private GridBagConstraints rbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.NORTH,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    public ReportPanel() {
        expenseListReportButton = new JButton("List of Expenses");
        expenseTypeReportButton = new JButton("Expense Breakdown");
        expensePercentReportButton = new JButton("Expense Percentage Breakdown");
        expenseListReportButton.setFont(ui.getButtonFontDefault());
        expenseTypeReportButton.setFont(ui.getButtonFontDefault());
        expensePercentReportButton.setFont(ui.getButtonFontDefault());
        ui.setButtonColor(expenseListReportButton);
        ui.setButtonColor(expenseTypeReportButton);
        ui.setButtonColor(expensePercentReportButton);
        reportLabel.setFont(ui.getTitleFontDefault());
        redirectSystemStreams();
    }

    //MODIFIES: this
    //EFFECTS: create the chart block that will show the chart
    public JPanel createReportBlock() {
        reportBlock = new JPanel();
        reportBlock.setLayout(new GridBagLayout());
        reportBlock.setPreferredSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT));
        reportBlock.setBackground(ui.getBackgroundColor());
        showDefaultImage();
        initializeFullTextArea();
        initializeHalfTextArea();
        initializeExpenseTypeBarChart();
        return reportBlock;
    }

    //EFFECTS: sets large text area to be visible when expense list is displayed
    //         and small text area when other reports are displayed
    private void showTextArea() {
        if (isReportExpenseList) {
            reportHalfText.setVisible(false);
            reportPane.setVisible(true);
            reportFullText.setVisible(true);

        } else if (isReportExpensePercent || isReportExpenseBreakdown) {
            reportPane.setVisible(false);
            reportFullText.setVisible(false);
            reportHalfText.setVisible(true);
        }
    }

    //EFFECTS: sets barchart to be visible when expense breakdown is presented
    private void showBarChart() {
        if (isReportExpenseBreakdown) {
            barChartPanel.setVisible(true);
        } else {
            barChartPanel.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes full text area
    private void initializeFullTextArea() {
        reportFullText = new JTextArea();
        reportFullText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        reportFullText.setEditable(false);
        reportPane = new JScrollPane(reportFullText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reportPane.setBackground(ui.getBackgroundColor());
        reportPane.setPreferredSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT));
        reportPane.setVisible(false);
        reportFullText.setVisible(false);
        reportBlock.add(reportPane, rbc);
    }

    //MODIFIES: this
    //EFFECTS: initializes half text area
    private void initializeHalfTextArea() {
        reportHalfText = new JTextArea();
        reportHalfText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        reportHalfText.setBorder(BorderFactory.createLineBorder(new Color(130, 135, 144)));
        reportHalfText.setPreferredSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT / 4));
        reportHalfText.setEditable(false);
        reportHalfText.setVisible(false);
        reportBlock.add(reportHalfText, rbc);
    }

    //MODIFIES: this
    //EFFECTS: shows default image in report block
    private void showDefaultImage() {
        try {
            defaultImage.setIcon(new ImageIcon(ImageIO.read(new File(
                    "C:\\Users\\swwbi\\Dropbox\\Education (Sept 2018-)\\Term 1\\CPSC210\\projectw1_team995\\budget.jpg"))));
        } catch (IOException ex) {
        }
        reportBlock.add(defaultImage, rpc);
    }

    //MODIFIES: this
    //EFFECTS: create the chart panel and buttons
    public JPanel createReportPanel() {
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(ui.getBackgroundColor());
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.add(reportLabel, ui.labelConstraints(rpc.gridx, rpc.gridy++));
        reportPanel.add(expenseListReportButton, ui.reportButtonConstraints(rpc.gridx, rpc.gridy++));
        reportPanel.add(expenseTypeReportButton, ui.reportButtonConstraints(rpc.gridx, rpc.gridy++));
        reportPanel.add(expensePercentReportButton, ui.reportButtonConstraints(rpc.gridx, rpc.gridy++));
        expenseListReportButton.setActionCommand("expense list");
        expenseListReportButton.addActionListener(this);
        expenseTypeReportButton.setActionCommand("expense type");
        expenseTypeReportButton.addActionListener(this);
        expensePercentReportButton.setActionCommand("expense percent");
        expensePercentReportButton.addActionListener(this);
        return reportPanel;
    }

    //EFFECTS: when one of the chart buttons has been pressed, it will print the appropriate chart:
    //         list of expenses, expense breakdown by category and percentages
    @Override
    public void actionPerformed(ActionEvent e) {
        //if any of the buttons pressed, hide default image
        if (e.getActionCommand().equals("expense percent") ||
                e.getActionCommand().equals("expense type") ||
                e.getActionCommand().equals("expense list")) {
            defaultImage.setVisible(false);
        }
        if (e.getActionCommand().equals("expense list")) {
            isReportExpenseList = true;
            isReportExpenseBreakdown = false;
            isReportExpensePercent = false;
            showTextArea();
            showExpenseList();
            showBarChart();
        } else if (e.getActionCommand().equals("expense type")) {
            isReportExpenseList = false;
            isReportExpenseBreakdown = true;
            isReportExpensePercent = false;
            showTextArea();
            showExpenseTypeBreakdown();
            showBarChart();
        } else if (e.getActionCommand().equals("expense percent")) {
            isReportExpenseList = false;
            isReportExpenseBreakdown = false;
            isReportExpensePercent = true;
            showTextArea();
            showExpensePercent();
            showBarChart();
        }
    }

    //EFFECTS: returns a Scene with the Expense Type Bar Chart
    private void initializeExpenseTypeBarChart() {
        barChartPanel = new JFXPanel();
        barChartPanel.setSize(new Dimension(BLOCK_WIDTH, BLOCK_HEIGHT * 3 / 4));
        barChartPanel.setBorder(BorderFactory.createLineBorder(new Color(130, 135, 144)));
        rbc.gridy++;
        rbc.fill = GridBagConstraints.CENTER;
        barChartPanel.setScene(expenseBarChart.createExpenseTypeBarChart());
        reportBlock.add(barChartPanel, rbc);
    }

    //EFFECTS: updates data in the Expense Type Bar Chart
    private void updateExpenseTypeBarChart() {
        expenseBarChart = new ExpenseBarChart();
        barChartPanel.setScene(expenseBarChart.createExpenseTypeBarChart());
        reportBlock.add(barChartPanel, rbc);
    }

    //EFFECTS: when an expense has been added, update the chart accordingly
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("expenseTotal")) {
            showTextArea();
            if (isReportExpenseBreakdown) {
                showExpenseTypeBreakdown();
                showBarChart();
                updateExpenseTypeBarChart();
            } else if (isReportExpenseList) {
                showExpenseList();
            } else if (isReportExpensePercent) {
                showExpensePercent();
            }
        }
    }

    //EFFECTS: clears text box and shows list of expenses
    private void showExpenseList() {
        resetPanel();
        expense.printExpenseList();
    }

    //EFFECTS: clears text box and shows expense breakdown into types
    private void showExpenseTypeBreakdown() {
        resetPanel();
        expense.printExpenseBreakdown();
    }

    //EFFECTS: clears text box and shows expense breakdown into percentages
    private void showExpensePercent() {
        resetPanel();
        expense.printExpensePercentage();
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
                if (isReportExpenseList) {
                    reportFullText.append(text);
                } else if (isReportExpenseBreakdown || isReportExpensePercent) {
                    reportHalfText.append(text);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: clears text panel
    private void resetPanel() {
        reportFullText.setText("");
        reportHalfText.setText("");
    }

}

// image taken from https://www.impactbnd.com/blog/what-to-include-in-your-marketing-budget-1