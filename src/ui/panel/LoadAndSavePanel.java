package ui.panel;

import ui.UIFormat;

import javax.swing.*;

public class LoadAndSavePanel extends JPanel {
    private JLabel fileNameLabel;
    private JTextField fileNameField;
    private JButton loadButton;
    private JButton saveButton;
    private UIFormat ui = new UIFormat();

    //EFFECTS: constructs load and save buttons
    public LoadAndSavePanel() {
        fileNameLabel = new JLabel("File Name:");
        fileNameField = new JTextField();
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
//        add(fileNameLabel, ui.labelConstraints(gbc.gridx,gbc.gridy));
//        add(fileNameField, ui.fieldConstraints(gbc.gridx,gbc.gridy++));
//        add(loadButton, ui.addButtonConstraints(gbc.gridx,gbc.gridy++));
//        add(saveButton, ui.addButtonConstraints(gbc.gridx, gbc.gridy++));

    }
}