package Control;

import java.util.ArrayList;

import Model.Microcontroller.PIC;
import View.MyView;

/**
 * Class to set fields of parts of view.
 */
public class MyControlView {

    MyView oMyView;
    PIC oPIC;

    public MyControlView(PIC oPIC, MyView view) {
        oMyView = view;
        this.oPIC = oPIC;
        updateView();
    }

    public void updateView() {
        setRegistersView();
        setPortAView();
        setPortBView();
        setRamView();
        setRegistersDetailed();
        setStack();
        setTestFileTable();
    }

    /**
     * Sets registers at gui to values from PIC.
     */
    private void setRegistersView() {

        //Get values from pic
        int[] aiValues = new int[9];
        aiValues[0] = oPIC.getRam().get_TMR0();
        aiValues[1] = oPIC.getRam().get_Programcounter();
        aiValues[2] = oPIC.getRam().get_STATUS();
        aiValues[3] = oPIC.getRam().get_PCLATH();
        aiValues[4] = oPIC.getRam().get_FSR();
        aiValues[5] = oPIC.getRam().get_PCL();
        aiValues[6] = oPIC.getRam().get_OPTION();
        if (oPIC.getRam().get_PSA()) {
            aiValues[7] = oPIC.getRam().get_WDT_PrescalerRate();
        } else {
            aiValues[7] = oPIC.getRam().get_TMR0_PrescalerRate();
        }
        aiValues[8] = oPIC.get_WRegister();

        //Fill gui element with gathered values 
        oMyView.getGUIRegister().setRegisters(aiValues);
    }

    /**
     * Sets detailed register-table values to values from PIC.
     */
    public void setRegistersDetailed() {
        int iStatus = oPIC.getRam().get_STATUS();
        int iOption = oPIC.getRam().get_OPTION();
        int iIntcon = oPIC.getRam().get_INTCON();

        int iS0 = ((iStatus & 1) == 1) ? 1:0;
        int iS1 = ((iStatus & 2) == 2) ? 1:0;
        int iS2 = ((iStatus & 4) == 4) ? 1:0;
        int iS3 = ((iStatus & 8) == 8) ? 1:0;
        int iS4 = ((iStatus & 16) == 16) ? 1:0;
        int iS5 = ((iStatus & 32) == 32) ? 1:0;
        int iS6 = ((iStatus & 64) == 64) ? 1:0;
        int iS7 = ((iStatus & 128) == 128) ? 1:0;

        int[] aiStatus = {iS0, iS1, iS2, iS3, iS4, iS5, iS6, iS7};
        oMyView.getGUIRegistersDetailed().setStatus(aiStatus);

        int iO0 = ((iOption & 1) == 1) ? 1:0;
        int iO1 = ((iOption & 2) == 2) ? 1:0;
        int iO2 = ((iOption & 4) == 4) ? 1:0;
        int iO3 = ((iOption & 8) == 8) ? 1:0;
        int iO4 = ((iOption & 16) == 16) ? 1:0;
        int iO5 = ((iOption & 32) == 32) ? 1:0;
        int iO6 = ((iOption & 64) == 64) ? 1:0;
        int iO7 = ((iOption & 128) == 128) ? 1:0;

        int[] aiOption = {iO0, iO1, iO2, iO3, iO4, iO5, iO6, iO7};
        oMyView.getGUIRegistersDetailed().setOption(aiOption);

        int iI0 = ((iIntcon & 1) == 1) ? 1:0;
        int iI1 = ((iIntcon & 2) == 2) ? 1:0;
        int iI2 = ((iIntcon & 4) == 4) ? 1:0;
        int iI3 = ((iIntcon & 8) == 8) ? 1:0;
        int iI4 = ((iIntcon & 16) == 16) ? 1:0;
        int iI5 = ((iIntcon & 32) == 32) ? 1:0;
        int iI6 = ((iIntcon & 64) == 64) ? 1:0;
        int iI7 = ((iIntcon & 128) == 128) ? 1:0;

        int[] aiIntcon = {iI0, iI1, iI2, iI3, iI4, iI5, iI6, iI7};
        oMyView.getGUIRegistersDetailed().setIntcon(aiIntcon);
    }

