package Model.Microcontroller;

public class RUNTIMER {
    private double dRuntime;
    private double dMaxWatchdog;
    private double dRTIncrVal;

    private boolean WDTE = false;

    private RAM oRam;

    public RUNTIMER(RAM oRam) {
        this.oRam = oRam;
    }

    public void setMaxWatchdog(double dMaxVal) {
        dMaxWatchdog = dMaxVal;
    }

    private void checkWatchdog() {
        if (WDTE) {
            if (oRam.get_PSA()) {
                setMaxWatchdog(18000 * oRam.get_WDT_PrescalerRate());
            } else {
                setMaxWatchdog(18000);//18 ms
            }
            if ((dMaxWatchdog - dRuntime) <= 0) {
                System.out.println("WDT Interrupt");
            }
        }
    }

    public double getRuntime() {
        return dRuntime;
    }

    public void setRuntime(double liVal) {
        dRuntime = liVal;
    }

    public void incrementRuntime() {
        dRuntime += dRTIncrVal;
        checkWatchdog();
    }

    public void setQuarzSpeed(int iInterval) {
        switch (iInterval) {
            //10 {"32 kHz", "100 kHz", "500 kHz", "1 MHz", "2 MHz", "4 MHz", "8 MHz", "12 MHz", "16 MHz", "20 MHz"}
            case 0: {
                //32 kHz => 125 mikrosekunden = 0.125ms
                dRTIncrVal = 0.001 * (4 / 0.032);
            }break;

            case 1: {
                //100kHz => 40 mikrosekunden = 0.040 ms
                dRTIncrVal = 0.001 * (4 / 0.1);
            }break;

            case 2: {
                //500kHz => 8 mikrosekunden = 0.008 ms
                dRTIncrVal = 0.001 * (4 / 0.5);
            }break;

            case 3: {
                //1MHz => 4 mikrosekunden = 0.004 ms
                dRTIncrVal = 0.001 * (4 / 1);
            }break;

            case 4: {
                //2MHz => 2 mikrosekunden = 0.002 ms
                dRTIncrVal = 0.001 * (4 / 2);
            }break;

            case 5: {
                //4MHz => 1 mikrosekunde = 0.001 ms
                dRTIncrVal = 0.001 * (4 / 4);
            }break;

            case 6: {
                //8MHz => 0.5 mikrosekunden = 0.0005 ms
                dRTIncrVal = 0.001 * (4 / 8); 
            }break;

            case 7: {
                //12MHz => 0.33 mikrosekunden = 0.0003 ms
                dRTIncrVal = 0.001 * (4 / 12);
            }break;

            case 8: {
                //16MHz => 0.25 mikrosekunden = 0.00025 ms
                dRTIncrVal = 0.001 * (4 / 16);
            }break;

            case 9: {
                //20MHz => 0.20 mikrosekunden = 0.00020 ms
                dRTIncrVal = 0.001 * (4 / 20);
            }break;
        }
    }
}