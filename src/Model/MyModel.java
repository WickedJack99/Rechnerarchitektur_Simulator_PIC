package Model;

import java.util.concurrent.ConcurrentLinkedQueue;

import Model.Microcontroller.INSTRUCTIONDECODER;
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
                /* -1 == ERROR, 0 == END, 1 == START, 2 == PAUSE, 3 == RESET 4 == STEP*/
                iProgState = qReceivedCommands.poll();
                switch (iProgState) {
                    //Stop program because of error
                    case (-1): {                        
                        System.out.println("Fehler");
                        bStopProgram = true;
                    }break;
                    //Stop Program
                    case (0): {                    
                        System.out.println("Programm wurde beendet.");
                        bStopProgram = true;
                    }break;
                    //Start program
                    case (1): {                        
                        while (iProgState == 1) {
                            //Check if pause/stop was pressed
                            if (!qReceivedCommands.isEmpty()) {
                                iProgState = qReceivedCommands.poll();
                            }
                            //Check if interrupt was acknowledged
                            if (oPIC.interruptAcknowledged()) {
                                //Execute ISR
                                oPIC.InterruptServiceRoutine();
                            } else {
                                //Check if breakpoints initialized
                                if (abBreakpoints != null) {
                                    //Check if stack overflowed or underflowed
                                    if (oPIC.getStack().getStackOverflow() || oPIC.getStack().getStackUnderflow()) {
                                        iProgState = 2;
                                        qDataToView.add(oPIC);
                                    //If stack did not over or underflow
                                    } else {
                                        //Check if breakpoint at [pc] is set
                                        if (!abBreakpoints[oPIC.getRam().get_Programcounter()]) {
                                            //Check if slow interval is active
                                            if(iVisualInterval > 0) {                                    
                                                try {
                                                    Thread.sleep(iVisualInterval * 1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            //Execute step
                                            step();
                                            //Update view
                                            qDataToView.add(oPIC);
                                        } else {
                                            //Pause program if breakpoint at [pc] is set
                                            iProgState = 2;
                                        }
                                    }                                    
                                } 
                            }                                                       
                        }
                    }break;
                    //Program paused
                    case (2): {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }break;
                    //Reset program
                    case (3): {
                        oPIC.resetPIC();
                        qDataToView.add(oPIC);
                    }break;
                    //Step program
                    case (4): {
                        //Check if breakpoints are initialized
                        if (abBreakpoints != null) {
                            //Check if breakpoint is set
                            int iProgC = oPIC.getRam().get_Programcounter();
                            iProgC &= abBreakpoints.length;
                            if (!abBreakpoints[iProgC]) {
                                step();
                                qDataToView.add(oPIC);
                            }
                        } else {
                            System.out.println("Please load file!");
                        }
                    }break;
                    //Reset Stack Overflow Bit
                    case (5): {
                        oPIC.getStack().resetStackOverflow();
                    }break;
                    //Reset Stack Underflow Bit
                    case (6): {
                        oPIC.getStack().resetStackUnderflow();
                    }break;
                }
            }            
        }
    }

    private void step() {
        INSTRUCTIONDECODER oBitmask = new INSTRUCTIONDECODER();
        //Makes one step through the eeprom.
        oBitmask.decodeAndExecuteCommand(oPIC.getEeprom().getElement(oPIC.getRam().get_Programcounter()), oPIC);
        //Update view
        qDataToView.add(oPIC);
    }

    private void setModel(MyModelData data) {
        oMyModelData = data;
        oPIC = oMyModelData.getPIC();
        abBreakpoints = oMyModelData.getBreakpoints();
        oPIC.getRuntimer().setQuarzSpeed(oMyModelData.getQuartzInterval());
        iVisualInterval = oMyModelData.getVisualInterval();
        qDataToView.add(oPIC);
    }
}
