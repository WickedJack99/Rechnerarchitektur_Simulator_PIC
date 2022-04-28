package Control;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Microcontroller.PIC;
import View.MyView;

/**
 * Class to set fields of parts of view.
 */
public class MyControlView {

    String[][]asPromptTitle = {
        {"Timer 0 Interrupt Festgestellt", "EE Interrupt Festgestellt", "PORT B Change Interrupt Festgestellt", "RB0 Interrupt Festgestellt"},
        {"Timer 0 Interrupt Acknowledged", "EE Interrupt Acknowledged", "PORT B Change Interrupt Acknowledged", "RB0 Interrupt Acknowledged"}
    };

    String[][]asPromptDialogs = {
        {"Information Timer 0 Interrupt\nISR wird ausgeführt", "Information EE Interrupt\nISR wird ausgeführt", "Information PORT B Change Interrupt\nISR wird ausgeführt", "Information RB0 Interrupt\nISR wird ausgeführt"},
        {"Information Timer 0 Interrupt\nISR will be executed", "Information EE Interrupt\nISR will be executed", "Information PORT B Change Interrupt\nISR will be executed", "Information RB0 Interrupt\nISR will be executed"}
    };

    MyView oMyView;
    PIC oPIC;
    ConcurrentLinkedQueue<Integer> qCommandsToModel;

    public MyControlView(PIC oPIC, MyView view, ConcurrentLinkedQueue<Integer> qCommands) {
        oMyView = view;
        this.oPIC = oPIC;
        qCommandsToModel = qCommands;
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
        setMCMenu();
        
        showStackPrompt();
        showTimer0InterruptPrompt();
        showEEInterruptPrompt();
        showPORTBInterruptPrompt();
        showRB0InterruptPrompt();
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

    public void setMCMenu() {
        oMyView.getGUITime().setRuntime(oPIC.getRuntimer().getRuntime());
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
                int[] aiProgList = oPIC.getEeprom().getProgramLines();
                for (int i = 0; i < aiProgList.length; i++) {
                    oMyView.getGUITestFileTable().unmarkLine(aiProgList[i]);
                }

                int iProgC = oPIC.getRam().get_Programcounter();

                oMyView.getGUITestFileTable().markLine(oPIC.getEeprom().getIndex(iProgC));
                
            } else {
                int[] aiProgList = oPIC.getEeprom().getProgramLines();
                for (int i = 0; i < aiProgList.length; i++) {
                    oMyView.getGUITestFileTable().unmarkLine(aiProgList[i]);
                }
                oMyView.getGUITestFileTable().markLine(oPIC.getEeprom().getProgramLine(0));
            }
        }
    }

    public void showTimer0InterruptPrompt() {
        if (oPIC.getRam().get_GIE() && oPIC.getRam().get_T0IE() && oPIC.getRam().get_T0IF()) {
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(new JFrame(), asPromptDialogs[oMyView.getLanguage()][0], asPromptTitle[oMyView.getLanguage()][0], JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
    }

    public void showEEInterruptPrompt() {
        if (oPIC.getRam().get_GIE() && oPIC.getRam().get_EEIE() && oPIC.getRam().get_EEIF()) {
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(new JFrame(),asPromptDialogs[oMyView.getLanguage()][1], asPromptTitle[oMyView.getLanguage()][1], JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
    }

    public void showPORTBInterruptPrompt() {
        if (oPIC.getRam().get_GIE() && oPIC.getRam().get_RBIE() && oPIC.getRam().get_RBIF()) {
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(new JFrame(),asPromptDialogs[oMyView.getLanguage()][2], asPromptTitle[oMyView.getLanguage()][2], JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
    }

    public void showRB0InterruptPrompt() {
        if (oPIC.getRam().get_GIE() && oPIC.getRam().get_INTE() && oPIC.getRam().get_INTF()) {
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(new JFrame(),asPromptDialogs[oMyView.getLanguage()][3], asPromptTitle[oMyView.getLanguage()][3], JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
    }

    public void showStackPrompt() {
        if (oPIC.getStack().getStackOverflow()) {
            oPIC.getStack().resetStackOverflow();
            Object[] options = {"Continue", "Reset"};
            int n = JOptionPane.showOptionDialog(new JFrame(),"Warning! A stack overflow has occured!", "Stack Overflow", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            qCommandsToModel.add(5);
            switch (n) {
                case (0): {
                    //Start program
                    qCommandsToModel.add(1);
                }break;
                case (1): {
                    //Reset program
                    qCommandsToModel.add(3);
                }break;
                case (-1): {
                    //Nothing happens / pause
                }break;
            }
        } else if (oPIC.getStack().getStackUnderflow()) {
            oPIC.getStack().resetStackUnderflow();
            Object[] options = {"Continue", "Reset"};
            int n = JOptionPane.showOptionDialog(new JFrame(),"Warning! A stack underflow has occured!", "Stack Underflow", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            qCommandsToModel.add(6);
            switch (n) {
                case (0): {
                    //Start program
                    qCommandsToModel.add(1);
                }break;
                case (1): {
                    //Reset program
                    qCommandsToModel.add(3);
                }break;
                case (-1): {
                    //Nothing happens / pause
                }break;
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