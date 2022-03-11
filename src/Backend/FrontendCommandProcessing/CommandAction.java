package Backend.FrontendCommandProcessing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import Backend.BackendCommandProcessing.InformationConnecter;
import Backend.EepromLoader.ReadEepromFile;
import Backend.Runtime.Environment;
import Backend.Microcontroller.PIC;

public class CommandAction {

    int iAction;
    String sValue;
    Environment env;

    //List with methods
    private List<Callable> actions = new ArrayList<>();

    private InformationConnecter informationConnecter = new InformationConnecter();

    public CommandAction(int iAction, String sValue, Environment env) {
        this.iAction = iAction;
        this.sValue = sValue;
        this.env = env;

        actions.add(callLoadFile);//0
        actions.add(callSaveProgrammState);//1
        actions.add(callSetBreakpoint);//2
        actions.add(callTogglePortA);//3
        actions.add(callTogglePortB);//4
        actions.add(callChangeQuarz);//5
        actions.add(callToggleWatchdog);//6
        actions.add(callReset);//7
        actions.add(callStep);//8
        actions.add(callStartProgramm);//9
        actions.add(callStopProgramm);//10
        actions.add(callLoadProgrammState);//11
        actions.add(callRemoveBreakpoint);//12
        actions.add(callRemoveAllBreakpoints);//13

        actions.add(callErrorReadFailed);//14
    }

