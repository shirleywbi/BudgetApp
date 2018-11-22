/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ui;

import model.Expense;
import model.Income;
import ui.panel.ExpensePanel;
import ui.panel.IncomePanel;
import ui.panel.ReportPanel;
import ui.panel.SummaryPanel;

import javax.swing.*;
import java.awt.*;

public class BudgetTrackerUI {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static Expense expense = new Expense();
    private Income income = Income.getInstance();
    private static UIFormat ui = new UIFormat();
    private static SummaryPanel sp = new SummaryPanel();
    private static ExpensePanel ep = new ExpensePanel();
    private static ReportPanel rp = new ReportPanel();
    private static IncomePanel ip = new IncomePanel();



    //EFFECTS: creates and shows GUI
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        //Create and set up the window.
        JFrame frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        //Set layout type
        frame.getContentPane().setBackground(ui.getBackgroundColor());
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0);

        //Set up the content pane.
        frame.add(sp.createSummaryPanel(), gbc);
        gbc.gridy++;
        frame.add(ip.createIncomePanel(), gbc);
        gbc.gridy++;
        frame.add(ep.createExpensePanel(), gbc);
        gbc.gridy++;
        frame.add(rp.createReportPanel(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        frame.add(rp.createReportBlock(), gbc);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //EFFECTS: initializes observers for expense and income
    private void initializeObservers() {
        expense.addObserver(sp);
        expense.addObserver(rp);
        income.addObserver(sp);
    }

    //EFFECTS: runs program
    public static void main(String[] args) {
        BudgetTrackerUI bt = new BudgetTrackerUI();
        bt.initializeObservers();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
