package Model;

import java.util.ArrayList;

import Model.Microcontroller.Bitmask;
import Model.Microcontroller.PIC;
import Model.Microcontroller.WATCHDOG;
import View.GUIMainFrame;

public class MyModel {
    private PIC oPIC;

    private int iModelState = 3;
    private int iActualLine;
    private int iLastLine;

    private ArrayList<Integer> listBreakpoints = new ArrayList<Integer>();

    private long liRuntimeRun;
    private long liRuntimeStep;

    private boolean[] bBreakpoints;

    private WATCHDOG watchdog;
    GUIMainFrame oMainFrame;

    public MyModel() {
        oPIC = new PIC();
    }

    public PIC getPIC() {
        return this.oPIC;
    }

    private int getModelState() {
        return iModelState;
    }

    /**
     * -1 == ERROR, 0 == END, 1 == START, 2 == PAUSE, 3 == RESET
     * @param i
     */
    public void setModelState(int i) {
        iModelState = i;
    }

    public ArrayList<Integer> getBreakpointsList() {
        return listBreakpoints;
    }

    public void setBreakpointsList(ArrayList<Integer> liBreakpoints) {
        listBreakpoints = liBreakpoints;
        bBreakpoints = new boolean[listBreakpoints.size()];
    }

    /**
     * 
     * @return
     */
    public int getActualLine() {
        return iActualLine;
    }

    /**
     * 
     * @return
     */
    public int getLastLine() {
        return iLastLine;
    }

    /**
     * 
     * @return
     */
    public long getRuntimeRun() {
        return liRuntimeRun;
    }

    /**
     * 
     * @return
     */
    public long getRuntimeStep() {
        return liRuntimeStep;
    }

    /**
     * 
     * @return
     */
    public WATCHDOG getWatchdog() {
        return watchdog;
    }

    public void step() {
        //Check if step valid
        if (oPIC.getRam().get_Programcounter() < (oPIC.getEeprom().getLengthEEPROM() - 1)) {
            final long timeStart = System.nanoTime();

            //Makes one step through the eeprom.
            Bitmask oBitmask = new Bitmask();
            oBitmask.bitMaskDecoderAndExecuteCommand(oPIC.getEeprom().getElement(oPIC.getRam().get_Programcounter()), oPIC);

            final long timeEnd = System.nanoTime();

            liRuntimeStep += (timeEnd - timeStart);

        } else {
            System.out.println("Step invalid, end of file reached!");
        }
    }

    public void start() {
        if (getModelState() != 2) { //Do not start again if paused, instead call start to unpause only.
            final long timeStart = System.nanoTime();

            //workWithWatchdog(1, 1);
            //Check if set breakpoint reached or program was stopped or interrupt
            
            while (getModelState() != 0) {
                switch (iModelState) {

                    case 1: { //START
                        step();
                        //
                        //workWithWatchdog(1, 2);
                        final long timeEnd = System.nanoTime();
                        liRuntimeRun += timeEnd - timeStart;
                        oMainFrame.updateWindow();
                    }break;

                    case 2: { //PAUSE resume() has to be called to continue
                        while (iModelState == 2) {}
                    }break;
                }
            }
        } else { //Unpause
            iModelState = 1;
        }
    }

    public void reset() {
        if (getModelState() != 1) {
            oPIC.resetPIC();
            iModelState = 3; //RESET
        }
    }

    public void pause() {
        iModelState = 2;
    }

    public void controlBreakpoint(int iBreakpoint) {
        if (bBreakpoints != null) {
            bBreakpoints[iBreakpoint] = !bBreakpoints[iBreakpoint];
            System.out.println("Breakpoint " + iBreakpoint + " was set to " + bBreakpoints[iBreakpoint]);
        }
    }
}
