package ui;

import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: STARTING POINT FOR UI
public class BudgetTrackerUI extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private JPanel sidePanel;
    private String add = "Add";

    private JLabel label;
    private JTextField incomeField;

    public BudgetTrackerUI() {
        super("Budget Tracker");                //title on application
        setSize(WIDTH, HEIGHT);                      //size of window
        setLayout(new GridBagLayout());
        loadSidebar();
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //how it closes application
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //getters TODO

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(add)) {
            label.setText(incomeField.getText());
        }
    }

    //EFFECTS: loads side bar with option to add income, add expense and select report
    private void loadSidebar() {
        sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(1,1));
        sidePanel.setSize(WIDTH/5,HEIGHT);
        add(sidePanel,FlowLayout.LEFT);
        label = new JLabel("flag");                  //TODO: Turn this into Income
        incomeField = new JTextField(10);         //text bar length
        add(incomeField);
        JButton addIncomeButton = new JButton("Add");
        add(addIncomeButton);
        addIncomeButton.setActionCommand(add);                  //allows for action
        addIncomeButton.addActionListener(this);
        add(label);
    }

    public static void main(String[] args) {
        new BudgetTrackerUI();
    }
}


//based off SmartHomeUI in SmartHome (Basics 1)