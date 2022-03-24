package View;

import java.util.ArrayList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPorts extends JPanel {
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

    JPanel oPanelPortA = new JPanel();
    JPanel oPanelPortB = new JPanel();

    JPanel oPanelPortAPins = new JPanel();
    JPanel oPanelPortATris = new JPanel();
    JPanel oPanelPortBPins = new JPanel();
    JPanel oPanelPortBTris = new JPanel();

    JLabel oPortA = new JLabel("Port A");
    JLabel oPortB = new JLabel("Port B");
    JLabel oTrisA = new JLabel("Tris");
    JLabel oTrisB = new JLabel("Tris");
    JLabel oPinA = new JLabel("Pin");
    JLabel oPinB = new JLabel("Pin");

    JCheckBox oRA4 = new JCheckBox("4");
    JCheckBox oRA3 = new JCheckBox("3");
    JCheckBox oRA2 = new JCheckBox("2");
    JCheckBox oRA1 = new JCheckBox("1");
    JCheckBox oRA0 = new JCheckBox("0");

    JCheckBox oTRISA4 = new JCheckBox("4");
    JCheckBox oTRISA3 = new JCheckBox("3");
    JCheckBox oTRISA2 = new JCheckBox("2");
    JCheckBox oTRISA1 = new JCheckBox("1");
    JCheckBox oTRISA0 = new JCheckBox("0");
    
    JCheckBox oRB7 = new JCheckBox("7");
    JCheckBox oRB6 = new JCheckBox("6");
    JCheckBox oRB5 = new JCheckBox("5");
    JCheckBox oRB4 = new JCheckBox("4");
    JCheckBox oRB3 = new JCheckBox("3");
    JCheckBox oRB2 = new JCheckBox("2");
    JCheckBox oRB1 = new JCheckBox("1");
    JCheckBox oRB0 = new JCheckBox("0");

    JCheckBox oTRISB7 = new JCheckBox("7");
    JCheckBox oTRISB6 = new JCheckBox("6");
    JCheckBox oTRISB5 = new JCheckBox("5");
    JCheckBox oTRISB4 = new JCheckBox("4");
    JCheckBox oTRISB3 = new JCheckBox("3");
    JCheckBox oTRISB2 = new JCheckBox("2");
    JCheckBox oTRISB1 = new JCheckBox("1");
    JCheckBox oTRISB0 = new JCheckBox("0");

    ArrayList<JCheckBox> oCheckboxes = new ArrayList<JCheckBox>();
    ArrayList<JLabel> oLabels = new ArrayList<JLabel>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    boolean bCheckboxesInit = false;

    public GUIPorts() {
        buildGUIPorts();
        fillLists();
        setTheme(0);
    }

    private void buildGUIPorts() {
        oPanelPortAPins.add(oRA4);
        oPanelPortAPins.add(oRA3);
        oPanelPortAPins.add(oRA2);
        oPanelPortAPins.add(oRA1);
        oPanelPortAPins.add(oRA0);

        oPanelPortATris.add(oTRISA4);
        oPanelPortATris.add(oTRISA3);
        oPanelPortATris.add(oTRISA2);
        oPanelPortATris.add(oTRISA1);
        oPanelPortATris.add(oTRISA0);

        oPanelPortBPins.add(oRB7);
        oPanelPortBPins.add(oRB6);
        oPanelPortBPins.add(oRB5);
        oPanelPortBPins.add(oRB4);
        oPanelPortBPins.add(oRB3);
        oPanelPortBPins.add(oRB2);
        oPanelPortBPins.add(oRB1);
        oPanelPortBPins.add(oRB0);

        oPanelPortBTris.add(oTRISB7);
        oPanelPortBTris.add(oTRISB6);
        oPanelPortBTris.add(oTRISB5);
        oPanelPortBTris.add(oTRISB4);
        oPanelPortBTris.add(oTRISB3);
        oPanelPortBTris.add(oTRISB2);
        oPanelPortBTris.add(oTRISB1);
        oPanelPortBTris.add(oTRISB0);

        oPanelPortA.setLayout(new GridBagLayout());
        oPanelPortB.setLayout(new GridBagLayout());

        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.anchor = GridBagConstraints.WEST;

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oPanelPortA.add(oPortA, oConstraints);
        oConstraints.gridy = 1;
        oPanelPortA.add(oPinA, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortA.add(oPanelPortAPins, oConstraints);
        oConstraints.gridx = 0;
        oConstraints.gridy = 2;
        oPanelPortA.add(oTrisA, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortA.add(oPanelPortATris, oConstraints);

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oPanelPortB.add(oPortB, oConstraints);
        oConstraints.gridy = 1;
        oPanelPortB.add(oPinB, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortB.add(oPanelPortBPins, oConstraints);
        oConstraints.gridx = 0;
        oConstraints.gridy = 2;
        oPanelPortB.add(oTrisB, oConstraints);
        oConstraints.gridx = 1;
        oPanelPortB.add(oPanelPortBTris, oConstraints);

        this.setLayout(new GridBagLayout());

        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oPanelPortA, oConstraints);
        oConstraints.gridy = 1;
        this.add(oPanelPortB, oConstraints);
    }

    private void fillLists() {
        oPanels.add(oPanelPortA);
        oPanels.add(oPanelPortAPins);
        oPanels.add(oPanelPortATris);
        oPanels.add(oPanelPortB);
        oPanels.add(oPanelPortBPins);
        oPanels.add(oPanelPortBTris);
        oPanels.add(this);

        oLabels.add(oPortA);
        oLabels.add(oPinA);
        oLabels.add(oTrisA);
        oLabels.add(oPortB);
        oLabels.add(oPinB);
        oLabels.add(oTrisB);

        oCheckboxes.add(oRA0);
        oCheckboxes.add(oRA1);
        oCheckboxes.add(oRA2);
        oCheckboxes.add(oRA3);
        oCheckboxes.add(oRA4);

        oCheckboxes.add(oTRISA0);
        oCheckboxes.add(oTRISA1);
        oCheckboxes.add(oTRISA2);
        oCheckboxes.add(oTRISA3);
        oCheckboxes.add(oTRISA4);

        oCheckboxes.add(oRB0);
        oCheckboxes.add(oRB1);
        oCheckboxes.add(oRB2);
        oCheckboxes.add(oRB3);
        oCheckboxes.add(oRB4);
        oCheckboxes.add(oRB5);
        oCheckboxes.add(oRB6);
        oCheckboxes.add(oRB7);

        oCheckboxes.add(oTRISB0);
        oCheckboxes.add(oTRISB1);
        oCheckboxes.add(oTRISB2);
        oCheckboxes.add(oTRISB3);
        oCheckboxes.add(oTRISB4);
        oCheckboxes.add(oTRISB5);
        oCheckboxes.add(oTRISB6);
        oCheckboxes.add(oTRISB7);

        bCheckboxesInit = true;
    }

    public ArrayList<JCheckBox> getPorts() {
        if (bCheckboxesInit)
            return oCheckboxes;
        else
            return null;
    }

    public void enableCheckboxesA(boolean[] abEnabled) {
        for (int i = 0; i < 10; i++) {
            oCheckboxes.get(i).setEnabled(abEnabled[i]);
        }
    }

    public void enableCheckboxesB(boolean[] abEnabled) {
        for (int i = 10; i < 26; i++) {
            oCheckboxes.get(i).setEnabled(abEnabled[i]);
        }
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);    
                }
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoLightTheme[0]);
                    oLabel.setBackground(aoLightTheme[1]);
                }
                for (JCheckBox oCheckBox : oCheckboxes) {
                    oCheckBox.setForeground(aoLightTheme[0]);
                    oCheckBox.setBackground(aoLightTheme[1]);
                    oCheckBox.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                }
                oPanelPortB.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2], 2));
            }break;
            case 1: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);    
                }
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoDarkTheme[0]);
                    oLabel.setBackground(aoDarkTheme[1]);
                }
                for (JCheckBox oCheckBox : oCheckboxes) {
                    oCheckBox.setForeground(aoDarkTheme[0]);
                    oCheckBox.setBackground(aoDarkTheme[1]);
                    oCheckBox.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                }
                oPanelPortB.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2], 2));
            }break;
        }
    }
}