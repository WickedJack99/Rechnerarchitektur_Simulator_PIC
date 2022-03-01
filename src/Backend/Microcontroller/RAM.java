package Backend.Microcontroller;

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
public class RAM {
    //Bank0 of the PIC-RAM
    private int[] bank0;

    //Bank1 of the PIC-RAM
    private int[] bank1;

    //Last programmcounter for function getLastLine in main.
    private int lastProgramcounter;

    //Prescaler counters Timer0.
    private int Timer0Prescaled;
    private int Timer0PrescaledRA4;

    /**
     * Constructor of RAM
     */
    public RAM() {
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
    public int get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(boolean rP0Bit, int adress) {
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
     * @param x shows if bank 0 or 1 will be set.
     * @param array an array which will represent bank x.
     */
    public void set_Bank_X(boolean rp0Bit, int[] array) {
        if (rp0Bit == false) {
            bank0 = array;
        }

        if (rp0Bit == true) {
            bank1 = array;
        }
    }

    /**
     * @param element between 0 and 127.
     * @param bank where the value will be changed.
     * @param value that will be written into the element.
     */
    public void set_Element_X_Of_Bank_Y_To_Z(int element, boolean rp0Bit, int value) {
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
            }

            else {
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
                        }break;
    
                        case 3: {
                            set_STATUS(value);
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
    
                        case 9:
                        {
                            set_EEADR(value);
                        }break;
    
                        case 10:
                        {
                            set_PCLATH(value);
                        }break;
    
                        case 11:
                        {
                            set_INTCON(value);
                        }break;
                    }
                }

                //Bank1 Registers
                else
                {
                    switch (element)
                    {
                        case 0:
                        {
                            bank1[bank0[4]] = value;
                        }break;

                        case 1:
                        {
                            set_OPTION(value);
                        }break;

                        case 2:
                        {
                            bank1[2] = value;
                        }break;

                        case 3:
                        {
                            bank1[3] = value;
                        }break;

                        case 4:
                        {
                            bank1[4] = value;
                        }break;

                        case 5:
                        {
                            set_TRISA(value);
                        }break;

                        case 6:
                        {
                            set_TRISB(value);
                        }break;

                        case 7:
                        {
                            set_EECON1(value);
                        }break;

                        case 8:
                        {
                            set_EECON2(value);
                        }break;

                        case 9:
                        {
                            bank1[9] = value;
                        }break;

                        case 10:
                        {
                            bank1[10] = value;
                        }break;

                        case 11:
                        {
                            bank1[11] = value;
                        }break;
                    }
                }            
            }
        }
    }

    //Bank0 Registers
    public void set_TMR0(int value)
    {
        value &= 255;
        bank0[1] = value;
    }

    public void increment_TMR0()
    {
        Timer0Prescaled++;

        if (Timer0Prescaled == get_TMR0_PrescalerRate())
        {
            if (bank0[1] == 255)
            {
                set_T0IF(true);
                bank0[1] = 0;
            }
    
            else
            {
                set_T0IF(false);
                bank0[1]++;
            }
            Timer0Prescaled = 0;
        }
    }

    public int get_TMR0()
    {
        return bank0[1];
    }

    public void set_PCL(int value)
    {
        bank0[2] = value;
        bank1[2] = value;
    }

    public int get_PCL()
    {
        return bank0[2];
    }

    public void set_STATUS(int value)
    {
        bank0[3] = value;
        bank1[3] = value;
    }

    public int get_STATUS()
    {
        return bank0[3];
    }

    public void set_FSR(int value)
    {
        bank0[4] = value;
        bank1[4] = value;
    }

    public int get_FSR()
    {
        return bank0[4];
    }

    public void set_PORTA(int value)
    {
        if (((value & 0b00010000) == 0b00010000) && get_T0CS())
        {
            Timer0PrescaledRA4++;
            if (Timer0PrescaledRA4 == get_TMR0_PrescalerRate())
            {
                if (bank0[1] == 255)
                {
                    set_T0IF(true);
                    bank0[1] = 0;
                }
    
                else
                {
                    set_T0IF(false);
                    bank0[1]++;
                }
                Timer0PrescaledRA4 = 0;
            }
        }

        bank0[5] = value;
    }

    public void set_PORTA_Bit_X_To_Y(int x, int y)
    {
        if ((x == 4))
        {
            if (y == 1)
            {
                if (get_T0CS())
                {
                    Timer0PrescaledRA4++;
                    if (Timer0PrescaledRA4 == get_TMR0_PrescalerRate())
                    {
                        if (bank0[1] == 255)
                        {
                            set_T0IF(true);
                            bank0[1] = 0;
                        }
            
                        else
                        {
                            set_T0IF(false);
                            bank0[1]++;
                        }
                        Timer0PrescaledRA4 = 0;
                    }
                }
            }
        }

        if (y == 0)
        {
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

        //if (y == 1)
        else
        {
            bank0[5] |= ((0b00000001) << x);
        }
    }

    public int get_PORTA()
    {
        return bank0[5];
    }

    public void set_PORTB(int value)
    {
        bank0[6] = value;
    }

    public void set_PORTB_Bit_X_To_Y(int x, int y)
    {
        if (y == 0)
        {
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

        //if (y == 1)
        else
        {
            bank0[6] |= ((0b00000001) << x);
        }
    }

    public int get_PORTB()
    {
        return bank0[6];
    }

    public void set_EEDATA(int value)
    {
        bank0[8] = value;
    }

    public int get_EEDATA()
    {
        return bank0[8];
    }

    public void set_EEADR(int value)
    {
        bank0[9] = value;
    }

    public int get_EEADR()
    {
        return bank0[9];
    }

    public void set_PCLATH(int value)
    {
        bank0[10] = value;
        bank1[10] = value;
    }

    public int get_PCLATH()
    {
        return bank0[10];
    }

    public void set_INTCON(int value)
    {
        bank0[11] = value;
        bank1[11] = value;
    }

    public int get_INTCON()
    {
        return bank0[11];
    }

    //Bank1 Registers
    public void set_OPTION(int value)
    {
        bank1[1] = value;
    }

    public int get_OPTION()
    {
        return bank1[1];
    }

    public void set_TRISA(int value)
    {
        bank1[5] = value;
    }

    public int get_TRISA()
    {
        return bank1[5];
    }

    public void set_TRISB(int value)
    {
        bank1[6] = value;
    }

    public int get_TRISB()
    {
        return bank1[6];
    }

    public void set_EECON1(int value)
    {
        bank1[7] = value;
    }

    public int get_EECON1()
    {
        return bank1[7];
    }

    public void set_EECON2(int value)
    {
        bank1[8] = value;
    }

    public int get_EECON2()
    {
        return bank1[8];
    }

    //Bank0 & Bank1 Statusflags
    public void set_Carryflag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00000001;
        }

        else
        {
            status &= 0b11111110;
        }

        set_STATUS(status);
    }

    public boolean get_Carryflag()
    {
        return (get_STATUS() & 0b00000001) == 1;
    }

    public void set_Digitcarryflag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00000010;
        }

        else
        {
            status &= 0b11111101;
        }

        set_STATUS(status);
    }

    public boolean get_Digitcarryflag()
    {
        return (get_STATUS() & 0b00000010) == 2;
    }

    public void set_Zeroflag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00000100;
        }

        else
        {
            status &= 0b11111011;
        }

        set_STATUS(status);
    }

    public boolean get_Zeroflag()
    {
        return (get_STATUS() & 0b00000100) == 4;
    }

    public void set_TimeOutFlag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00001000;
        }

        else
        {
            status &= 0b11110111;
        }

        set_STATUS(status);
    }

    public boolean get_TimeOutFlag()
    {
        return (get_STATUS() & 0b00001000) == 8;
    }

    public void set_PowerDownFlag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00010000;
        }

        else
        {
            status &= 0b11101111;
        }

        set_STATUS(status);
    }

    public boolean get_PowerDownFlag()
    {
        return (get_STATUS() & 0b00010000) == 16;
    }

    public void set_RP0Bit(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b00100000;
        }

        else
        {
            status &= 0b11011111;
        }

        set_STATUS(status);
    }

    public boolean get_RP0Bit()
    {
        return (get_STATUS() & 0b00100000) == 32;
    }

    public void set_RP1Bit(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b01000000;
        }

        else
        {
            status &= 0b10111111;
        }

        set_STATUS(status);
    }

    public boolean get_RP1Bit()
    {
        return (get_STATUS() & 0b01000000) == 64;
    }

    public void set_Interruptflag(boolean value)
    {
        int status = get_STATUS();
        if (value)
        {
            status |= 0b10000000;
        }

        else
        {
            status &= 0b01111111;
        }

        set_STATUS(status);
    }

    public boolean get_Interruptflag()
    {
        return (get_STATUS() & 0b10000000) == 128;
    }

    //Bank0 PCL
    public int get_Programcounter()
    {
        return (bank0[2]);
    }

    public int get_LastProgramcounter()
    {
        return lastProgramcounter;
    }

    public void inkrement_Programcounter(int value, int kindOfCall) //0 at Fetchzycle, 1 at Jumpcommand, 2 at ...
    {
        if (bank0[2] >= 0 && bank0[2] <= 255)
        {
            lastProgramcounter = bank0[2];
            bank0[2] += value;
        }
        
        else
        {
            if (kindOfCall == 0)
            {
                //Nothing happens
            }

            if (kindOfCall == 1)
            {
                bank0[2] = 0; //Wrap-Around at fetchcycle
            }
        }
    }

    public void dekrement_Programcounter(int value)
    {
        if (bank0[2] > 0 && bank0[2] <= 255)
        {
            lastProgramcounter = bank0[2];
            bank0[2] -= value;
        }
        
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean set_Programcounter(int value)
    {
        boolean setWorked = false;

        if (value >= 0 && value <= 255)
        {
            lastProgramcounter = bank0[2];
            bank0[2] = value;
            setWorked = true;
        }
        
        else
        {
            throw new IndexOutOfBoundsException();
        }

        return setWorked;
    }

    //Bank0 PORTA
    public void set_RA0(boolean value)
    {
        int portA = get_PORTA();
        if (value)
        {
            portA |= 0b00000001;
        }

        else
        {
            portA &= 0b11111110;
        }

        set_PORTA(portA);
    }
    
    public boolean get_RA0()
    {
        {
            return (get_PORTA() & 0b00000001) == 1;
        }
    }

    public void set_RA1(boolean value)
    {
        int portA = get_PORTA();
        if (value)
        {
            portA |= 0b00000010;
        }

        else
        {
            portA &= 0b11111101;
        }

        set_PORTA(portA);
    }
    
    public boolean get_RA1()
    {
        {
            return (get_PORTA() & 0b00000010) == 2;
        }
    }

    public void set_RA2(boolean value)
    {
        int portA = get_PORTA();
        if (value)
        {
            portA |= 0b00000100;
        }

        else
        {
            portA &= 0b11111011;
        }

        set_PORTA(portA);
    }
    
    public boolean get_RA2()
    {
        {
            return (get_PORTA() & 0b00000100) == 4;
        }
    }

    public void set_RA3(boolean value)
    {
        int portA = get_PORTA();
        if (value)
        {
            portA |= 0b00001000;
        }

        else
        {
            portA &= 0b11110111;
        }

        set_PORTA(portA);
    }
    
    public boolean get_RA3()
    {
        {
            return (get_PORTA() & 0b00001000) == 8;
        }
    }

    //External Clockimpulse for Timer0
    public void set_RA4_T0CKI(boolean value)
    {
        int portA = get_PORTA();
        if (value)
        {
            portA |= 0b00010000;
        }

        else
        {
            portA &= 0b11101111;
        }

        set_PORTA(portA);
    }
    
    public boolean get_RA4_T0CKI()
    {
        {
            return (get_PORTA() & 0b00010000) == 16;
        }
    }

    //Bank0 PortB
    public void set_RB0_INT(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00000001;
        }

        else
        {
            portB &= 0b11111110;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB0_INT()
    {
        {
            return (get_PORTB() & 0b00000001) == 1;
        }
    }

    public void set_RB1(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00000010;
        }

        else
        {
            portB &= 0b11111101;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB1()
    {
        {
            return (get_PORTB() & 0b00000010) == 2;
        }
    }

    public void set_RB2(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00000100;
        }

        else
        {
            portB &= 0b11111011;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB2()
    {
        return (get_PORTB() & 0b00000100) == 4;
    }

    public void set_RB3(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00001000;
        }

        else
        {
            portB &= 0b11110111;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB3()
    {
        {
            return (get_PORTB() & 0b00001000) == 8;
        }
    }

    public void set_RB4(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00010000;
        }

        else
        {
            portB &= 0b11101111;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB4()
    {
        {
            return (get_PORTB() & 0b00010000) == 16;
        }
    }

    public void set_RB5(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b00100000;
        }

        else
        {
            portB &= 0b11011111;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB5()
    {
        {
            return (get_PORTB() & 0b00100000) == 32;
        }
    }

    public void set_RB6(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b01000000;
        }

        else
        {
            portB &= 0b10111111;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB6()
    {
        {
            return (get_PORTB() & 0b01000000) == 64;
        }
    }

    public void set_RB7(boolean value)
    {
        int portB = get_PORTB();
        if (value)
        {
            portB |= 0b10000000;
        }

        else
        {
            portB &= 0b01111111;
        }

        set_PORTB(portB);
    }
    
    public boolean get_RB7()
    {
        {
            return (get_PORTB() & 0b10000000) == 128;
        }
    }

    //Bank0 INTCON
    public void set_RBIF(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00000001;
        }

        else
        {
            intcon &= 0b11111110;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_RBIF()
    {
        {
            return (get_INTCON() & 0b00000001) == 1;
        }
    }

    public void set_INTF(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00000010;
        }

        else
        {
            intcon &= 0b11111101;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_INTF()
    {
        {
            return (get_INTCON() & 0b00000010) == 2;
        }
    }

    public void set_T0IF(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00000100;
        }

        else
        {
            intcon &= 0b11111011;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_T0IF()
    {
        {
            return (get_INTCON() & 0b00000100) == 4;
        }
    }

    public void set_RBIE(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00001000;
        }

        else
        {
            intcon &= 0b11110111;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_RBIE()
    {
        {
            return (get_INTCON() & 0b00001000) == 8;
        }
    }

    public void set_INTE(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00010000;
        }

        else
        {
            intcon &= 0b11101111;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_INTE()
    {
        {
            return (get_INTCON() & 0b00010000) == 16;
        }
    }

    public void set_T0IE(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b00100000;
        }

        else
        {
            intcon &= 0b11011111;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_T0IE()
    {
        {
            return (get_INTCON() & 0b00100000) == 32;
        }
    }

    public void set_EEIE(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b01000000;
        }

        else
        {
            intcon &= 0b10111111;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_EEIE()
    {
        {
            return (get_INTCON() & 0b01000000) == 64;
        }
    }

    public void set_GIE(boolean value)
    {
        int intcon = get_INTCON();
        if (value)
        {
            intcon |= 0b10000000;
        }

        else
        {
            intcon &= 0b01111111;
        }

        set_INTCON(intcon);
    }
    
    public boolean get_GIE()
    {
        {
            return (get_INTCON() & 0b10000000) == 128;
        }
    }

    //Bank1 OPTION_REG
    public void set_PS0(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00000001;
        }

        else
        {
            option &= 0b11111110;
        }

        set_OPTION(option);
    }
    
    public boolean get_PS0()
    {
        {
            return (get_OPTION() & 0b00000001) == 1;
        }
    }

    public void set_PS1(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00000010;
        }

        else
        {
            option &= 0b11111101;
        }

        set_OPTION(option);
    }
    
    public boolean get_PS1()
    {
        {
            return (get_OPTION() & 0b00000010) == 2;
        }
    }

    public void set_PS2(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00000100;
        }

        else
        {
            option &= 0b11111011;
        }

        set_OPTION(option);
    }
    
    public boolean get_PS2()
    {
        {
            return (get_OPTION() & 0b00000100) == 4;
        }
    }

    public int get_TMR0_PrescalerRate()
    {
        int returnValue = 0;

        if (get_PSA())
        {
            returnValue = 1;
        }
        
        else
        {
            switch (get_OPTION() & 0b00000111)
            {
                case 0:
                {
                    returnValue = 2;
                }break;
            
                case 1:
                {
                    returnValue = 4;
                }break;
    
                case 2:
                {
                    returnValue = 8;
                }break;
    
                case 3:
                {
                    returnValue = 16;
                }break;
    
                case 4:
                {
                    returnValue = 32;
                }break;
    
                case 5:
                {
                    returnValue = 64;
                }break;
    
                case 6:
                {
                    returnValue = 128;
                }break;
    
                case 7:
                {
                    returnValue = 256;
                }break;
            }
        }
        return returnValue;
    }

    public int get_WDT_PrescalerRate()
    {
        int returnValue = 0;

        switch (get_OPTION() & 0b00000111)
        {
            case 0:
            {
                returnValue = 1;
            }break;
        
            case 1:
            {
                returnValue = 2;
            }break;

            case 2:
            {
                returnValue = 4;
            }break;

            case 3:
            {
                returnValue = 8;
            }break;

            case 4:
            {
                returnValue = 16;
            }break;

            case 5:
            {
                returnValue = 32;
            }break;

            case 6:
            {
                returnValue = 64;
            }break;

            case 7:
            {
                returnValue = 128;
            }break;
        }

        return returnValue;
    }

    public void set_PSA(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00001000;
        }

        else
        {
            option &= 0b11110111;
        }

        set_OPTION(option);
    }
    
    public boolean get_PSA()
    {
        {
            return (get_OPTION() & 0b00001000) == 8;
        }
    }

    public void set_T0SE(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00010000;
        }

        else
        {
            option &= 0b11101111;
        }

        set_OPTION(option);
    }
    
    public boolean get_T0SE()
    {
        {
            return (get_OPTION() & 0b00010000) == 16;
        }
    }

    public void set_T0CS(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b00100000;
        }

        else
        {
            option &= 0b11011111;
        }

        set_OPTION(option);
    }
    
    public boolean get_T0CS()
    {
        {
            return (get_OPTION() & 0b00100000) == 32;
        }
    }

    public void set_INTEDG(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b01000000;
        }

        else
        {
            option &= 0b10111111;
        }

        set_OPTION(option);
    }
    
    public boolean get_INTEDG()
    {
        {
            return (get_OPTION() & 0b01000000) == 64;
        }
    }

    public void set_RBPU(boolean value)
    {
        int option = get_OPTION();
        if (value)
        {
            option |= 0b10000000;
        }

        else
        {
            option &= 0b01111111;
        }

        set_OPTION(option);
    }
    
    public boolean get_RBPU()
    {
        {
            return (get_OPTION() & 0b10000000) == 128;
        }
    }

    //Bank1 EECON1
    public void set_RD(boolean value)
    {
        int eecon = get_EECON1();
        if (value)
        {
            eecon |= 0b00000001;
        }

        else
        {
            eecon &= 0b11111110;
        }

        set_EECON1(eecon);
    }
    
    public boolean get_RD()
    {
        {
            return (get_EECON1() & 0b00000001) == 1;
        }
    }

    public void set_WR(boolean value)
    {
        int eecon = get_EECON1();
        if (value)
        {
            eecon |= 0b00000010;
        }

        else
        {
            eecon &= 0b11111101;
        }

        set_EECON1(eecon);
    }
    
    public boolean get_WR()
    {
        {
            return (get_EECON1() & 0b00000010) == 2;
        }
    }

    public void set_WREN(boolean value)
    {
        int eecon = get_EECON1();
        if (value)
        {
            eecon |= 0b00000100;
        }

        else
        {
            eecon &= 0b11111011;
        }

        set_EECON1(eecon);
    }
    
    public boolean get_WREN()
    {
        {
            return (get_EECON1() & 0b00000100) == 4;
        }
    }

    public void set_WRERR(boolean value)
    {
        int eecon = get_EECON1();
        if (value)
        {
            eecon |= 0b00001000;
        }

        else
        {
            eecon &= 0b11110111;
        }

        set_EECON1(eecon);
    }
    
    public boolean get_WRERR()
    {
        {
            return (get_EECON1() & 0b00001000) == 8;
        }
    }

    public void set_EEIF(boolean value)
    {
        int eecon = get_EECON1();
        if (value)
        {
            eecon |= 0b00010000;
        }

        else
        {
            eecon &= 0b11101111;
        }

        set_EECON1(eecon);
    }
    
    public boolean get_EEIF()
    {
        {
            return (get_EECON1() & 0b00010000) == 16;
        }
    }

    //Bank1 TRISA
    public void set_TRISA0(boolean value)
    {
        int trisA = get_TRISA();
        if (value)
        {
            trisA |= 0b00000001;
        }

        else
        {
            trisA &= 0b11111110;
        }

        set_TRISA(trisA);
    }

    public boolean get_TRISA0()
    {
        return (get_TRISA() & 0b00000001) == 1;
    }

    public void set_TRISA1(boolean value)
    {
        int trisA = get_TRISA();
        if (value)
        {
            trisA |= 0b00000010;
        }

        else
        {
            trisA &= 0b11111101;
        }

        set_TRISA(trisA);
    }

    public boolean get_TRISA1()
    {
        return (get_TRISA() & 0b00000010) == 2;
    }

    public void set_TRISA2(boolean value)
    {
        int trisA = get_TRISA();
        if (value)
        {
            trisA |= 0b00000100;
        }

        else
        {
            trisA &= 0b11111011;
        }

        set_TRISA(trisA);
    }

    public boolean get_TRISA2()
    {
        return (get_TRISA() & 0b00000100) == 4;
    }

    public void set_TRISA3(boolean value)
    {
        int trisA = get_TRISA();
        if (value)
        {
            trisA |= 0b00001000;
        }

        else
        {
            trisA &= 0b11110111;
        }

        set_TRISA(trisA);
    }

    public boolean get_TRISA3()
    {
        return (get_TRISA() & 0b00001000) == 8;
    }

    public void set_TRISA4(boolean value)
    {
        int trisA = get_TRISA();
        if (value)
        {
            trisA |= 0b00010000;
        }

        else
        {
            trisA &= 0b11101111;
        }

        set_TRISA(trisA);
    }

    public boolean get_TRISA4()
    {
        return (get_TRISA() & 0b00010000) == 16;
    }
}