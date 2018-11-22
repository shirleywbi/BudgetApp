package ui.panel;

import javax.swing.*;

public class LoadAndSavePanel extends JPanel {
    private JLabel fileNameLabel;
    private JTextField fileNameField;
    private JButton loadButton;
    private JButton saveButton;

    public LoadAndSavePanel() {
        //section 0: Load and Save
        //sets load and save buttons
        fileNameLabel = new JLabel("File Name:");
        fileNameField = new JTextField();
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
//        add(fileNameLabel, labelConstraints(gbc.gridx,gbc.gridy));
//        add(fileNameField, fieldConstraints(gbc.gridx,gbc.gridy++));
//        add(loadButton, addButtonConstraints(gbc.gridx,gbc.gridy++));
//        add(saveButton, addButtonConstraints(gbc.gridx, gbc.gridy++));

    }
}