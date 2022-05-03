package Model.Microcontroller;

/**
 * @author Aaron Moser
 * @date 23.06.2021
 */

/**
 * Random Access Memory of the PIC (Datenspeicher)
 * RAM has two banks.
 * Each bank has 128 bytes.
 * The first twelve locations of each bank are reserved for the special function registers.
 * 
 * Page 13 datasheet parts of RAM.
 */
public class Ram {
    //Bank0 of the PIC-RAM
    private int[] bank0;

    //Bank1 of the PIC-RAM
    private int[] bank1;

    private int iProgramcounter = 0;

    //Last programmcounter for function getLastLine in main.
    private int lastProgramcounter = -1;

    //
    private int iPrescaledTMR0;

    /**
     * Constructor of RAM
     * Initializes two banks as two int arrays of size 128.
     */
    public Ram() {
        //Bank0 of the PIC-RAM
        bank0 = new int[128];

        //Bank1 of the PIC-RAM
        bank1 = new int[128];
    }

    /**
     * @param rP0Bit shows if data is at bank 0 or 1.
     * @param adress represents the adress of where the data is stored.
     * @return the value of the element at the adress of the bank.
     * @throws Exception
     */
    public synchronized int get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(boolean rP0Bit, int adress) {
        int valueOfBankRP0BitOfElementAtAdress = 0;

        if (rP0Bit == false) {
            if (adress >= 1 && adress < 128) {
                valueOfBankRP0BitOfElementAtAdress = bank0[adress];     
            }

            else {
                valueOfBankRP0BitOfElementAtAdress = bank0[bank0[4]];
            }
        }

        if (rP0Bit == true) {
            if (adress >= 0 && adress < 128) {
                valueOfBankRP0BitOfElementAtAdress = bank1[adress];     
            } else {
                valueOfBankRP0BitOfElementAtAdress = bank1[bank1[4]];
            }
        }

        return valueOfBankRP0BitOfElementAtAdress;
    }

    /**
     * Sets bank x to values of overhanded array.
     * @param x shows if bank 0 or 1 will be set.
     * @param array an array which will represent bank x.
     */
    public synchronized void set_Bank_X(boolean rp0Bit, int[] array) {
        if (rp0Bit == false) {
            bank0 = array;
        }

        if (rp0Bit == true) {
            bank1 = array;
        }
    }

    /**
     * Sets element x of bank y to z.
     * @param element between 0 and 127. (x)
     * @param rp0Bit bank where the value will be changed. (y)
     * @param value that will be written into the element. (z)
     */
    public synchronized void set_Element_X_Of_Bank_Y_To_Z(int element, boolean rp0Bit, int value, boolean bStatusAffected) {
        if (element < 0 || element > 127) {
            System.out.println("Wrong input, value from 0 to 127 expected!");
        } else {

            if (element >= 12 && element < 128) {
                if (rp0Bit == false) {
                    bank0[element] = value;
                }
    
                if (rp0Bit == true) {
                    bank1[element] = value;
                }
            } else {
                //Bank0 Registers
                if (rp0Bit == false) {
                    switch (element) {
                        case 0: {
                            bank0[bank0[4]] = value;
                        }break;
                        case 1: {
                            set_TMR0(value);
                        }break;
                        case 2: {
                            set_PCL(value);
                            set_Programcounter(value + (get_PCLATH() << 8));
                        }break;
                        case 3: {
                            if (bStatusAffected) {
                                int iStatus_Lower = get_STATUS() & 0b111;
                                int iSTATUS_Upper = value & 0b11111000;
                                int iNewSTATUS = iSTATUS_Upper | iStatus_Lower;
                                set_STATUS(iNewSTATUS);
                            } else {
                                set_STATUS(value);
                            }                                                        
                        }break;
                        case 4: {
                            set_FSR(value);
                        }break;
                        case 5: {
                            set_PORTA(value);
                        }break;
                        case 6: {
                            set_PORTB(value);
                        }break;
                        case 7: {
                            bank0[7] = value;
                        }break;
                        case 8: {
                            set_EEDATA(value);
                        }break;
                        case 9: {
                            set_EEADR(value);
                        }break;
                        case 10: {
                            set_PCLATH(value);
                        }break;
                        case 11: {
                            set_INTCON(value);
                        }break;
                    }
                }
                //Bank1 Registers
                else {
                    switch (element) {
                        case 0: {
                            bank1[bank0[4]] = value;
                        }break;
                        case 1: {
                            set_OPTION(value);
                        }break;
                        case 2: {
                            bank1[2] = value;
                        }break;
                        case 3: {
                            if (bStatusAffected) {
                                int iStatus_Lower = get_STATUS() & 0b111;
                                int iSTATUS_Upper = value & 0b11111000;
                                int iNewSTATUS = iSTATUS_Upper | iStatus_Lower;
                                set_STATUS(iNewSTATUS);
                            } else {
                                set_STATUS(value);
                            } 
                        }break;
                        case 4: {
                            bank1[4] = value;
                        }break;
                        case 5: {
                            set_TRISA(value);
                        }break;
                        case 6: {
                            set_TRISB(value);
                        }break;
                        case 7: {
                            set_EECON1(value);
                        }break;
                        case 8: {
                            set_EECON2(value);
                        }break;
                        case 9: {
                            bank1[9] = value;
                        }break;
                        case 10: {
                            bank1[10] = value;
                        }break;
                        case 11: {
                            bank1[11] = value;
                        }break;
                    }
                }            
            }
        }
    }

