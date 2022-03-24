package Control;

import java.util.ArrayList;

import Model.Backend.MyModel;
import View.MyView;

public class MyControlView {

    MyView oMyView;
    MyModel oMyModel;

    public MyControlView(MyView view, MyModel model) {
        oMyView = view;
        oMyModel = model;
    }

    public void updateView() {
        setRegistersView();
        setPortAView();
        setPortBView();
        setRamView();
    }

    /**
     * Sets registers at gui to values from PIC.
     */
    private void setRegistersView() {

        //Get values from pic
        int[] aiValues = new int[9];
        aiValues[0] = oMyModel.getPIC().getRam().get_TMR0();
        aiValues[1] = oMyModel.getPIC().getRam().get_Programcounter();
        aiValues[2] = oMyModel.getPIC().getRam().get_STATUS();
        aiValues[3] = oMyModel.getPIC().getRam().get_PCLATH();
        aiValues[4] = oMyModel.getPIC().getRam().get_FSR();
        aiValues[5] = oMyModel.getPIC().getRam().get_PCL();
        aiValues[6] = oMyModel.getPIC().getRam().get_OPTION();
        if (oMyModel.getPIC().getRam().get_PSA()) {
            aiValues[7] = oMyModel.getPIC().getRam().get_WDT_PrescalerRate();
        } else {
            aiValues[7] = oMyModel.getPIC().getRam().get_TMR0_PrescalerRate();
        }
        aiValues[8] = oMyModel.getPIC().get_WRegister();

        //Fill gui element with gathered values 
        oMyView.getGUIRegister().setRegisters(aiValues);
    }

    /**
     * Sets detailed register-table values to values from PIC.
     */
    public void setRegistersDetailed() {

    }

    /**
     * 
     */
    public void setPortAView() {

        boolean[] abEnabled = new boolean[10];

        abEnabled[0] = oMyModel.getPIC().getRam().get_TRISA0();
        abEnabled[1] = oMyModel.getPIC().getRam().get_TRISA1();
        abEnabled[2] = oMyModel.getPIC().getRam().get_TRISA2();
        abEnabled[3] = oMyModel.getPIC().getRam().get_TRISA3();
        abEnabled[4] = oMyModel.getPIC().getRam().get_TRISA4();

        abEnabled[5] = true;
        abEnabled[6] = true;
        abEnabled[7] = true;
        abEnabled[8] = true;
        abEnabled[9] = true;

        oMyView.getGUIPorts().enableCheckboxesA(abEnabled);
    }

    public void setPortBView() {

        boolean[] abEnabled = new boolean[10];

        abEnabled[0] = oMyModel.getPIC().getRam().get_TRISB0();
        abEnabled[1] = oMyModel.getPIC().getRam().get_TRISB1();
        abEnabled[2] = oMyModel.getPIC().getRam().get_TRISB2();
        abEnabled[3] = oMyModel.getPIC().getRam().get_TRISB3();
        abEnabled[4] = oMyModel.getPIC().getRam().get_TRISB4();
        abEnabled[5] = oMyModel.getPIC().getRam().get_TRISB5();
        abEnabled[6] = oMyModel.getPIC().getRam().get_TRISB6();
        abEnabled[7] = oMyModel.getPIC().getRam().get_TRISB7();

        abEnabled[8] = true;
        abEnabled[9] = true;
        abEnabled[10] = true;
        abEnabled[11] = true;
        abEnabled[12] = true;
        abEnabled[13] = true;
        abEnabled[14] = true;
        abEnabled[15] = true;

        oMyView.getGUIPorts().enableCheckboxesA(abEnabled);
    }

    public void setRamView() {
        int[] aiData;
        int[] aiBank0 = oMyModel.getPIC().getRam().get_Bank0();
        int[] aiBank1 = oMyModel.getPIC().getRam().get_Bank1();
        int iLenB0 = aiBank0.length;
        int iLenB1 = aiBank1.length;
        aiData = new int[iLenB0 + iLenB1];
        for (int i = 0; i < iLenB0; i++) {
            aiData[i] = aiBank0[i];
        }
        for (int i = iLenB0; i < (iLenB0 + iLenB1); i++) {
            aiData[i] = aiBank1[i];
        }
        oMyView.getGUIRamTable().setGUIRam(aiData);
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
}