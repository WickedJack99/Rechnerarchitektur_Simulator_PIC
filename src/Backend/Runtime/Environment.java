/**
 * @author Aaron Moser
 * @date 21.02.2022
 * @lastchange 21.02.2022 
 */

package Backend.Runtime;

import java.util.ArrayList;

import Backend.Microcontroller.PIC;
import Backend.Microcontroller.WATCHDOG;
import Frontend.PIC_SIMULATOR_GUI_JAVA.GUIMainFrame;

public class Environment {

    private PIC oPIC;

    private String sEepromDataFile;
    private String sActualCommand;

    private int iEnvironmentState;
    private int iActualLine;
    private int iLastLine;

    private ArrayList<Integer> listBreakpoints = new ArrayList<Integer>();

    private long liRuntime;

    private WATCHDOG watchdog;
    
    public Environment() {

        oPIC = new PIC();
        oPIC.setWRegister(4);
        GUIMainFrame oMainFrame = new GUIMainFrame(this);

        watchdog = new WATCHDOG();



        

        sEepromDataFile = "";
        sActualCommand = "";

        iEnvironmentState = 0;

        while (iEnvironmentState > -1) {
        
            // loadfile command readEepromFile.readFileAndWriteToEEPROM(new File(sEepromDataFile), oPIC);
        }
    }

    public PIC getPIC() {
        return oPIC;
    }

    public ArrayList<Integer> getBreakpoints() {
        return listBreakpoints;
    }

    /**
     * 
     * @param iBreakpoint
     * @param bState
     */
    public void changeListBreakpoints(int iBreakpoint, boolean bState) {
        if (iBreakpoint > -1) {
            if (bState) {
                //add new breakpoint to list
                boolean bExists = false;
                for (int i = 0; i < listBreakpoints.size(); i++) {
                    if (listBreakpoints.get(i) == iBreakpoint)
                        bExists = true;
                }
                if (!bExists) {
                    listBreakpoints.add(iBreakpoint);
                }
            } else {
                //remove breakpoint from list
                int iIndex = -1;
                for (int i = 0; i < listBreakpoints.size(); i++) {
                    if (listBreakpoints.get(i) == iBreakpoint) {
                        iIndex = i;
                        i = listBreakpoints.size();
                    }  
                }
                if (iIndex > -1) {
                    listBreakpoints.remove(iIndex);
                }
            }
        } else {
            //remove all breakpoints from list
            while (listBreakpoints.size() > 0) {
                listBreakpoints.remove(0);
            }
        }
        
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
    public long getRuntime() {
        return liRuntime;
    }

    /**
     * 
     * @return
     */
    public WATCHDOG getWatchdog() {
        return watchdog;
    }

    public void step() {
        
    }
}