    public int execute() {
        int iReturn = 0;
        if (iAction > -1) {
            try {
                iReturn = (int)actions.get(iAction).call();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return iReturn;
    }

    Callable callLoadFile = new Callable() {
        @Override
        public Object call() {
            return loadFile();
        }
    };

    Callable callSaveProgrammState = new Callable() {
        @Override
        public Object call() {
            return saveProgrammState();
        }
    };

    Callable callSetBreakpoint = new Callable() {
        @Override
        public Object call() {
            return setBreakpoint();
        }
    };

    Callable callTogglePortA = new Callable() {
        @Override
        public Object call() {
            return togglePortA();
        }
    };

    Callable callTogglePortB = new Callable() {
        @Override
        public Object call() {
            return togglePortB();
        }
    };

    Callable callChangeQuarz = new Callable() {
        @Override
        public Object call() {
            return changeQuarz();
        }
    };

    Callable callToggleWatchdog = new Callable() {
        @Override
        public Object call() {
            return toggleWatchdog();
        }
    };

    Callable callReset = new Callable() {
        @Override
        public Object call() {
            return reset();
        }
    };

    Callable callStep = new Callable() {
        @Override
        public Object call() {
            return step();
        }
    };

    Callable callStartProgramm = new Callable() {
        @Override
        public Object call() {
            return startProgramm();
        }
    };

    Callable callStopProgramm = new Callable() {
        @Override
        public Object call() {
            return stopProgramm();
        }
    };

    Callable callLoadProgrammState = new Callable() {
        @Override
        public Object call() {
            return loadProgrammState();
        }
    };

    Callable callRemoveBreakpoint = new Callable() {
        @Override
        public Object call() {
            return removeBreakpoint();
        }
    };

    Callable callRemoveAllBreakpoints = new Callable() {
        @Override
        public Object call() {
            return removeAllBreakpoints();
        }
    };

    Callable callErrorReadFailed = new Callable() {
        @Override
        public Object call() {
            return readFailed();
        }
    };

    //Read file out of filepath and write it to EEPROM of oPIC.
    private int loadFile() {
        ReadEepromFile readEepromFile = new ReadEepromFile();
        env.getPIC().resetPIC();
        readEepromFile.readFileAndWriteToEEPROM(new File(sValue), env.getPIC());
        //env.getMainToFrontendQueue().add(informationConnecter.connectInformation(env.getPIC(), env.getActualLine(), env.getLastLine(), -2, -2, env.getRuntime(), env.getWatchdog().getTime()));
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int saveProgrammState() {
        //TODO Logic
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int setBreakpoint() {
        env.changeListBreakpoints(Integer.parseInt(sValue), true);
        //env.getMainToFrontendQueue().add(informationConnecter.connectInformation(env.getPIC(), env.getActualLine(), env.getLastLine(), Integer.parseInt(sValue), -2, env.getRuntime(), env.getWatchdog().getTime()));
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int togglePortA() {
        int iBit = Integer.parseInt(sValue.substring(0,1));
        int iValue = Integer.parseInt(sValue.substring(2, 3));
        env.getPIC().getRam().set_PORTA_Bit_X_To_Y(iBit, iValue);
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int togglePortB() {
        int iBit = Integer.parseInt(sValue.substring(0,1));
        int iValue = Integer.parseInt(sValue.substring(2, 3));
        env.getPIC().getRam().set_PORTB_Bit_X_To_Y(iBit, iValue);
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int changeQuarz() {
        //XTA(L) not implemented
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int toggleWatchdog() {
        /**
         * //WAT(CHDOG)
            if (sFileFrontendToBackendCommand.substring(9, 11).equals("ON"))
            {
                //ON
                liWorkWithWatchdog(1, 5);
            }

            else
            {
                //OFF
                liWorkWithWatchdog(1, 4);
            }
         */
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int reset() {
        /**
         * //RES(ET)
            liDifferenceTimeNano = 0;
            oPIC.resetPIC();
            //oPIC.Ram.set_OPTION(255);
            //resetLines();
            bCreateGUIFile = true;
         */
        env.getPIC().resetPIC();
        env.getPIC().getRam().set_OPTION(255);
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int step() {
        /**
         * //STE(P)
            //System.out.println("STEP " + oPIC.Ram.get_Programcounter());
            if (oPIC.Ram.get_Programcounter() < (oPIC.Eeprom.getLengthEEPROM() - 1))
            {
                final long liTimeStart = System.nanoTime();

                //Makes one step through the eeprom.
                bitMaskDecisionMaker(oPIC.Eeprom.getElement(oPIC.Ram.get_Programcounter()), oPIC);

                final long timeEnd = System.nanoTime();

                liDifferenceTimeNano += (timeEnd - liTimeStart);

                if (!oPIC.Ram.get_T0CS())
                {
                    oPIC.Ram.increment_TMR0();
                }

                bCreateGUIFile = true;
         */
        env.step();//step in env or set/gets
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int startProgramm() {
        /**
         * //STA(RT)

            final long liTimeStart = System.nanoTime();

            liWorkWithWatchdog(1, 1);

            if ((iAccessBreakpoint(3, 0) > (-1)) && (iAccessBreakpoint(3, 0) <= (oPIC.Eeprom.getLengthEEPROM() - 1)))
            {
                while ((oPIC.Ram.get_Programcounter() < iAccessBreakpoint(3, 0)) && (cMenueSelection != 'p'))
                {
                    //System.out.println("STEP " + oPIC.Ram.get_Programcounter());
                    bitMaskDecisionMaker(oPIC.Eeprom.getElement(oPIC.Ram.get_Programcounter()), oPIC);
    
                    if (!oPIC.Ram.get_T0CS())
                    {
                        oPIC.Ram.increment_TMR0();
                    }
                    
                    bCreateGUIFile = true;
                }
                iAccessBreakpoint(2, 0);
                bCreateGUIFile = true;
                System.out.println(iAccessBreakpoint(3, 0));
            }

            else
            {
                while ((oPIC.Ram.get_Programcounter() < ((oPIC.Eeprom.getLengthEEPROM() - 1))) && (cMenueSelection != 'p'))
                {
                    //System.out.println("STEP " + oPIC.Ram.get_Programcounter());
                    bitMaskDecisionMaker(oPIC.Eeprom.getElement(oPIC.Ram.get_Programcounter()), oPIC);

                    if (!oPIC.Ram.get_T0CS())
                    {
                        oPIC.Ram.increment_TMR0();
                    }

                    bCreateGUIFile = true;
                }
            }
            
            liWorkWithWatchdog(1, 2);
            final long timeEnd = System.nanoTime();

            liDifferenceTimeNano = timeEnd - liTimeStart;

            bCreateGUIFile = true;
         */
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int stopProgramm() {
        /**
         * //STO(PP)
            //watchdogThread.setWatchdogStatus(3);
            cMenueSelection = 'p';
         */
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int loadProgrammState() {
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int removeBreakpoint() {
        env.changeListBreakpoints(Integer.parseInt(sValue), false);
        //TODO sent message to thread, to change value in file
        return 0;
    }

    private int removeAllBreakpoints() {
        env.changeListBreakpoints(-1, false);
        return 0;
    }

    private int readFailed() {
        System.out.println("Fehler: Lesen der Datei fehlgeschlagen.");
        return 0;
    }

    /**
     * //PRT( Scale) ? Prescaler is set from additional frontend.
            try
            {
                if (sFileFrontendToBackendCommand.substring(4, 5) != null)
                {
                    oPIC.Ram.set_OPTION((oPIC.Ram.get_OPTION() & 11111000) | (Integer.parseInt(sFileFrontendToBackendCommand.substring(4, 5))));
                    bCreateGUIFile = true;
                }
            }
            catch (StringIndexOutOfBoundsException e)
            {}
     */

    /**
     * //PRW( Scale) ? Prescaler is set from additional frontend.
            try
            {
                if (sFileFrontendToBackendCommand.substring(4, 5) != null)
                {
                    oPIC.Ram.set_OPTION((oPIC.Ram.get_OPTION() & 11111000) | (Integer.parseInt(sFileFrontendToBackendCommand.substring(4, 5))));
                    bCreateGUIFile = true;
                }
                if (sFileFrontendToBackendCommand.substring(5, 6) != null)
                {
                    oPIC.Ram.set_OPTION((oPIC.Ram.get_OPTION() & 11111000) | (Integer.parseInt(sFileFrontendToBackendCommand.substring(4, 6))));
                    bCreateGUIFile = true;
                }
                if (sFileFrontendToBackendCommand.substring(6, 7) != null)
                {
                    oPIC.Ram.set_OPTION((oPIC.Ram.get_OPTION() & 11111000) | (Integer.parseInt(sFileFrontendToBackendCommand.substring(4, 7))));
                    bCreateGUIFile = true;
                }
            }
            catch (StringIndexOutOfBoundsException e)
            {}
     */
}