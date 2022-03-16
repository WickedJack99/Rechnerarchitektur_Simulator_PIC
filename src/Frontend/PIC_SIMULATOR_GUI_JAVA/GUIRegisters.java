package Frontend.PIC_SIMULATOR_GUI_JAVA;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIRegisters extends JPanel {

    ArrayList<JTextField> oTextfields = new ArrayList<JTextField>();
    ArrayList<JPanel> oPanels = new ArrayList<JPanel>();

    JPanel oTopComponentPanel = new JPanel();
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

    JPanel oBottomComponentPanel1 = new JPanel();
    JPanel oBottomComponentPanel2 = new JPanel();


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
    }

    private void buildGUIRegisters() {
        GridBagConstraints oConstraints = new GridBagConstraints();

        oTopComponentPanel.setLayout(new GridBagLayout());
        oLeftComponentPanel.setLayout(new GridBagLayout());
        oRightComponentPanel.setLayout(new GridBagLayout());

        oBottomComponentPanel1.setLayout(new GridBagLayout());
        oBottomComponentPanel2.setLayout(new GridBagLayout());

        this.setLayout(new GridBagLayout());

        //Fill top component panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oTopComponentPanel.add(oTextSFR, oConstraints);

        //Fill left side of left component panel
        oLeftComponentPanel.add(oTextPCL, oConstraints);
        oConstraints.gridy = 1;
        oLeftComponentPanel.add(oTextPCLATH, oConstraints);
        oConstraints.gridy = 2;
        oLeftComponentPanel.add(oTextPCIntern, oConstraints);
        oConstraints.gridy = 3;
        oLeftComponentPanel.add(oTextStatus, oConstraints);

        //Fill right side of left component panel
        oConstraints.gridx = 1;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.EAST;
        oLeftComponentPanel.add(oValuePCL, oConstraints);
        oConstraints.gridy = 1;
        oLeftComponentPanel.add(oValuePCLATH, oConstraints);
        oConstraints.gridy = 2;
        oLeftComponentPanel.add(oValuePCIntern, oConstraints);
        oConstraints.gridy = 3;
        oLeftComponentPanel.add(oValueStatus, oConstraints);

        //Fill left side of right component panel
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.WEST;
        oRightComponentPanel.add(oTextFileSelectionRegister, oConstraints);
        oConstraints.gridy = 1;
        oRightComponentPanel.add(oTextOption, oConstraints);
        oConstraints.gridy = 2;
        oRightComponentPanel.add(oTextPrescaler, oConstraints);
        oConstraints.gridy = 3;
        oRightComponentPanel.add(oTextTMR0, oConstraints);

        //Fill right side of right component panel
        oConstraints.gridx = 1;
        oConstraints.gridy = 0;
        oConstraints.anchor = GridBagConstraints.EAST;
        oRightComponentPanel.add(oValueFileSearchRegister, oConstraints);
        oConstraints.gridy = 1;
        oRightComponentPanel.add(oValueOption, oConstraints);
        oConstraints.gridy = 2;
        oRightComponentPanel.add(oValuePrescaler, oConstraints);
        oConstraints.gridy = 3;
        oRightComponentPanel.add(oValueTMR0, oConstraints);

        //Fill 1st bottom component panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        oBottomComponentPanel1.add(oTextW, oConstraints);

        //Fill 2nd bottom component panel
        oBottomComponentPanel2.add(oTextWRegister, oConstraints);
        oConstraints.anchor = GridBagConstraints.EAST;
        oConstraints.gridx = 1;
        oBottomComponentPanel2.add(oValueWRegister, oConstraints);

        //Fill this panel
        oConstraints.anchor = GridBagConstraints.WEST;
        oConstraints.gridx = 0;
        oConstraints.gridy = 0;
        this.add(oTopComponentPanel, oConstraints);
        oConstraints.gridy = 1;
        this.add(oLeftComponentPanel, oConstraints);
        oConstraints.gridx = 1;
        this.add(oRightComponentPanel, oConstraints);
        oConstraints.gridx = 0;
        oConstraints.gridy = 2;
        this.add(oBottomComponentPanel1, oConstraints);
        oConstraints.gridy = 3;
        this.add(oBottomComponentPanel2, oConstraints);
    }

    /**
     * 
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
        //TODO
    }

    /**
     * 
     */
    private void setEditFalse() {
        //TODO
    }

    /**
     * 
     * @param iThemeNr
     */
    public void setTheme(int iThemeNr) {
        //TODO
    }

    /**
     * 
     * @param aiRegisters
     */
    public void setRegisters(int[] aiRegisters) {
        //TODO
    }
}