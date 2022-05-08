/**
 * @author Aaron Moser
 */
package View;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;

public class GUIStack extends JPanel {
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

    JLabel oStackLabel = new JLabel("Stack");

    String[][] asData = {{"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}};
    String[] asHeaders = {"0"};
    JTable oStackTable = new JTable(asData, asHeaders);

    public GUIStack() {
        buildGUIStack();
        setTheme(0);
    }

    private void buildGUIStack() {
        GridBagConstraints oConstraints = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        oStackTable.setEnabled(false);
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        this.add(oStackLabel, oConstraints);
        oConstraints.gridy = 1;
        this.add(oStackTable, oConstraints);
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                oStackLabel.setForeground(aoLightTheme[0]);
                oStackLabel.setBackground(aoLightTheme[1]);
                oStackTable.setForeground(aoLightTheme[0]);
                oStackTable.setBackground(aoLightTheme[1]);
                oStackTable.setGridColor(aoLightTheme[2]);
                oStackTable.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2], 2));
            }break;
            case 1: {
                oStackLabel.setForeground(aoDarkTheme[0]);
                oStackLabel.setBackground(aoDarkTheme[1]);
                oStackTable.setForeground(aoDarkTheme[0]);
                oStackTable.setBackground(aoDarkTheme[1]);
                oStackTable.setGridColor(aoDarkTheme[2]);
                oStackTable.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2], 2));
            }break;
        }
            
    }

    public void setStack(int[] aiStack) {
        String s0 = aiStack[0] + "";
        String s1 = aiStack[1] + "";
        String s2 = aiStack[2] + "";
        String s3 = aiStack[3] + "";
        String s4 = aiStack[4] + "";
        String s5 = aiStack[5] + "";
        String s6 = aiStack[6] + "";
        String s7 = aiStack[7] + "";

        oStackTable.setValueAt(s0, 0, 0);
        oStackTable.setValueAt(s1, 1, 0);
        oStackTable.setValueAt(s2, 2, 0);
        oStackTable.setValueAt(s3, 3, 0);
        oStackTable.setValueAt(s4, 4, 0);
        oStackTable.setValueAt(s5, 5, 0);
        oStackTable.setValueAt(s6, 6, 0);
        oStackTable.setValueAt(s7, 7, 0);
    }
}