    //Bank0 Registers

    /**
     * Set TMR0 to value & 255.
     * @param value to set TMR0 to.
     */
    public synchronized void set_TMR0(int value) {
        iPrescaledTMR0 = 0;
        value &= 255;
        bank0[1] = value;
    }

    /**
     * Increment TMR0 by 1 if PSA == 1 or by prescaler rate if PSA == 0.
     */
    public synchronized void increment_TMR0() {
        int iValTMR0 = get_TMR0();
        int iValPrescaler;

        boolean bPSA = get_PSA(); //PreScalerAssignment

        //Check assignment of prescaler (PSA == 0) => TMR0; (PSA == 1) => WDT
        //Prescaler assigned to WDT, icrement TMR0 by 1.
        if (bPSA) {
            //Increment TMR0 by 1, set T0IF at overflow.
            if ((iValTMR0 + 1) > 255) {
                set_T0IF(true);
                bank0[1] = (iValTMR0 + 1) & 255;
            } else {
                bank0[1] = (iValTMR0 + 1) & 255;
            }
            //Prescaler assigned to TMR0, increment TMR0 if prescaled TMR0 equals TMR0 prescaler-rate.
        } else {
            iValPrescaler = get_TMR0_PrescalerRate();
            iPrescaledTMR0++;
            if (iPrescaledTMR0 == iValPrescaler) {
                iPrescaledTMR0 = 0; //Reset prescaled TMR0
                //Increment TMR0 by 1, set T0IF at overflow.
                if ((iValTMR0 + 1) > 255) {
                    set_T0IF(true);
                    bank0[1] = (iValTMR0 + 1) & 255;
                } else {
                    bank0[1] = (iValTMR0 + 1) & 255;
                }
            }
        }
    }

    public synchronized int get_TMR0() {
        return bank0[1];
    }

    public synchronized void set_PCL(int value) {
        bank0[2] = value;
        bank1[2] = value;
    }

    public synchronized int get_PCL() {
        return bank0[2];
    }

    public synchronized void set_STATUS(int value) {
        bank0[3] = value;
        bank1[3] = value;
    }

    public synchronized int get_STATUS() {
        return bank0[3];
    }

    /**
     * Sets file-search-register to value.
     * @param value file-search-register is set to.
     */
    public synchronized void set_FSR(int value) {
        bank0[4] = value;
        bank1[4] = value;
    }

    /**
     * @returns value of file search register.
     */
    public synchronized int get_FSR() {
        return bank0[4];
    }

    /**
     * Sets PORT A and checks whether TMR0 has to be incremented or not.
     * @param value
     */
    public synchronized void set_PORTA(int value) {
        if (get_T0CS()) {
            //Check if PORT A RA4 was set/cleared
            boolean bActualRA4 = get_RA4_T0CKI();
            boolean bNewRA4 = ((value & 0b00010000) == 0b00010000);

            //Increment TMR0 if RA4 was set/cleared
            if (bActualRA4 != bNewRA4) {
                if (bNewRA4) { //rising edge
                    if (!get_T0SE()) { //rising edge increments TMR0
                        increment_TMR0();
                    }
                } else { //falling edge
                    if (get_T0SE()) { //falling edge increments TMR0
                        increment_TMR0();
                    }
                }
            }
        }
        
        //Set PORT A
        bank0[5] = value;
    }

