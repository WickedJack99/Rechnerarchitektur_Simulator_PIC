/**
 * @author Aaron Moser
 */
package View;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;

public class GUIRegistersDetailed extends JPanel {

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

    JTable oStatusTable;
    JTable oOptionTable;
    JTable oIntconTable;

    JPanel oStatusPanel = new JPanel();
    JPanel oOptionPanel = new JPanel();
    JPanel oIntconPanel = new JPanel();

    JLabel oStatusLabel = new JLabel("STATUS");
    JLabel oOptionLabel = new JLabel("OPTION");
    JLabel oIntconLabel = new JLabel("INTCON");

    ArrayList<JLabel> oLabels = new ArrayList<JLabel>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();
    ArrayList<JTable> oTables = new ArrayList<JTable>();

    public GUIRegistersDetailed() {
        buildGUIRegistersDetailed();
        addComponentsList();
        setTheme(0);
    }

    private void buildGUIRegistersDetailed() {
        this.setLayout(new GridBagLayout());

        oStatusPanel.setLayout(new GridBagLayout());
        oOptionPanel.setLayout(new GridBagLayout());
        oIntconPanel.setLayout(new GridBagLayout());

        String[][] asDataStatus = {{"IRP", "RP1", "RP0", "TO", "PD", "Z", "DC", "C"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersStatus = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oStatusTable = new JTable(asDataStatus, asHeadersStatus);
        oStatusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsStatus = new GridBagConstraints();
        oConstraintsStatus.anchor = GridBagConstraints.WEST;
        oConstraintsStatus.gridx = 0;
        oConstraintsStatus.gridy = 0;
        oStatusPanel.add(oStatusLabel, oConstraintsStatus);
        oConstraintsStatus.gridy = 1;
        oStatusPanel.add(oStatusTable, oConstraintsStatus);

        for (int i = 0; i < 8; i++) {
            oStatusTable.getColumn(i + "").setPreferredWidth(50);
        }
        oStatusTable.setEnabled(false);

        String[][] asDataOption = {{"RBPU", "INTEDG", "T0CS", "T0SE", "PSA", "PS2", "PS1", "PS0"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersOption = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oOptionTable = new JTable(asDataOption, asHeadersOption);
        oOptionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsOption = new GridBagConstraints();
        oConstraintsOption.anchor = GridBagConstraints.WEST;
        oConstraintsOption.gridx = 0;
        oConstraintsOption.gridy = 0;
        oOptionPanel.add(oOptionLabel, oConstraintsOption);
        oConstraintsOption.gridy = 1;
        oOptionPanel.add(oOptionTable, oConstraintsOption);

        for (int i = 0; i < 8; i++) {
            oOptionTable.getColumn(i + "").setPreferredWidth(50);
        }
        oOptionTable.setEnabled(false);

        String[][] asDataIntcon = {{"GIE", "EEIE", "T0IE", "INTE", "RBIE", "T0IF", "INTF", "RBIF"}, {"0", "0", "0", "0", "0", "0", "0", "0"}};
        String[] asHeadersIntcon = {"0", "1", "2", "3", "4", "5", "6", "7"};
        oIntconTable = new JTable(asDataIntcon, asHeadersIntcon);
        oIntconTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        GridBagConstraints oConstraintsIntcon = new GridBagConstraints();
        oConstraintsIntcon.anchor = GridBagConstraints.WEST;
        oConstraintsIntcon.gridx = 0;
        oConstraintsIntcon.gridy = 0;
        oIntconPanel.add(oIntconLabel, oConstraintsIntcon);
        oConstraintsIntcon.gridy = 1;
        oIntconPanel.add(oIntconTable, oConstraintsIntcon);

        for (int i = 0; i < 8; i++) {
            oIntconTable.getColumn(i + "").setMaxWidth(50);
        }
        oIntconTable.setEnabled(false);

        GridBagConstraints oConstraints = new GridBagConstraints();
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oStatusPanel, oConstraints);
        oConstraints.gridy = 1;
        this.add(oOptionPanel, oConstraints);
        oConstraints.gridy = 2;
        this.add(oIntconPanel, oConstraints);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void addComponentsList() {
        oLabels.add(oIntconLabel);
        oLabels.add(oOptionLabel);
        oLabels.add(oStatusLabel);
        
        oPanels.add(oIntconPanel);
        oPanels.add(oOptionPanel);
        oPanels.add(oStatusPanel);

        oTables.add(oIntconTable);
        oTables.add(oOptionTable);
        oTables.add(oStatusTable);
    }

    public void setStatus(int[] aiStatus) {
        oStatusTable.setValueAt(aiStatus[7] + "", 1, 0);
        oStatusTable.setValueAt(aiStatus[6] + "", 1, 1);
        oStatusTable.setValueAt(aiStatus[5] + "", 1, 2);
        oStatusTable.setValueAt(aiStatus[4] + "", 1, 3);
        oStatusTable.setValueAt(aiStatus[3] + "", 1, 4);
        oStatusTable.setValueAt(aiStatus[2] + "", 1, 5);
        oStatusTable.setValueAt(aiStatus[1] + "", 1, 6);
        oStatusTable.setValueAt(aiStatus[0] + "", 1, 7);
    }

    public void setOption(int[] aiOption) {
        oOptionTable.setValueAt(aiOption[7] + "", 1, 0);
        oOptionTable.setValueAt(aiOption[6] + "", 1, 1);
        oOptionTable.setValueAt(aiOption[5] + "", 1, 2);
        oOptionTable.setValueAt(aiOption[4] + "", 1, 3);
        oOptionTable.setValueAt(aiOption[3] + "", 1, 4);
        oOptionTable.setValueAt(aiOption[2] + "", 1, 5);
        oOptionTable.setValueAt(aiOption[1] + "", 1, 6);
        oOptionTable.setValueAt(aiOption[0] + "", 1, 7);
    }

    public void setIntcon(int[] aiIntcon) {
        oIntconTable.setValueAt(aiIntcon[7] + "", 1, 0);
        oIntconTable.setValueAt(aiIntcon[6] + "", 1, 1);
        oIntconTable.setValueAt(aiIntcon[5] + "", 1, 2);
        oIntconTable.setValueAt(aiIntcon[4] + "", 1, 3);
        oIntconTable.setValueAt(aiIntcon[3] + "", 1, 4);
        oIntconTable.setValueAt(aiIntcon[2] + "", 1, 5);
        oIntconTable.setValueAt(aiIntcon[1] + "", 1, 6);
        oIntconTable.setValueAt(aiIntcon[0] + "", 1, 7);
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoLightTheme[0]);
                    oLabel.setBackground(aoLightTheme[1]);
                }
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                }
                for (JTable oTable : oTables) {
                    oTable.setForeground(aoLightTheme[0]);
                    oTable.setBackground(aoLightTheme[1]);
                    oTable.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                    oTable.setGridColor(aoLightTheme[2]);
                }
                this.setForeground(aoLightTheme[0]);
                this.setBackground(aoLightTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
            }break;
            case 1: {
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoDarkTheme[0]);
                    oLabel.setBackground(aoDarkTheme[1]);
                }
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                }
                for (JTable oTable : oTables) {
                    oTable.setForeground(aoDarkTheme[0]);
                    oTable.setBackground(aoDarkTheme[1]);
                    oTable.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                    oTable.setGridColor(aoDarkTheme[2]);
                }
                this.setForeground(aoDarkTheme[0]);
                this.setBackground(aoDarkTheme[1]);
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
            }break;
        }
    }
}