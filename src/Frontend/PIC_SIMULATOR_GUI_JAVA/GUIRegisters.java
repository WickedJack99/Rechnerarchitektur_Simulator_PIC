package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIRegisters extends JPanel {

    /**
     * Color oWhite = new Color(255, 253, 250);
     * Color oDarkGray = new Color(76, 78, 82);
     * First Color == TextColor
     * Second Color == BackgroundColor
     */
    Color[] aoDarkTheme = {new Color(255, 253, 250), new Color(76, 78, 82)};
    Color[] aoLightTheme = {new Color(76, 78, 82), new Color(255, 253, 250)};

    ArrayList<JTextField> oTextfields = new ArrayList<JTextField>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    JPanel oLeftComponentPanel = new JPanel();
    JPanel oRightComponentPanel = new JPanel();

    JTextField oTextSFR = new JTextField("SFR", 22);

    JTextField oTextPCL = new JTextField("PCL", 22);
    JTextField oTextPCLATH = new JTextField("PCLATH", 22);
    JTextField oTextPCIntern = new JTextField("PC intern", 22);
    JTextField oTextStatus = new JTextField("STATUS", 22);
    JTextField oTextFileSelectionRegister = new JTextField("FileSelectionRegister", 22);
    JTextField oTextOption = new JTextField("OPTION", 22);
    JTextField oTextPrescaler = new JTextField("Prescaler", 22);
    JTextField oTextTMR0 = new JTextField("TMR0", 22);
    
    JTextField oValuePCL = new JTextField("00", 2);
    JTextField oValuePCLATH = new JTextField("00", 2);
    JTextField oValuePCIntern = new JTextField("00", 2);
    JTextField oValueStatus = new JTextField("00", 2);
    JTextField oValueFileSearchRegister = new JTextField("00", 2);
    JTextField oValueOption = new JTextField("00", 2);
    JTextField oValuePrescaler = new JTextField("00", 2);
    JTextField oValueTMR0 = new JTextField("00", 2);

    JTextField oFillTextField1 = new JTextField("", 22);
    JTextField oFillValueField1 = new JTextField("", 2);
    JTextField oFillTextField2 = new JTextField("", 22);
    JTextField oFillValueField2 = new JTextField("", 2);
    JTextField oFillTextField3 = new JTextField("", 22);
    JTextField oFillValueField3 = new JTextField("", 2);
    JTextField oFillValueField4 = new JTextField("", 2);
    JTextField oFillValueField5 = new JTextField("", 2);

    JTextField oTextW = new JTextField("W", 22);

    JTextField oTextWRegister = new JTextField("W-Register", 22);

    JTextField oValueWRegister = new JTextField("00", 2);

    public GUIRegisters() {
        //9
        /**
         * [0] W-Register
         * [1] PCL
         * [2] PCLATH
         * [3] PC intern
         * [4] Status
         * [5] FSR FileSelectionRegister
         * [6] Option
         * [7] Vorteiler
         * [8] TMR0
         */
        addComponentsToLists();
        setEditFalse();
        buildGUIRegisters();
        setTheme(0);
        this.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230)));
    }

    private void buildGUIRegisters() {
        GridBagConstraints oConstraints = new GridBagConstraints();

        oLeftComponentPanel.setLayout(new GridBagLayout());
        oRightComponentPanel.setLayout(new GridBagLayout());

        this.setLayout(new GridBagLayout());

        //Fill left component panel "header"
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oLeftComponentPanel.add(oTextSFR, oConstraints);
        oConstraints.gridx = 1;
        oLeftComponentPanel.add(oFillValueField1);

        //Fill left side of left component panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 1;
        oLeftComponentPanel.add(oTextPCL, oConstraints);
        oConstraints.gridy = 2;
        oLeftComponentPanel.add(oTextPCLATH, oConstraints);
        oConstraints.gridy = 3;
        oLeftComponentPanel.add(oTextPCIntern, oConstraints);
        oConstraints.gridy = 4;
        oLeftComponentPanel.add(oTextStatus, oConstraints);

        //Fill right side of left component panel
        oConstraints.gridx = 1;
        oConstraints.gridy = 1;
        oConstraints.anchor = GridBagConstraints.EAST;
        oLeftComponentPanel.add(oValuePCL, oConstraints);
        oConstraints.gridy = 2;
        oLeftComponentPanel.add(oValuePCLATH, oConstraints);
        oConstraints.gridy = 3;
        oLeftComponentPanel.add(oValuePCIntern, oConstraints);
        oConstraints.gridy = 4;
        oLeftComponentPanel.add(oValueStatus, oConstraints);

        //Fill left side of right component panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oRightComponentPanel.add(oFillTextField1, oConstraints);
        oConstraints.gridy = 1;
        oRightComponentPanel.add(oTextFileSelectionRegister, oConstraints);
        oConstraints.gridy = 2;
        oRightComponentPanel.add(oTextOption, oConstraints);
        oConstraints.gridy = 3;
        oRightComponentPanel.add(oTextPrescaler, oConstraints);
        oConstraints.gridy = 4;
        oRightComponentPanel.add(oTextTMR0, oConstraints);
        oConstraints.gridy = 5;
        oRightComponentPanel.add(oFillTextField2, oConstraints);

        //Fill right side of right component panel
        oConstraints.gridx = 1;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.EAST;
        oRightComponentPanel.add(oFillValueField1, oConstraints);
        oConstraints.gridy = 1;
        oRightComponentPanel.add(oValueFileSearchRegister, oConstraints);
        oConstraints.gridy = 2;
        oRightComponentPanel.add(oValueOption, oConstraints);
        oConstraints.gridy = 3;
        oRightComponentPanel.add(oValuePrescaler, oConstraints);
        oConstraints.gridy = 4;
        oRightComponentPanel.add(oValueTMR0, oConstraints);
        oConstraints.gridy = 5;
        oRightComponentPanel.add(oFillValueField2);

        //Fill footer of left component panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 5;
        oLeftComponentPanel.add(oTextW, oConstraints);
        oConstraints.anchor = GridBagConstraints.EAST;
        oConstraints.gridx = 1;
        oLeftComponentPanel.add(oFillValueField2, oConstraints); 
        oConstraints.gridx = 0;
        oLeftComponentPanel.add(oTextWRegister, oConstraints);
        oConstraints.anchor = GridBagConstraints.EAST;
        oConstraints.gridx = 1;
        oLeftComponentPanel.add(oValueWRegister, oConstraints);

        //Fill this panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oLeftComponentPanel, oConstraints);
        this.add(oRightComponentPanel, oConstraints);
    }

    /**
     * Adds panels to panel list and textfields to textfield list
     */
    private void addComponentsToLists() {
        oTextfields.add(oTextSFR);
        oTextfields.add(oTextPCL);
        oTextfields.add(oTextPCLATH);
        oTextfields.add(oTextPCIntern);
        oTextfields.add(oTextStatus);
        oTextfields.add(oTextFileSelectionRegister);
        oTextfields.add(oTextOption);
        oTextfields.add(oTextPrescaler);
        oTextfields.add(oTextTMR0);
        oTextfields.add(oTextW);
        oTextfields.add(oTextWRegister);

        oTextfields.add(oValuePCL);
        oTextfields.add(oValuePCLATH);
        oTextfields.add(oValuePCIntern);
        oTextfields.add(oValueStatus);
        oTextfields.add(oValueFileSearchRegister);
        oTextfields.add(oValueOption);
        oTextfields.add(oValuePrescaler);
        oTextfields.add(oValueTMR0);
        oTextfields.add(oValueWRegister);

        oPanels.add(this);
        oPanels.add(oLeftComponentPanel);
        oPanels.add(oRightComponentPanel);
    }

    /**
     * 
     */
    private void setEditFalse() {
        for (JTextField oTextfield : oTextfields) {
            oTextfield.setEditable(false);
        }
    }

    /**
     * 
     * @param aiRegisters
     */
    public void setRegisters(int[] aiRegisters) {
        //TODO
    }

    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: {
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                }
                for (JTextField oTextfield : oTextfields) {
                    oTextfield.setForeground(aoLightTheme[0]);
                    oTextfield.setBackground(aoLightTheme[1]);
                    oTextfield.setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                }
            }break;
        
            case 1: {
                System.out.println("Test");
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                }
                for (JTextField oTextfield : oTextfields) {
                    oTextfield.setForeground(aoDarkTheme[0]);
                    oTextfield.setBackground(aoDarkTheme[1]);
                    oTextfield.setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                }
            }break;
        }
    }
}