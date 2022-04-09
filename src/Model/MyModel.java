package Model;

import java.util.concurrent.ConcurrentLinkedQueue;

import Model.Microcontroller.Bitmask;
import Model.Microcontroller.PIC;

public class MyModel extends Thread {
    PIC oPIC = new PIC();
    
    MyModelData oMyModelData;

    boolean bStopProgram = false;

    int iProgState = 0;
    int iVisualInterval = 0;

    double dRTIncrVal = 0;

    private boolean[] abBreakpoints;

    ConcurrentLinkedQueue<Integer> qReceivedCommands;
    ConcurrentLinkedQueue<MyModelData> qDataToModel;
    ConcurrentLinkedQueue<PIC> qDataToView;

    public MyModel(ConcurrentLinkedQueue<Integer> qCommands, ConcurrentLinkedQueue<PIC> qDataS, ConcurrentLinkedQueue<MyModelData> qDataR) {
        qReceivedCommands = qCommands;
        qDataToView = qDataS;
        qDataToModel = qDataR;
    }

    @Override
    public void run() {
        //Check if end or error
        while (!bStopProgram) {
            //Check if element to set model
            while (!qDataToModel.isEmpty()) {
                setModel(qDataToModel.poll());
            }
            //Check if element at command-queue
            if (!qReceivedCommands.isEmpty()) {
                /* -1 == ERROR, 0 == END, 1 == START, 2 == PAUSE, 3 == RESET*/
                iProgState = qReceivedCommands.poll();
                switch (iProgState) {
                    case (-1): {
                        System.out.println("Fehler");
                        bStopProgram = true;
                    }break;
                    case (0): {
                        System.out.println("Programm wurde beendet.");
                        bStopProgram = true;
                    }break;
                    case (1): {
                        //start program
                        while (iProgState == 1) {
                            //Check if pause/stop was pressed
                            if (!qReceivedCommands.isEmpty()) {
                                iProgState = qReceivedCommands.poll();
                            }
                            //Check if breakpoint is set
                            if (abBreakpoints != null) {
                                if (!abBreakpoints[oPIC.getRam().get_Programcounter()]) {
                                    if(iVisualInterval > 0) {                                    
                                        try {
                                            Thread.sleep(iVisualInterval * 1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    step();
                                    qDataToView.add(oPIC);
                                } else {
                                    //Pause program
                                    iProgState = 2;
                                }
                            }                            
                        }
                    }break;
                    case (2): {

                    }break;
                    case (3): {
                        oPIC.resetPIC();
                        qDataToView.add(oPIC); //TODO
                    }break;
                    case (4): {
                        if (abBreakpoints != null) {
                            //Check if breakpoint is set
                            if (!abBreakpoints[oPIC.getRam().get_Programcounter()]) {
                                step();
                                qDataToView.add(oPIC); //TODO
                            }
                        } else {
                            System.out.println("Please load file!");
                        }
                    }break;
                }
            }

            
        }
    }

    private void step() {
        //Check if step valid
        if (oPIC.getRam().get_Programcounter() < (oPIC.getEeprom().getLengthEEPROM())) {
            //Makes one step through the eeprom.
            Bitmask oBitmask = new Bitmask();
            oBitmask.bitMaskDecoderAndExecuteCommand(oPIC.getEeprom().getElement(oPIC.getRam().get_Programcounter()), oPIC);
            qDataToView.add(oPIC);
        } else {
            System.out.println("Step invalid, end of file reached!");
        }
    }

    private void setQuarzSpeed(int iInterval) { //TODO Quartz
        switch (iInterval) {
            //10 {"32 kHz", "100 kHz", "500 kHz", "1 MHz", "2 MHz", "4 MHz", "8 MHz", "12 MHz", "16 MHz", "20 MHz"}
            case 0: {
                //32 kHz => 
                dRTIncrVal = (4 / 0.032);
            }break;

            case 1: {
                //100kHz => 0.000010s
                dRTIncrVal = (4 / 0.1);
            }break;

            case 2: {
                //500kHz => 0.0000020s
                dRTIncrVal = (4 / 0.5);
            }break;

            case 3: {
                //1MHz => 0.0000010s
                dRTIncrVal = (4 / 1);
            }break;

            case 4: {
                //2MHz => 0.0000005s
                dRTIncrVal = (4 / 2);
            }break;

            case 5: {
                //4MHz => 0.00000025s 1 mükrosekunde
                dRTIncrVal = (4 / 4);
            }break;

            case 6: {
                //8MHz => 0.000000125s
                dRTIncrVal = (4 / 8);
            }break;

            case 7: {
                //12MHz => 0.00000008333333
                dRTIncrVal = (4 / 12);
            }break;

            case 8: {
                //16MHz => 0.0000000625
                dRTIncrVal = (4 / 16);
            }break;

            case 9: {
                //20MHz => 0.00000005
                dRTIncrVal = (4 / 20);
            }break;
        }
    }

    private void setModel(MyModelData data) {
        oMyModelData = data;
        oPIC = oMyModelData.getPIC();
        abBreakpoints = oMyModelData.getBreakpoints();
        setQuarzSpeed(oMyModelData.getQuartzInterval());
        iVisualInterval = oMyModelData.getVisualInterval();
    }
}
