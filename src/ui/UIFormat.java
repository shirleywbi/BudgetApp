package ui;

import javax.swing.*;
import java.awt.*;

//Formatting for UI
public class UIFormat {
    private int insetDefault = 20;
    private Font titleFontDefault = new Font(null, Font.BOLD, 16);
    private Font subtitleFontDefault = new Font(null, Font.PLAIN,14);
    private Font buttonFontDefault = new Font(null,Font.PLAIN,14);
    private Color balanceColor = Color.BLACK;
    private Color backgroundColor = new Color(216,216,216);

    // EFFECTS: sets label constraints and displays in given grid position (x,y)
    public GridBagConstraints labelConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, insetDefault, 5, insetDefault);
        return gbc;
    }

    // EFFECTS: sets text constraints and displays in given grid position (x,y)
    public GridBagConstraints textConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(2, insetDefault, 2, insetDefault);
        return gbc;
    }

    // EFFECTS: sets field constraints and displays in given grid position (x,y)
    public GridBagConstraints fieldConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, insetDefault, 5, insetDefault);
        return gbc;
    }

    // EFFECTS: sets add button constraints and displays in given grid position (x,y)
    public GridBagConstraints addButtonConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, insetDefault, 0, insetDefault);
        return gbc;
    }

    // EFFECTS: sets chart button constraints and displays in given grid position (x,y)
    public GridBagConstraints reportButtonConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, insetDefault, 5, insetDefault);
        return gbc;
    }

    // EFFECTS: converts double to string in currency format X.XX
    public String decimalFormat(double num){
        return String.format("%.2f", num);
    }

    //getters
    public Font getTitleFontDefault() {
        return titleFontDefault;
    }
    public Font getSubtitleFontDefault() {return subtitleFontDefault;}
    public Font getButtonFontDefault() {return buttonFontDefault;}
    public Color getBackgroundColor() {return backgroundColor;}
    public Color getBalanceColor() {return balanceColor;}

    public void setButtonColor(JButton button){
        button.setBackground(Color.lightGray);
        button.setForeground(Color.BLACK);
    }

}
