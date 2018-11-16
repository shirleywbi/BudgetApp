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

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import javax.swing.*;

import static java.awt.Color.black;
import static java.awt.GridBagConstraints.NORTHWEST;

public class GridBagLayoutDemo {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static void addComponentsToPane(Container pane) {
//        pane.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        pane.setBackground(black);

//        JLabel label;
//        label = new JLabel("Income");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(0, 0, 10, 0);
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        pane.add(label, c);
//
//        JTextField field;
//        field = new JTextField(10);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 10;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy++;
//        pane.add(field, c);
//
//        JButton button;
//        button = new JButton("Add");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx++;
//        pane.add(button, c);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        //Create and set up the window.
        JFrame frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets size of frame.
        frame.setSize(WIDTH, HEIGHT);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        frame.add(new SelectionPanel());

        //Display the window.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

