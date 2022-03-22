package View;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GUIMCMenu extends JPanel {
/**
     * Color oWhite = new Color(255, 253, 250);
     * Color oDarkGray = new Color(76, 78, 82);
     * Color oDarkGrayB = new Color(47, 47, 47);
     * Color oLightBlue = new Color(173, 216, 230);
     * Color oOrangeDM = new Color(255, 170, 0);
     * Color oLightBlueDM = new Color(0, 213, 255);
     * Color oOrangeDMB = new Color(255, 85, 0);
     * First Color == TextColor
     * Second Color == BackgroundColor
     * Third Color == BorderColor
     * Fourth Color == TextColor Marked
     * Fifth Color == BackgroundColor Marked
     * Sixth Color == BorderColor Marked
     */
    Color[] aoDarkTheme = {new Color(255, 253, 250), new Color(76, 78, 82), new Color(47, 47, 47), new Color(0, 213, 255), new Color(255, 170, 0), new Color(255, 85, 0)};
    Color[] aoLightTheme = {new Color(76, 78, 82), new Color(255, 253, 250), new Color(173, 216, 230), new Color(0, 213, 255), new Color(255, 170, 0), new Color(255, 85, 0)};

    JButton oButtonStart = new JButton();
    JButton oButtonStep = new JButton();
    JButton oButtonPause = new JButton();
    JButton oButtonReset = new JButton();

    ArrayList<JButton> oButtons = new ArrayList<JButton>();

    public GUIMCMenu() {
        buildGUIMCMenu();
        fillList();
    }

    private void buildGUIMCMenu() {
        GridBagConstraints oConstraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.insets = new Insets(2, 0, 2, 0);
        oConstraints.anchor = GridBagConstraints.WEST;
        this.add(oButtonStart, oConstraints);
        oConstraints.gridy = 1;
        this.add(oButtonStep, oConstraints);
        oConstraints.gridy = 2;
        this.add(oButtonPause, oConstraints);
    }

    private void fillList() {
        oButtons.add(oButtonStart);
        oButtons.add(oButtonStep);
        oButtons.add(oButtonPause);
        oButtons.add(oButtonReset);
    }

    public ArrayList<JButton> getControlButtons() {
        return oButtons;
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JButton oButton : oButtons) {
                    oButton.setForeground(aoLightTheme[0]);
                    oButton.setBackground(aoLightTheme[1]);
                    oButton.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                }
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
            }break;
            case 1: {
                for (JButton oButton : oButtons) {
                    oButton.setForeground(aoDarkTheme[0]);
                    oButton.setBackground(aoDarkTheme[1]);
                    oButton.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                }
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
            }break;
        }
    }
}
