package ui.panel;

import ui.Operations;
import ui.UIFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;

import static ui.ActionCommand.LOAD;
import static ui.ActionCommand.SAVE;

public class LoadAndSavePanel extends Observable implements ActionListener {
    private boolean isLoaded = false;
    private String filename = "budgetinput.txt";
    private JButton loadButton;
    private JButton saveButton;
    private UIFormat ui = new UIFormat();
    private Operations op = new Operations();
    private GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
            GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0);

    //EFFECTS: constructs load and save buttons
    public LoadAndSavePanel() {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");

        loadButton.setFont(ui.getButtonFontDefault());
        saveButton.setFont(ui.getButtonFontDefault());
        ui.setButtonColor(loadButton);
        ui.setButtonColor(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: creates load and save panel
    public JPanel createLoadAndSavePanel() {
        JPanel loadAndSavePanel = new JPanel();
        loadAndSavePanel.setBackground(ui.getBackgroundColor());
        loadAndSavePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 5));
        loadAndSavePanel.add(loadButton);
        loadAndSavePanel.add(saveButton);
        loadButton.setActionCommand(LOAD.getAction());
        loadButton.addActionListener(this);
        saveButton.setActionCommand(SAVE.getAction());
        saveButton.addActionListener(this);
        return loadAndSavePanel;
    }

    //EFFECTS: if load button pressed, loads file; if save button pressed, saves file
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LOAD.getAction())) {
            if (!isLoaded) {
                try {
                    op.load(filename);
                    setChanged();
                    notifyObservers(LOAD.getAction());
                    isLoaded = true;
                } catch (IOException e1) {
                    System.out.println("File error.");
                }
            }
        } else if (e.getActionCommand().equals(SAVE.getAction())) {
            try {
                op.save(filename);
            } catch (IOException e1) {
                System.out.println("File could not be saved.");
            }
        }
    }

}