    /**
     * Enables and disables checkboxes of PortA.
     */
    public void setPortAView() {

        boolean[] abEnabled = new boolean[10];

        abEnabled[0] = oPIC.getRam().get_TRISA0();
        abEnabled[1] = oPIC.getRam().get_TRISA1();
        abEnabled[2] = oPIC.getRam().get_TRISA2();
        abEnabled[3] = oPIC.getRam().get_TRISA3();
        abEnabled[4] = oPIC.getRam().get_TRISA4();

        abEnabled[5] = true;
        abEnabled[6] = true;
        abEnabled[7] = true;
        abEnabled[8] = true;
        abEnabled[9] = true;

        oMyView.getGUIPorts().enableCheckboxesA(abEnabled);
    }

    /**
     * Enables and disables checkboxes of PortB.
     */
    public void setPortBView() {

        boolean[] abEnabled = new boolean[16];

        abEnabled[0] = oPIC.getRam().get_TRISB0();
        abEnabled[1] = oPIC.getRam().get_TRISB1();
        abEnabled[2] = oPIC.getRam().get_TRISB2();
        abEnabled[3] = oPIC.getRam().get_TRISB3();
        abEnabled[4] = oPIC.getRam().get_TRISB4();
        abEnabled[5] = oPIC.getRam().get_TRISB5();
        abEnabled[6] = oPIC.getRam().get_TRISB6();
        abEnabled[7] = oPIC.getRam().get_TRISB7();

        abEnabled[8] = true;
        abEnabled[9] = true;
        abEnabled[10] = true;
        abEnabled[11] = true;
        abEnabled[12] = true;
        abEnabled[13] = true;
        abEnabled[14] = true;
        abEnabled[15] = true;

        oMyView.getGUIPorts().enableCheckboxesB(abEnabled);
    }

    public void setRamView() {
        int[] aiData;
        int[] aiBank0 = oPIC.getRam().get_Bank0();
        int[] aiBank1 = oPIC.getRam().get_Bank1();

        aiData = new int[256];
        for (int i = 0; i < 128; i++) {
            aiData[i] = aiBank0[i];
        }
        int j = 0;
        for (int i = 128; i < 256; i++) {
            aiData[i] = aiBank1[j];
            j++;
        }
        oMyView.getGUIRamTable().setGUIRam(aiData);
    }

    public void setStack() {
        oMyView.getGUIStack().setStack(oPIC.getStack().getSTACK());
    }

    public void setTestFileTable() {
        if (oPIC.getEeprom().getProgramLines() != null) {
            if (oPIC.getRam().get_LastProgramcounter() > -1) {
                oMyView.getGUITestFileTable().unmarkLine(oPIC.getEeprom().getProgramLine(oPIC.getRam().get_LastProgramcounter()));
                oMyView.getGUITestFileTable().markLine(oPIC.getEeprom().getProgramLine(oPIC.getRam().get_Programcounter()));
            } else {
                int[] aiProgList = oPIC.getEeprom().getProgramLines();
                for (int i = 0; i < aiProgList.length; i++) {
                    oMyView.getGUITestFileTable().unmarkLine(aiProgList[i]);
                }
                oMyView.getGUITestFileTable().markLine(oPIC.getEeprom().getProgramLine(0));
            }
        }
    }
    
    public void startProgramView() {

    }

    public void stepProgramView() {

    }

    public void pauseProgramView() {

    }

    public void resetProgramView() {

    }

    public void controlWDTView(boolean bEnabled) {

    }

    public void setQuarzView(int iElement) {

    }


    public void loadFileToEEPROMView(ArrayList<String> oData) {

    }

    public void saveSimulatorStateView() {

    }

    public void loadSimulatorStateView() {

    }

    public void exitSimulatorView() {
        
    }

    public void setPIC(PIC oPic) {
        oPIC = oPic;
    }
}