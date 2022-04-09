package Model;

import Model.Microcontroller.PIC;

public class MyModelData {
    private PIC oPIC;
    private int iQuartzInterval;
    private int iVisualInterval;
    private boolean bWDTEnabled = false;

    private boolean[] abBreakpoints;

    public PIC getPIC() {
        return oPIC;
    }

    public int getQuartzInterval() {
        return iQuartzInterval;
    }

    public int getVisualInterval() {
        return iVisualInterval;
    }

    public boolean[] getBreakpoints() {
        return abBreakpoints;
    }

    public boolean getWDTEnabled() {
        return bWDTEnabled;
    }

    public void setPIC(PIC pic) {
        this.oPIC = pic;
    }

    public void setQuartzInterval(int iIndex) {
        this.iQuartzInterval = iIndex;
    }

    public void setVisualInterval(int iIndex) {
        this.iVisualInterval = iIndex;
    }

    public void setBreakpoints(boolean[] abBreakpoints) {
        this.abBreakpoints = abBreakpoints;
    }

    public void setWDTEnabled(boolean bWDTEnabled) {
        this.bWDTEnabled = bWDTEnabled;
    }
}
