package Model.BackendCommandProcessing;

import Model.Microcontroller.PIC;

public class InformationConnecter {

    public String connectInformation (PIC oPic, int iActualLine, int iLastLine, int iSetBreakpoint, int iResetBreakpoint, long liRuntime, long liWatchdog) {

        String sConnectedInformation = "";

        for (int i = 0; i < 128; i++) {
            sConnectedInformation.concat("FREG " + i + "," + oPic.getRam().get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(false, i) + "\n");
        }
        int j = 0;
        for (int i = 128; i < 256; i++) {
            sConnectedInformation.concat("FREG " + i + "," + oPic.getRam().get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(true, j) + "\n");
            j++;
        }
        sConnectedInformation.concat("SETLINE " + iActualLine + "\n");
        if (iLastLine != iActualLine)
        {
            sConnectedInformation.concat("RESLINE " + iLastLine + "\n");
        }
        sConnectedInformation.concat("TIME " + (liRuntime / 100) + " µs\n");
        sConnectedInformation.concat("WReg " + oPic.get_WRegister() + "\n");
        sConnectedInformation.concat("PCL " + oPic.getRam().get_PCL() + "\n");
        sConnectedInformation.concat("PCLATH " + oPic.getRam().get_PCLATH() + "\n");
        sConnectedInformation.concat("PCINTERN " + oPic.getRam().get_Programcounter() + "\n");
        sConnectedInformation.concat("STATUS " + oPic.getRam().get_STATUS() + "\n");
        sConnectedInformation.concat("FSR " + oPic.getRam().get_FSR() + "\n");
        sConnectedInformation.concat("OPTION " + 1 + "\n");

        //Statusbits
        int sC = oPic.getRam().get_Carryflag()? 1:0;
        int sDC = oPic.getRam().get_Digitcarryflag()? 1:0;
        int sZ = oPic.getRam().get_Zeroflag()? 1:0;
        int sPD = oPic.getRam().get_PowerDownFlag()? 1:0;
        int sTO = oPic.getRam().get_TimeOutFlag()? 1:0;
        int sRP0 = oPic.getRam().get_RP0Bit()? 1:0;
        int sRP1 = oPic.getRam().get_RP1Bit()? 1:0;
        int sIRP = oPic.getRam().get_Interruptflag()? 1:0;

        //Set Statusbits in frontend.
        sConnectedInformation.concat("STATUSBIT " + 0 + ", " + sC + "\n");
        sConnectedInformation.concat("STATUSBIT " + 1 + ", " + sDC + "\n");
        sConnectedInformation.concat("STATUSBIT " + 2 + ",  " + sZ + "\n");
        sConnectedInformation.concat("STATUSBIT " + 3 + ",  " + sPD + "\n");
        sConnectedInformation.concat("STATUSBIT " + 4 + ",  " + sTO + "\n");
        sConnectedInformation.concat("STATUSBIT " + 5 + ",  " + sRP0 + "\n");
        sConnectedInformation.concat("STATUSBIT " + 6 + ", " + sRP1 + "\n");
        sConnectedInformation.concat("STATUSBIT " + 7 + ", " + sIRP + "\n");

        //Optionbits
        int oPS0 = oPic.getRam().get_PS0()? 1:0;
        int oPS1 = oPic.getRam().get_PS1()? 1:0;
        int oPS2 = oPic.getRam().get_PS2()? 1:0;
        int oPSA = oPic.getRam().get_PSA()? 1:0;
        int oTSE = oPic.getRam().get_T0SE()? 1:0;
        int oTCS = oPic.getRam().get_T0CS()? 1:0;
        int oIEG = oPic.getRam().get_INTEDG()? 1:0;
        int oRPU = oPic.getRam().get_RBPU()? 1:0;

        //Set Optionbits in frontend.
        sConnectedInformation.concat("OPTIONBIT " + 0 + ",   " + oPS0 + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 1 + ",   " + oPS1 + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 2 + ",  " + oPS2 + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 3 + ",  " + oPSA + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 4 + ",  " + oTSE + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 5 + ",  " + oTCS + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 6 + ", " + oIEG + "\n");
        sConnectedInformation.concat("OPTIONBIT " + 7 + ", " + oRPU + "\n");

        //Intconbits
        int iRIF = oPic.getRam().get_RBIF()? 1:0;
        int iIF = oPic.getRam().get_INTF()? 1:0;
        int iTIF = oPic.getRam().get_T0IF()? 1:0;
        int iRIE = oPic.getRam().get_RBIE()? 1:0;
        int iIE = oPic.getRam().get_INTE()? 1:0;
        int iTIE = oPic.getRam().get_T0IE()? 1:0;
        int iEIE = oPic.getRam().get_EEIE()? 1:0;
        int iGIE = oPic.getRam().get_GIE()? 1:0;
        
        //Set Intconbits in frontend.
        sConnectedInformation.concat("INTCONBIT " + 0 + ", " + iRIF + "\n");
        sConnectedInformation.concat("INTCONBIT " + 1 + ", " + iIF + "\n");
        sConnectedInformation.concat("INTCONBIT " + 2 + ", " + iTIF + "\n");
        sConnectedInformation.concat("INTCONBIT " + 3 + ", " + iRIE + "\n");
        sConnectedInformation.concat("INTCONBIT " + 4 + "," + iIE + "\n");
        sConnectedInformation.concat("INTCONBIT " + 5 + ",  " + iTIE + "\n");
        sConnectedInformation.concat("INTCONBIT " + 6 + ", " + iEIE + "\n");
        sConnectedInformation.concat("INTCONBIT " + 7 + ", " + iGIE + "\n");

        //TRISA in frontend is set.
        sConnectedInformation.concat("TRISA " + 0 + "," + ((oPic.getRam().get_TRISA() & 0b00000001) >> 0) + "\n"); //For better understanding
        sConnectedInformation.concat("TRISA " + 1 + "," + ((oPic.getRam().get_TRISA() & 0b00000010) >> 1) + "\n");
        sConnectedInformation.concat("TRISA " + 2 + "," + ((oPic.getRam().get_TRISA() & 0b00000100) >> 2) + "\n");
        sConnectedInformation.concat("TRISA " + 3 + "," + ((oPic.getRam().get_TRISA() & 0b00001000) >> 3) + "\n");
        sConnectedInformation.concat("TRISA " + 4 + "," + ((oPic.getRam().get_TRISA() & 0b00010000) >> 4) + "\n");
        sConnectedInformation.concat("TRISA " + 5 + "," + ((oPic.getRam().get_TRISA() & 0b00100000) >> 5) + "\n");
        sConnectedInformation.concat("TRISA " + 6 + "," + ((oPic.getRam().get_TRISA() & 0b01000000) >> 6) + "\n");
        sConnectedInformation.concat("TRISA " + 7 + "," + ((oPic.getRam().get_TRISA() & 0b10000000) >> 7) + "\n");

        //PORTA in frontend is set.
        sConnectedInformation.concat("PORTA " + 0 + "," + ((oPic.getRam().get_PORTA() & 0b00001) >> 0) + "\n"); //For better understanding
        sConnectedInformation.concat("PORTA " + 1 + "," + ((oPic.getRam().get_PORTA() & 0b00010) >> 1) + "\n");
        sConnectedInformation.concat("PORTA " + 2 + "," + ((oPic.getRam().get_PORTA() & 0b00100) >> 2) + "\n");
        sConnectedInformation.concat("PORTA " + 3 + "," + ((oPic.getRam().get_PORTA() & 0b01000) >> 3) + "\n");
        sConnectedInformation.concat("PORTA " + 4 + "," + ((oPic.getRam().get_PORTA() & 0b10000) >> 4) + "\n");

        //TRISB in frontend is set.
        sConnectedInformation.concat("TRISB " + 0 + "," + ((oPic.getRam().get_TRISB() & 0b00000001) >> 0) + "\n"); //For better understanding
        sConnectedInformation.concat("TRISB " + 1 + "," + ((oPic.getRam().get_TRISB() & 0b00000010) >> 1) + "\n");
        sConnectedInformation.concat("TRISB " + 2 + "," + ((oPic.getRam().get_TRISB() & 0b00000100) >> 2) + "\n");
        sConnectedInformation.concat("TRISB " + 3 + "," + ((oPic.getRam().get_TRISB() & 0b00001000) >> 3) + "\n");
        sConnectedInformation.concat("TRISB " + 4 + "," + ((oPic.getRam().get_TRISB() & 0b00010000) >> 4) + "\n");
        sConnectedInformation.concat("TRISB " + 5 + "," + ((oPic.getRam().get_TRISB() & 0b00100000) >> 5) + "\n");
        sConnectedInformation.concat("TRISB " + 6 + "," + ((oPic.getRam().get_TRISB() & 0b01000000) >> 6) + "\n");
        sConnectedInformation.concat("TRISB " + 7 + "," + ((oPic.getRam().get_TRISB() & 0b10000000) >> 7) + "\n");

        //PORTB in frontend is set.
        sConnectedInformation.concat("PORTB " + 0 + "," + ((oPic.getRam().get_PORTB() & 0b00000001) >> 0) + "\n"); //For better understandin
        sConnectedInformation.concat("PORTB " + 1 + "," + ((oPic.getRam().get_PORTB() & 0b00000010) >> 1) + "\n");
        sConnectedInformation.concat("PORTB " + 2 + "," + ((oPic.getRam().get_PORTB() & 0b00000100) >> 2) + "\n");
        sConnectedInformation.concat("PORTB " + 3 + "," + ((oPic.getRam().get_PORTB() & 0b00001000) >> 3) + "\n");
        sConnectedInformation.concat("PORTB " + 4 + "," + ((oPic.getRam().get_PORTB() & 0b00010000) >> 4) + "\n");
        sConnectedInformation.concat("PORTB " + 5 + "," + ((oPic.getRam().get_PORTB() & 0b00100000) >> 5) + "\n");
        sConnectedInformation.concat("PORTB " + 6 + "," + ((oPic.getRam().get_PORTB() & 0b01000000) >> 6) + "\n");
        sConnectedInformation.concat("PORTB " + 7 + "," + ((oPic.getRam().get_PORTB() & 0b10000000) >> 7) + "\n");

        //STACK in frontend is set.   
        sConnectedInformation.concat("STACK " + oPic.getStack().getSTACK()[0] + "," 
                            + oPic.getStack().getSTACK()[1] + "," 
                            + oPic.getStack().getSTACK()[2] + "," 
                            + oPic.getStack().getSTACK()[3] + "," 
                            + oPic.getStack().getSTACK()[4] + "," 
                            + oPic.getStack().getSTACK()[5] + "," 
                            + oPic.getStack().getSTACK()[6] + "," 
                            + oPic.getStack().getSTACK()[7] + "\n");

        //TIMER0
        sConnectedInformation.concat("TIMER0 " + oPic.getRam().get_TMR0() + "\n");

        //Watchdog in frontend is set. (Doesn't work correctly since watchdog isn't implemented correctly) TODO
        sConnectedInformation.concat("WATCHDOG " + liWatchdog + "\n");
        sConnectedInformation.concat("PRESCALER " + 1 + ":" + oPic.getRam().get_TMR0_PrescalerRate() + "\n");

        if (iSetBreakpoint > -1)
            sConnectedInformation.concat("SETBREAK " + iSetBreakpoint + "\n");

        if (iResetBreakpoint > -1)    
            sConnectedInformation.concat("RESBREAK " + iResetBreakpoint + "\n");
        else if (iResetBreakpoint == -2) {
            sConnectedInformation.concat("RESALLBREAK"); //TODO \n if new string concated
        }

        return sConnectedInformation;
    }
}
