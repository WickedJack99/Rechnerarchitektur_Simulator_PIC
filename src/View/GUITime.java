package View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUITime extends JPanel {
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

    JLabel oLabelWDT = new JLabel("Watchdog Timer");
    JCheckBox oEnableWDT = new JCheckBox("WDT");
    
    JLabel oLabelRuntime = new JLabel("Runtime: 0");

    JLabel oLabelQuarz = new JLabel("Quarzfrequency");
    String[] asIntervals = {"32 kHz", "100 kHz", "500 kHz", "1 MHz", "2 MHz", "4 MHz", "8 MHz", "12 MHz", "16 MHz", "20 MHz"};
    JComboBox<String> oIntervals = new JComboBox<>(asIntervals);

    public GUITime() {
        buildGUITime();
    }

    private void buildGUITime() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.weightx = 1;
        oConstraints.weighty = 1;
        oConstraints.anchor = GridBagConstraints.NORTHWEST;
        oConstraints.insets = new Insets(5,35,0,36);
        this.add(oLabelWDT, oConstraints);
        oConstraints.gridy = 1;
        oConstraints.insets = new Insets(0,35,0,36);
        this.add(oEnableWDT, oConstraints);
        oConstraints.gridy = 2;
        oConstraints.insets = new Insets(20,35,0,36);
        this.add(oLabelRuntime, oConstraints);
        oConstraints.gridy = 3;
        oConstraints.insets = new Insets(20,35,0,36);
        this.add(oLabelQuarz, oConstraints);
        oConstraints.gridy = 4;
        oConstraints.insets = new Insets(0,35,5,36);
        this.add(oIntervals, oConstraints);
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                oLabelWDT.setForeground(aoLightTheme[0]);
                oLabelWDT.setBackground(aoLightTheme[1]);
                oEnableWDT.setForeground(aoLightTheme[0]);
                oEnableWDT.setBackground(aoLightTheme[1]);
                oLabelRuntime.setForeground(aoLightTheme[0]);
                oLabelRuntime.setBackground(aoLightTheme[1]);
                oLabelQuarz.setForeground(aoLightTheme[0]);
                oLabelQuarz.setBackground(aoLightTheme[1]);
                oIntervals.setForeground(aoLightTheme[0]);
                oIntervals.setBackground(aoLightTheme[1]);
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2], 2));
            }break;
            case 1: {
                oLabelWDT.setForeground(aoDarkTheme[0]);
                oLabelWDT.setBackground(aoDarkTheme[1]);
                oEnableWDT.setForeground(aoDarkTheme[0]);
                oEnableWDT.setBackground(aoDarkTheme[1]);
                oLabelRuntime.setForeground(aoDarkTheme[0]);
                oLabelRuntime.setBackground(aoDarkTheme[1]);
                oLabelQuarz.setForeground(aoDarkTheme[0]);
                oLabelQuarz.setBackground(aoDarkTheme[1]);
                oIntervals.setForeground(aoDarkTheme[0]);
                oIntervals.setBackground(aoDarkTheme[1]);
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2], 2));
            }break;
        }
    }
}