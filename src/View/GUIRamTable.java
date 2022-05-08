/**
 * @author Aaron Moser
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class GUIRamTable extends JScrollPane {
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

    JLabel oRam = new JLabel("RAM");

    String[][] assData = {{"0x", "00", "01", "02", "03", "04", "05", "06", "07"}, {"00", "", "", "", "", "", "", "", ""},
                          {"08", "", "", "", "", "", "", "", ""}, {"10", "", "", "", "", "", "", "", ""},
                          {"18", "", "", "", "", "", "", "", ""}, {"20", "", "", "", "", "", "", "", ""},
                          {"28", "", "", "", "", "", "", "", ""}, {"30", "", "", "", "", "", "", "", ""},
                          {"38", "", "", "", "", "", "", "", ""}, {"40", "", "", "", "", "", "", "", ""},
                          {"48", "", "", "", "", "", "", "", ""}, {"50", "", "", "", "", "", "", "", ""},
                          {"58", "", "", "", "", "", "", "", ""}, {"60", "", "", "", "", "", "", "", ""},
                          {"68", "", "", "", "", "", "", "", ""}, {"70", "", "", "", "", "", "", "", ""},
                          {"78", "", "", "", "", "", "", "", ""}, {"80", "", "", "", "", "", "", "", ""},
                          {"88", "", "", "", "", "", "", "", ""}, {"90", "", "", "", "", "", "", "", ""},
                          {"98", "", "", "", "", "", "", "", ""}, {"A0", "", "", "", "", "", "", "", ""},
                          {"A8", "", "", "", "", "", "", "", ""}, {"B0", "", "", "", "", "", "", "", ""},
                          {"B8", "", "", "", "", "", "", "", ""}, {"C0", "", "", "", "", "", "", "", ""},
                          {"C8", "", "", "", "", "", "", "", ""}, {"D0", "", "", "", "", "", "", "", ""},
                          {"D8", "", "", "", "", "", "", "", ""}, {"E0", "", "", "", "", "", "", "", ""},
                          {"E8", "", "", "", "", "", "", "", ""}, {"F0", "", "", "", "", "", "", "", ""},
                          {"F8", "", "", "", "", "", "", "", ""}};
    String[] asHeaders = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    JTable oRamTable = new JTable(assData, asHeaders);
    JPanel oRamPanel = new JPanel();

    public GUIRamTable() {
        buildGUIRamTable();
        setColumnWidth();
        setTheme(0);
    }

    private void buildGUIRamTable() {
        oRamTable.setEnabled(false);
        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oRamPanel.setLayout(new GridBagLayout());
        oRamPanel.add(oRam, oConstraints);
        oConstraints.gridy = 1;
        oRamPanel.add(oRamTable, oConstraints);
        this.setPreferredSize(new Dimension(320, 350));
        this.setViewportView(oRamPanel);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setWheelScrollingEnabled(true);
        this.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    public void setGUIRam(int[] aiRam) {
        int j = 0;
        for (int i = 1; i < 33; i++) {
            for (int k = 1; k < 9; k++) {
                oRamTable.setValueAt(aiRam[j] + "", i, k);
                j++;
            }
        }
    }

    private void setColumnWidth() {
        for (int i = 0; i < 9; i++) {
            oRamTable.getColumn(i + "").setPreferredWidth(33);
        }
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                oRam.setForeground(aoLightTheme[0]);
                oRam.setBackground(aoLightTheme[1]);
                oRamPanel.setForeground(aoLightTheme[0]);
                oRamPanel.setBackground(aoLightTheme[1]);
                oRamTable.setForeground(aoLightTheme[0]);
                oRamTable.setBackground(aoLightTheme[1]);
                oRamTable.setGridColor(aoLightTheme[2]);
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2], 2));
            }break;
            case 1: {
                oRam.setForeground(aoDarkTheme[0]);
                oRam.setBackground(aoDarkTheme[1]);
                oRamPanel.setForeground(aoDarkTheme[0]);
                oRamPanel.setBackground(aoDarkTheme[1]);
                oRamTable.setForeground(aoDarkTheme[0]);
                oRamTable.setBackground(aoDarkTheme[1]);
                oRamTable.setGridColor(aoDarkTheme[2]);
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2], 2));
            }break;
        }
    }
}