    public synchronized void set_PORTA_Bit_X_To_Y(int x, int y) {
        
        //Increment TMR0 if RA4 was set/cleared
        if ((x == 4)) {
            //Check T0CS
            if (get_T0CS()) {
                boolean bActualRA4 = get_RA4_T0CKI();
                boolean bNewRA4 = false;
                if (y == 1)
                    bNewRA4 = true;
                
                if (bActualRA4 != bNewRA4) {
                    if (bNewRA4) { //rising edge
                        if (!get_T0SE()) { //rising edge increments TMR0
                            increment_TMR0();
                        }
                    } else { //falling edge
                        if (get_T0SE()) { //falling edge increments TMR0
                            increment_TMR0();
                        }
                    }
                }
            }
        }

        //Clear bit x
        if (y == 0) {
            int[] array = new int[8];
            array[0] = 0b11111110;
            array[1] = 0b11111101;
            array[2] = 0b11111011;
            array[3] = 0b11110111;
            array[4] = 0b11101111;
            array[5] = 0b11011111;
            array[6] = 0b10111111;
            array[7] = 0b01111111;

            bank0[5] &= array[x];
        }

        //if (y == 1) set bit x
        else {
            bank0[5] |= ((0b00000001) << x);
        }
    }

    /**
     * @returns value of PORT A.
     */
    public synchronized int get_PORTA() {
        return bank0[5];
    }

    /**
     * Sets PORT B to value.
     * @param value to set PORT B to.
     */
    public synchronized void set_PORTB(int value) {
        bank0[6] = value;
    }

    /**
     * Sets PORT B bit x to y. (y == 0) => false; (y == 1) => true
     * @param x indicates the position of the bit which will be set/cleared.
     * @param y indicates whether the bit will be set/cleared.
     */
    public synchronized void set_PORTB_Bit_X_To_Y(int x, int y) {
        //Clear bit x
        if (y == 0) {
            int[] array = new int[8];
            array[0] = 0b11111110;
            array[1] = 0b11111101;
            array[2] = 0b11111011;
            array[3] = 0b11110111;
            array[4] = 0b11101111;
            array[5] = 0b11011111;
            array[6] = 0b10111111;
            array[7] = 0b01111111;

            bank0[6] &= array[x];
        }

        //if (y == 1) set bit x
        else {
            bank0[6] |= ((0b00000001) << x);
        }
    }

    /**
     * @returns value of PORT B.
     */
    public synchronized int get_PORTB() {
        return bank0[6];
    }

    /**
     * Sets EEDATA to value.
     * @param value to set EEDATA to.
     */
    public synchronized void set_EEDATA(int value) {
        bank0[8] = value;
    }

    /**
     * @returns value of EEDATA.
     */
    public synchronized int get_EEDATA() {
        return bank0[8];
    }

    /**
     * Sets EEADR to value.
     * @param value to set EEADR to.
     */
    public synchronized void set_EEADR(int value) {
        bank0[9] = value;
    }

    public synchronized int get_EEADR() {
        return bank0[9];
    }

    public synchronized void set_PCLATH(int value) {
        bank0[10] = value;
        bank1[10] = value;
    }

    public synchronized int get_PCLATH() {
        return bank0[10];
    }

    public synchronized void set_INTCON(int value) {
        bank0[11] = value;
        bank1[11] = value;
    }

    public synchronized int get_INTCON() {
        return bank0[11];
    }

    //Bank1 Registers
    public synchronized void set_OPTION(int value) {
        bank1[1] = value;
    }

    public synchronized int get_OPTION() {
        return bank1[1];
    }

    public synchronized void set_TRISA(int value) {
        bank1[5] = value;
    }

    public synchronized int get_TRISA() {
        return bank1[5];
    }

    public synchronized void set_TRISB(int value) {
        bank1[6] = value;
    }

    public synchronized int get_TRISB() {
        return bank1[6];
    }

    public synchronized void set_EECON1(int value) {
        bank1[7] = value;
    }

    public synchronized int get_EECON1() {
        return bank1[7];
    }

