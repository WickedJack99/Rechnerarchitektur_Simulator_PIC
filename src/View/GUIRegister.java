package View;

import java.util.ArrayList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GUIRegister extends JPanel {
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

    JLabel oSFR = new JLabel("SFR");
    JLabel oW = new JLabel("W");

    /*JTextField oTextPCL = new JTextField("PCL", iTextFieldSize);
    JTextField oTextPCLATH = new JTextField("PCLATH", iTextFieldSize);
    JTextField oTextPCIntern = new JTextField("PC intern", iTextFieldSize);
    JTextField oTextStatus = new JTextField("STATUS", iTextFieldSize);
    JTextField oTextFileSelectionRegister = new JTextField("FileSelectionRegister", iTextFieldSize);
    JTextField oTextOption = new JTextField("OPTION", iTextFieldSize);
    JTextField oTextPrescaler = new JTextField("Prescaler", iTextFieldSize);
    JTextField oTextTMR0 = new JTextField("TMR0", iTextFieldSize);*/

    String[][] asDataSFR = {{"TMR0", "0", "PC intern", "0"}, {"STATUS", "0", "PCLATH", "0"}, {"FSR", "0", "PCL", "0"}, {"OPTION", "0", "Prescaler", "0"}};
    String[] asHeadersSFR = {"0", "1", "2", "3"};

    String[][] asDataW = {{"W-Register", "0", "", ""}};
    String[] asHeadersW = {"0", "1", "2", "3"};

    JTable oSFRRegisters = new JTable(asDataSFR, asHeadersSFR);
    JTable oWRegister = new JTable(asDataW, asHeadersW);

    JPanel oPanelSFR = new JPanel();
    JPanel oPanelW = new JPanel();

    ArrayList<JLabel> oLabels = new ArrayList<JLabel>();
    ArrayList<JTable> oTables = new ArrayList<JTable>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    public GUIRegister() {
        oSFRRegisters.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        addComponents();
        buildGUIRegister();
        setWidth();
        setTheme(0);
    }

    private void addComponents() {
        oLabels.add(oSFR);
        oLabels.add(oW);

        oTables.add(oSFRRegisters);
        oTables.add(oWRegister);

        oPanels.add(oPanelSFR);
        oPanels.add(oPanelW);
        oPanels.add(this);
    }

    private void buildGUIRegister() {
        GridBagConstraints oConstraints = new GridBagConstraints();
        oPanelSFR.setLayout(new GridBagLayout());
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oPanelSFR.add(oSFR, oConstraints);
        oConstraints.gridy = 1;
        oPanelSFR.add(oSFRRegisters, oConstraints);

        oPanelW.setLayout(new GridBagLayout());
        oConstraints.gridy = 0;
        oPanelW.add(oW, oConstraints);
        oConstraints.gridy = 1;
        oPanelW.add(oWRegister, oConstraints);

        this.setLayout(new GridBagLayout());
        oConstraints.gridy = 0;
        this.add(oPanelSFR, oConstraints);
        oConstraints.gridy = 1;
        this.add(oPanelW, oConstraints);
    }

    private void setWidth() {
        for (int i = 0; i < 4; i++) {
            oSFRRegisters.getColumn(i + "").setPreferredWidth(100);
        }
        for (int i = 0; i < 4; i++) {
            oWRegister.getColumn(i + "").setPreferredWidth(100);
        }
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoLightTheme[0]);
                    oLabel.setBackground(aoLightTheme[1]);
                }
                for (JTable oTable : oTables) {
                    oTable.setForeground(aoLightTheme[0]);
                    oTable.setBackground(aoLightTheme[1]);
                    oTable.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                    oTable.setGridColor(aoLightTheme[2]);
                }
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                }
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
            }break;
            case 1: {
                for (JLabel oLabel : oLabels) {
                    oLabel.setForeground(aoDarkTheme[0]);
                    oLabel.setBackground(aoDarkTheme[1]);
                }
                for (JTable oTable : oTables) {
                    oTable.setForeground(aoDarkTheme[0]);
                    oTable.setBackground(aoDarkTheme[1]);
                    oTable.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                    oTable.setGridColor(aoDarkTheme[2]);
                }
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                }
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
            }break;
        }
    }
}