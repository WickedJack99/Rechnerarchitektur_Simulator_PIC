package View.PIC_SIMULATOR_GUI_JAVA;

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

    ArrayList<JTextField> oTextfields = new ArrayList<JTextField>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    JPanel oLeftComponentPanel = new JPanel();
    JPanel oRightComponentPanel = new JPanel();

    int iTextFieldSize = 15;

    JTextField oTextSFR = new JTextField("SFR", iTextFieldSize);

    JTextField oTextPCL = new JTextField("PCL", iTextFieldSize);
    JTextField oTextPCLATH = new JTextField("PCLATH", iTextFieldSize);
    JTextField oTextPCIntern = new JTextField("PC intern", iTextFieldSize);
    JTextField oTextStatus = new JTextField("STATUS", iTextFieldSize);
    JTextField oTextFileSelectionRegister = new JTextField("FileSelectionRegister", iTextFieldSize);
    JTextField oTextOption = new JTextField("OPTION", iTextFieldSize);
    JTextField oTextPrescaler = new JTextField("Prescaler", iTextFieldSize);
    JTextField oTextTMR0 = new JTextField("TMR0", iTextFieldSize);
    
    JTextField oValuePCL = new JTextField("00", 2);
    JTextField oValuePCLATH = new JTextField("00", 2);
    JTextField oValuePCIntern = new JTextField("00", 2);
    JTextField oValueStatus = new JTextField("00", 2);
    JTextField oValueFileSelectionRegister = new JTextField("00", 2);
    JTextField oValueOption = new JTextField("00", 2);
    JTextField oValuePrescaler = new JTextField("00", 2);
    JTextField oValueTMR0 = new JTextField("00", 2);

    JTextField oFillTextField1 = new JTextField("", iTextFieldSize);
    JTextField oFillValueField1 = new JTextField("", 2);
    JTextField oFillTextField2 = new JTextField("", iTextFieldSize);
    JTextField oFillValueField2 = new JTextField("", 2);
    JTextField oFillTextField3 = new JTextField("", iTextFieldSize);
    JTextField oFillValueField3 = new JTextField("", 2);
    JTextField oFillValueField4 = new JTextField("", 2);
    JTextField oFillValueField5 = new JTextField("", 2);

    JTextField oTextW = new JTextField("W", iTextFieldSize);
    JTextField oTextWRegister = new JTextField("W-Register", iTextFieldSize);
    JTextField oValueWRegister = new JTextField("00", 2);

    /**
     * Constructor of register-table-class, adds components to list, sets components uneditable,
     * builds register-table and colors according to theme.
     */
    public GUIRegisters() {
        addComponentsToLists();
        setEditFalse();
        buildGUIRegisters();
        setTheme(0);
    }

    /**
     * Builds register-panel by actually setting the positions of the fields and adding them to the panels.
     */
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
        oLeftComponentPanel.add(oFillValueField1, oConstraints);

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
        oConstraints.gridy = 6;
        oRightComponentPanel.add(oFillTextField3, oConstraints);

        //Fill right side of right component panel
        oConstraints.gridx = 1;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.EAST;
        oRightComponentPanel.add(oFillValueField2, oConstraints);
        oConstraints.gridy = 1;
        oRightComponentPanel.add(oValueFileSelectionRegister, oConstraints);
        oConstraints.gridy = 2;
        oRightComponentPanel.add(oValueOption, oConstraints);
        oConstraints.gridy = 3;
        oRightComponentPanel.add(oValuePrescaler, oConstraints);
        oConstraints.gridy = 4;
        oRightComponentPanel.add(oValueTMR0, oConstraints);
        oConstraints.gridy = 5;
        oRightComponentPanel.add(oFillValueField3, oConstraints);
        oConstraints.gridy = 6;
        oRightComponentPanel.add(oFillValueField4, oConstraints);

        //Fill footer of left component panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 5;
        oLeftComponentPanel.add(oTextW, oConstraints);
        oConstraints.anchor = GridBagConstraints.EAST;
        oConstraints.gridx = 1;
        oLeftComponentPanel.add(oFillValueField5, oConstraints); 
        oConstraints.gridx = 0;
        oConstraints.gridy = 6;
        oLeftComponentPanel.add(oTextWRegister, oConstraints);
        oConstraints.anchor = GridBagConstraints.EAST;
        oConstraints.gridx = 1;
        oLeftComponentPanel.add(oValueWRegister, oConstraints);

        //Fill this panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oLeftComponentPanel, oConstraints);
        oConstraints.gridx = 1;
        this.add(oRightComponentPanel, oConstraints);
    }

    private void changeWidth() {

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
        oTextfields.add(oFillTextField1);
        oTextfields.add(oFillTextField2);
        oTextfields.add(oFillTextField3);

        oTextfields.add(oValuePCL);
        oTextfields.add(oValuePCLATH);
        oTextfields.add(oValuePCIntern);
        oTextfields.add(oValueStatus);
        oTextfields.add(oValueFileSelectionRegister);
        oTextfields.add(oValueOption);
        oTextfields.add(oValuePrescaler);
        oTextfields.add(oValueTMR0);
        oTextfields.add(oValueWRegister);
        oTextfields.add(oFillValueField1);
        oTextfields.add(oFillValueField2);
        oTextfields.add(oFillValueField3);
        oTextfields.add(oFillValueField4);
        oTextfields.add(oFillValueField5);

        oPanels.add(oLeftComponentPanel);
        oPanels.add(oRightComponentPanel);
    }

    /**
     * Set edit all textfields to false.
     */
    private void setEditFalse() {
        for (JTextField oTextfield : oTextfields) {
            oTextfield.setEditable(false);
        }
    }

    /**
     * Sets textfields of registervalues.
     * @param aiRegisters
     */
    public void setRegisters(int[] aiRegisters) {
        oValuePCL.setText(aiRegisters[0] + "");
        oValuePCLATH.setText(aiRegisters[1] + "");
        oValuePCIntern.setText(aiRegisters[2] + "");
        oValueStatus.setText(aiRegisters[3] + "");
        oValueFileSelectionRegister.setText(aiRegisters[4] + "");
        oValueOption.setText(aiRegisters[5] + "");
        oValuePrescaler.setText(aiRegisters[6] + "");
        oValueTMR0.setText(aiRegisters[7] + "");
        oValueWRegister.setText(aiRegisters[8] + "");
    }

    /**
     * Set theme of RegisterTable
     * @param iThemeNr 0 == light theme, 1 == dark theme
     */
    public void setTheme(int iThemeNr) {
        switch (iThemeNr) {
            case 0: { //light theme
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoLightTheme[0]);
                    oPanel.setBackground(aoLightTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoLightTheme[1]));
                }
                for (JTextField oTextfield : oTextfields) {
                    oTextfield.setForeground(aoLightTheme[0]);
                    oTextfield.setBackground(aoLightTheme[1]);
                    oTextfield.setBorder(BorderFactory.createLineBorder(aoLightTheme[2]));
                }
                this.setBorder(BorderFactory.createLineBorder(aoLightTheme[2], 3));
            }break;
        
            case 1: { //dark theme
                for (JPanel oPanel : oPanels) {
                    oPanel.setForeground(aoDarkTheme[0]);
                    oPanel.setBackground(aoDarkTheme[1]);
                    oPanel.setBorder(BorderFactory.createLineBorder(aoDarkTheme[1]));
                }
                for (JTextField oTextfield : oTextfields) {
                    oTextfield.setForeground(aoDarkTheme[0]);
                    oTextfield.setBackground(aoDarkTheme[1]);
                    oTextfield.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2]));
                }
                this.setBorder(BorderFactory.createLineBorder(aoDarkTheme[2], 3));
            }break;
        }
    }
}