    public synchronized void set_EECON2(int value) {
        bank1[8] = value;
    }

    public synchronized int get_EECON2() {
        return bank1[8];
    }

    //Bank0 & Bank1 Statusflags
    public synchronized void set_Carryflag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00000001;
        } else {
            status &= 0b11111110;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_Carryflag() {
        return (get_STATUS() & 0b00000001) == 1;
    }

    public synchronized void set_Digitcarryflag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00000010;
        } else {
            status &= 0b11111101;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_Digitcarryflag() {
        return (get_STATUS() & 0b00000010) == 2;
    }

    public synchronized void set_Zeroflag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00000100;
        } else {
            status &= 0b11111011;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_Zeroflag() {
        return (get_STATUS() & 0b00000100) == 4;
    }

    public synchronized void set_TimeOutFlag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00001000;
        } else {
            status &= 0b11110111;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_TimeOutFlag() {
        return (get_STATUS() & 0b00001000) == 8;
    }

    public synchronized void set_PowerDownFlag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00010000;
        } else {
            status &= 0b11101111;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_PowerDownFlag() {
        return (get_STATUS() & 0b00010000) == 16;
    }

    public synchronized void set_RP0Bit(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b00100000;
        } else {
            status &= 0b11011111;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_RP0Bit() {
        return (get_STATUS() & 0b00100000) == 32;
    }

    public synchronized void set_RP1Bit(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b01000000;
        } else {
            status &= 0b10111111;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_RP1Bit() {
        return (get_STATUS() & 0b01000000) == 64;
    }

    public synchronized void set_Interruptflag(boolean value) {
        int status = get_STATUS();
        if (value) {
            status |= 0b10000000;
        } else {
            status &= 0b01111111;
        }
        set_STATUS(status);
    }

    public synchronized boolean get_Interruptflag() {
        return (get_STATUS() & 0b10000000) == 128;
    }

    public synchronized int get_Programcounter() {
        return iProgramcounter;
    }

    public synchronized int get_LastProgramcounter() {
        return lastProgramcounter;
    }

    /**
     * 
     * @param value
     */
    public synchronized void inkrement_Programcounter(int value) {        
        lastProgramcounter = get_Programcounter();
        iProgramcounter += value;
        iProgramcounter &= 0x3FF;
        set_PCL(iProgramcounter & 0xFF);
    }

    /**
     * Decrements PC by value.
     * @param value to decrement PC by.
     */
    public synchronized void dekrement_Programcounter(int value) {
        lastProgramcounter = get_Programcounter();
        iProgramcounter -= value;
        iProgramcounter &= 0x3FF;
        set_PCL(iProgramcounter & 0xFF);
    }

    /**
     * Sets PC to value.
     * @param value to set PC to.
     * @returns true if set worked, else false if set didn't work.
     */
    public synchronized void set_Programcounter(int value) {
        lastProgramcounter = iProgramcounter;
        iProgramcounter = value;
        set_PCL(iProgramcounter & 0xFF);
    }

    //Bank0 PORTA
    /**
     * 
     * @param value
     */
    public synchronized void set_RA0(boolean value) {
        int portA = get_PORTA();
        if (value) {
            portA |= 0b00000001;
        } else {
            portA &= 0b11111110;
        }
        set_PORTA(portA);
    }
    
    /**
     * 
     * @return
     */
    public synchronized boolean get_RA0() {
        return (get_PORTA() & 0b00000001) == 1;
    }

    /**
     * 
     * @param value
     */
    public synchronized void set_RA1(boolean value) {
        int portA = get_PORTA();
        if (value) {
            portA |= 0b00000010;
        } else {
            portA &= 0b11111101;
        }
        set_PORTA(portA);
    }
    
    /**
     * 
     * @return
     */
    public synchronized boolean get_RA1() {
        return (get_PORTA() & 0b00000010) == 2;
    }

    /**
     * 
     * @param value
     */
    public synchronized void set_RA2(boolean value) {
        int portA = get_PORTA();
        if (value) {
            portA |= 0b00000100;
        } else {
            portA &= 0b11111011;
        }
        set_PORTA(portA);
    }
    
    /**
     * 
     * @return
     */
    public synchronized boolean get_RA2() {
        return (get_PORTA() & 0b00000100) == 4;
    }

    /**
     * 
     * @param value
     */
    public synchronized void set_RA3(boolean value) {
        int portA = get_PORTA();
        if (value) {
            portA |= 0b00001000;
        } else {
            portA &= 0b11110111;
        }
        set_PORTA(portA);
    }
    
    public synchronized boolean get_RA3() {
        return (get_PORTA() & 0b00001000) == 8;
    }

    //External Clockimpulse for Timer0
    public synchronized void set_RA4_T0CKI(boolean value) {
        int portA = get_PORTA();
        if (value) {
            portA |= 0b00010000;
        } else {
            portA &= 0b11101111;
        }
        set_PORTA(portA);
    }
    
    public synchronized boolean get_RA4_T0CKI() {
        return (get_PORTA() & 0b00010000) == 16;
    }

    //Bank0 PortB
    public synchronized void set_RB0_INT(boolean value) {
        int portB = get_PORTB();
        boolean bOldRB0 = get_RB0_INT();
        //If INTEDG is set, check if rising edge appeared
        if (get_INTEDG()) {
            //If rising edge appeared, set INT-Interrupt-Flag
            if ((!bOldRB0) && value) {
                set_INTF(true);
            }
        //If INTEDG is clear, check if falling edge appeared
        } else {
            //If falling edge appeared, set INT-Interrupt-Flag
            if (bOldRB0 && (!value)) {
                set_INTF(true);
            }
        }
        if (value) {
            portB |= 0b00000001;
        } else {
            portB &= 0b11111110;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB0_INT() {
        return (get_PORTB() & 0b00000001) == 1;
    }

    public synchronized void set_RB1(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b00000010;
        } else {
            portB &= 0b11111101;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB1() {
        return (get_PORTB() & 0b00000010) == 2;
    }

    public synchronized void set_RB2(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b00000100;
        } else {
            portB &= 0b11111011;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB2() {
        return (get_PORTB() & 0b00000100) == 4;
    }

    public synchronized void set_RB3(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b00001000;
        } else {
            portB &= 0b11110111;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB3() {
        return (get_PORTB() & 0b00001000) == 8;
    }

    public synchronized void set_RB4(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b00010000;
        } else {
            portB &= 0b11101111;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB4() {
        return (get_PORTB() & 0b00010000) == 16;
    }

    public synchronized void set_RB5(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b00100000;
        } else {
            portB &= 0b11011111;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB5() {    
        return (get_PORTB() & 0b00100000) == 32;
    }

    public synchronized void set_RB6(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b01000000;
        } else {
            portB &= 0b10111111;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB6() {
        return (get_PORTB() & 0b01000000) == 64;
    }

    public synchronized void set_RB7(boolean value) {
        int portB = get_PORTB();
        if (value) {
            portB |= 0b10000000;
        } else {
            portB &= 0b01111111;
        }
        set_PORTB(portB);
    }
    
    public synchronized boolean get_RB7() {
        return (get_PORTB() & 0b10000000) == 128;
    }

    //Bank0 INTCON
    public synchronized void set_RBIF(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00000001;
        } else {
            intcon &= 0b11111110;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_RBIF() {    
        return (get_INTCON() & 0b00000001) == 1;
    }

    public synchronized void set_INTF(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00000010;
        } else {
            intcon &= 0b11111101;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_INTF() {    
        return (get_INTCON() & 0b00000010) == 2;
    }

    public synchronized void set_T0IF(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00000100;
        } else {
            intcon &= 0b11111011;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_T0IF() {
        return (get_INTCON() & 0b00000100) == 4;
    }

    public synchronized void set_RBIE(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00001000;
        } else {
            intcon &= 0b11110111;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_RBIE() {    
        return (get_INTCON() & 0b00001000) == 8;
    }

    public synchronized void set_INTE(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00010000;
        } else {
            intcon &= 0b11101111;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_INTE() {
        return (get_INTCON() & 0b00010000) == 16;
    }

    public synchronized void set_T0IE(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b00100000;
        } else {
            intcon &= 0b11011111;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_T0IE() {
        return (get_INTCON() & 0b00100000) == 32;
    }

    public synchronized void set_EEIE(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b01000000;
        } else {
            intcon &= 0b10111111;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_EEIE() {
        return (get_INTCON() & 0b01000000) == 64;
    }

    public synchronized void set_GIE(boolean value) {
        int intcon = get_INTCON();
        if (value) {
            intcon |= 0b10000000;
        } else {
            intcon &= 0b01111111;
        }
        set_INTCON(intcon);
    }
    
    public synchronized boolean get_GIE() {    
        return (get_INTCON() & 0b10000000) == 128;
    }

    //Bank1 OPTION_REG
    public synchronized void set_PS0(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00000001;
        } else {
            option &= 0b11111110;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_PS0() {    
        return (get_OPTION() & 0b00000001) == 1;
    }

    public synchronized void set_PS1(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00000010;
        } else {
            option &= 0b11111101;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_PS1() {    
        return (get_OPTION() & 0b00000010) == 2;
    }

    public synchronized void set_PS2(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00000100;
        } else {
            option &= 0b11111011;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_PS2() {    
        return (get_OPTION() & 0b00000100) == 4;
    }

    public synchronized int get_TMR0_PrescalerRate() {
        int returnValue = 0;
        if (get_PSA()) {
            returnValue = 1;
        } else {
            switch (get_OPTION() & 0b00000111) {
                case 0: {
                    returnValue = 2;
                }break;
                case 1: {
                    returnValue = 4;
                }break;
                case 2: {
                    returnValue = 8;
                }break;
                case 3: {
                    returnValue = 16;
                }break;
                case 4: {
                    returnValue = 32;
                }break;
                case 5: {
                    returnValue = 64;
                }break;
                case 6: {
                    returnValue = 128;
                }break;
                case 7: {
                    returnValue = 256;
                }break;
            }
        }
        return returnValue;
    }

    public synchronized int get_WDT_PrescalerRate() {
        int returnValue = 0;

        switch (get_OPTION() & 0b00000111) {
            case 0: {
                returnValue = 1;
            }break;
            case 1: {
                returnValue = 2;
            }break;
            case 2: {
                returnValue = 4;
            }break;
            case 3: {
                returnValue = 8;
            }break;
            case 4: {
                returnValue = 16;
            }break;
            case 5: {
                returnValue = 32;
            }break;
            case 6: {
                returnValue = 64;
            }break;
            case 7: {
                returnValue = 128;
            }break;
        }
        return returnValue;
    }

    public synchronized void set_PSA(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00001000;
        } else {
            option &= 0b11110111;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_PSA() {
        return (get_OPTION() & 0b00001000) == 8;
    }

    public synchronized void set_T0SE(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00010000;
        } else {
            option &= 0b11101111;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_T0SE() {
        return (get_OPTION() & 0b00010000) == 16;
    }

    public synchronized void set_T0CS(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b00100000;
        } else {
            option &= 0b11011111;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_T0CS() {
        return (get_OPTION() & 0b00100000) == 32;
    }

    public synchronized void set_INTEDG(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b01000000;
        } else {
            option &= 0b10111111;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_INTEDG() {
        return (get_OPTION() & 0b01000000) == 64;
    }

    public synchronized void set_RBPU(boolean value) {
        int option = get_OPTION();
        if (value) {
            option |= 0b10000000;
        } else {
            option &= 0b01111111;
        }
        set_OPTION(option);
    }
    
    public synchronized boolean get_RBPU() {
        return (get_OPTION() & 0b10000000) == 128;
    }

    //Bank1 EECON1
    public synchronized void set_RD(boolean value) {
        int eecon = get_EECON1();
        if (value) {
            eecon |= 0b00000001;
        } else {
            eecon &= 0b11111110;
        }
        set_EECON1(eecon);
    }
    
    public synchronized boolean get_RD() {
        return (get_EECON1() & 0b00000001) == 1;
    }

    public synchronized void set_WR(boolean value) {
        int eecon = get_EECON1();
        if (value) {
            eecon |= 0b00000010;
        } else {
            eecon &= 0b11111101;
        }
        set_EECON1(eecon);
    }
    
    public synchronized boolean get_WR() {
        return (get_EECON1() & 0b00000010) == 2;
    }

    public synchronized void set_WREN(boolean value) {
        int eecon = get_EECON1();
        if (value) {
            eecon |= 0b00000100;
        } else {
            eecon &= 0b11111011;
        }
        set_EECON1(eecon);
    }
    
    public synchronized boolean get_WREN() {
        return (get_EECON1() & 0b00000100) == 4;
    }

    public synchronized void set_WRERR(boolean value) {
        int eecon = get_EECON1();
        if (value) {
            eecon |= 0b00001000;
        } else {
            eecon &= 0b11110111;
        }
        set_EECON1(eecon);
    }
    
    public synchronized boolean get_WRERR() {
        return (get_EECON1() & 0b00001000) == 8;
    }

    public synchronized void set_EEIF(boolean value) {
        int eecon = get_EECON1();
        if (value) {
            eecon |= 0b00010000;
        } else {
            eecon &= 0b11101111;
        }
        set_EECON1(eecon);
    }
    
    public synchronized boolean get_EEIF() {
        return (get_EECON1() & 0b00010000) == 16;   
    }

    //Bank1 TRISA
    public synchronized void set_TRISA0(boolean value) {
        int trisA = get_TRISA();
        if (value) {
            trisA |= 0b00000001;
        } else {
            trisA &= 0b11111110;
        }
        set_TRISA(trisA);
    }

    public synchronized boolean get_TRISA0() {
        return (get_TRISA() & 0b00000001) == 1;
    }

    public synchronized void set_TRISA1(boolean value) {
        int trisA = get_TRISA();
        if (value) {
            trisA |= 0b00000010;
        } else {
            trisA &= 0b11111101;
        }
        set_TRISA(trisA);
    }

    public synchronized boolean get_TRISA1() {
        return (get_TRISA() & 0b00000010) == 2;
    }

    public synchronized void set_TRISA2(boolean value) {
        int trisA = get_TRISA();
        if (value) {
            trisA |= 0b00000100;
        } else {
            trisA &= 0b11111011;
        }
        set_TRISA(trisA);
    }

    public synchronized boolean get_TRISA2() {
        return (get_TRISA() & 0b00000100) == 4;
    }

    public synchronized void set_TRISA3(boolean value) {
        int trisA = get_TRISA();
        if (value) {
            trisA |= 0b00001000;
        } else {
            trisA &= 0b11110111;
        }
        set_TRISA(trisA);
    }

    public synchronized boolean get_TRISA3() {
        return (get_TRISA() & 0b00001000) == 8;
    }

    public synchronized void set_TRISA4(boolean value) {
        int trisA = get_TRISA();
        if (value) {
            trisA |= 0b00010000;
        } else {
            trisA &= 0b11101111;
        }
        set_TRISA(trisA);
    }

    public synchronized boolean get_TRISA4() {
        return (get_TRISA() & 0b00010000) == 16;
    }

    public synchronized void set_TRISB0(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00000001;
        } else {
            trisB &= 0b11111110;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB0() {
        return (get_TRISB() & 0b00000001) == 1;
    }

    public synchronized void set_TRISB1(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00000010;
        } else {
            trisB &= 0b11111101;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB1() {
        return (get_TRISB() & 0b00000010) == 2;
    }

    public synchronized void set_TRISB2(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00000100;
        } else {
            trisB &= 0b11111011;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB2() {
        return (get_TRISB() & 0b00000100) == 4;
    }

    public synchronized void set_TRISB3(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00001000;
        } else {
            trisB &= 0b11110111;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB3() {
        return (get_TRISB() & 0b00001000) == 8;
    }

    public synchronized void set_TRISB4(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00010000;
        } else {
            trisB &= 0b11101111;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB4() {
        return (get_TRISB() & 0b00010000) == 16;
    }

    public synchronized void set_TRISB5(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b00100000;
        } else {
            trisB &= 0b11011111;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB5() {
        return (get_TRISB() & 0b00100000) == 32;
    }

    public synchronized void set_TRISB6(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b01000000;
        } else {
            trisB &= 0b10111111;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB6() {
        return (get_TRISB() & 0b01000000) == 64;
    }

    public synchronized void set_TRISB7(boolean value) {
        int trisB = get_TRISB();
        if (value) {
            trisB |= 0b10000000;
        } else {
            trisB &= 0b01111111;
        }
        set_TRISB(trisB);
    }

    public synchronized boolean get_TRISB7() {
        return (get_TRISB() & 0b10000000) == 128;
    }

    public synchronized int[] get_Bank0() {
        return bank0;
    }

    public synchronized int[] get_Bank1() {
        return bank1;
    }
}