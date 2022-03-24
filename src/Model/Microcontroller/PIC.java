package Model.Microcontroller;

/**
 * @author Aaron Moser
 * @date 23.06.2021
 */

/** 
 * Microcontrollerclass that contains all other partclasses
 */
public class PIC {
    /**
     * Parts of PIC.
     * Objects are written with a large starting letter.
     */
    private EEPROM Eeprom;
    private RAM Ram;
    private STACK Stack;
    private int WRegister;

    public PIC() {
        //Initialising objects of PIC.
        Eeprom = new EEPROM();
        Ram = new RAM();
        Stack = new STACK();
        WRegister = 0;
    }

    public synchronized void resetPIC() {
        Ram = new RAM();
        Stack = new STACK();
        WRegister = 0;
    }

    //Bitmasks to set or get bits (use OR for set and AND for get).
    int bitMaskSetBit0 = 0b00000001;
    int bitMaskSetBit1 = 0b00000010;
    int bitMaskSetBit2 = 0b00000100;
    int bitMaskSetBit3 = 0b00001000;
    int bitMaskSetBit4 = 0b00010000;
    int bitMaskSetBit5 = 0b00100000;
    int bitMaskSetBit6 = 0b01000000;
    int bitMaskSetBit7 = 0b10000000;

    int bitMaskSetBitArray[] = { bitMaskSetBit0, bitMaskSetBit1, bitMaskSetBit2, bitMaskSetBit3, bitMaskSetBit4, bitMaskSetBit5,
                                bitMaskSetBit6, bitMaskSetBit7 };

    //Bitmasks to clear bits (use AND).
    int bitMaskClearBit0 = 0b11111110;
    int bitMaskClearBit1 = 0b11111101;
    int bitMaskClearBit2 = 0b11111011;
    int bitMaskClearBit3 = 0b11110111;
    int bitMaskClearBit4 = 0b11101111;
    int bitMaskClearBit5 = 0b11011111;
    int bitMaskClearBit6 = 0b10111111;
    int bitMaskClearBit7 = 0b01111111;

    int bitMaskClearBitArray[] = { bitMaskClearBit0, bitMaskClearBit1, bitMaskClearBit2, bitMaskClearBit3, bitMaskClearBit4, bitMaskClearBit5,
                                   bitMaskClearBit6, bitMaskClearBit7};

    public synchronized void setWRegister(int value) {
        WRegister = value;
    }

    public synchronized int get_WRegister() {
        return WRegister;
    }

    public synchronized RAM getRam() {
        return Ram;
    }

    public synchronized EEPROM getEeprom() {
        return Eeprom;
    }

    public synchronized STACK getStack() {
        return Stack;
    }

    /**
     * Datasheet Page 57
     * 
     * The contents of the W register are
     * added to the eight bit literal ’k’ and the
     * result is placed in the W register.
     */
    public void ADDLW(int eightK) {
        int wRegValue = get_WRegister();
        int result = wRegValue + eightK;

        //If result is zero.
        if (result == 0) {
            Ram.set_Zeroflag(true);
        }
        
        //If result is not zero.
        else {
            Ram.set_Zeroflag(false);
        }

        //If result is greater than 255.
        if (result > 255) {
            result = result & 255;
            Ram.set_Carryflag(true);
        }

        //If result is smaller than 0.
        else if (result < 0) {
            result = result & 255;
            Ram.set_Carryflag(true);
        }

        //If result is greater than or equal to 0 and smaller than or equal to 255.  
        else {
            Ram.set_Carryflag(false);
        }

        //The lower four bits are bitwise and with 1111b (15d).
        int dcResult = (wRegValue & 15) + (eightK & 15);

        //If the result is greater than 15, DC-Flag is set true.
        if (dcResult > 15) {
            Ram.set_Digitcarryflag(true);
        }

        //If the result is smaller or equal to 15, DC-Flag is set false.
        else {
            Ram.set_Digitcarryflag(false);
        }

        setWRegister(result);
        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PCL and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 57
     * 
     * The contents of W register are
     * AND’ed with the eight bit literal 'k'. The
     * result is placed in the W register.
     */
    public void ANDLW(int eightK)
    {
        int wRegValue = get_WRegister();
        int result = wRegValue & eightK;

        //If result is zero.
        if (result == 0)
        {
            Ram.set_Zeroflag(true);
        }
        
        //If result is not zero.
        else
        {
            Ram.set_Zeroflag(false);
        }

        //Write result into 
        setWRegister(result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 57
     * 
     * Add the contents of the W register with the
     * contents of register ’f’. If ’d’ is 0 the result is
     * stored in the W register. If ’d’ is 1 the result is
     * stored back in register ’f’.
     */
    public void ADDWF(int destinationBit, int registerFileAddress)
    {
        //Get Value of 
        int wRegValue = get_WRegister();
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //Calculate result.
        int result = wRegValue + regFileAddrValue;

        //If result is zero.
        if (result == 0)
        {
            Ram.set_Zeroflag(true);
        }

        //If result is not zero.
        else
        {
            Ram.set_Zeroflag(false);
        }

        //If result is greater than 255.
        if (result > 255)
        {
            result &= 255;
            Ram.set_Carryflag(true);
        }

        //If result is smaller than 0.
        else if (result < 0)
        {
            result &= 255;
            Ram.set_Carryflag(true);
        }

        else
        {
            Ram.set_Carryflag(false);
        }

        //The lower four bits are bitwise and with 1111boolean (15dezimal).
        int dcResult = (wRegValue & 15) + (regFileAddrValue & 15);

        //If the result is greater than 15, DC-Flag is set true.
        if (dcResult > 15)
        {
            Ram.set_Digitcarryflag(true);
        }

        //If the result is smaller or equal to 15, DC-Flag is set false.
        else
        {
            Ram.set_Digitcarryflag(false);
        }

        //If the destinationbit is 0, the result is written into the 
        if (destinationBit == 0)
        {
            setWRegister(result);
        }

        //If the destinationbit is 1, the result is written into the RAM.
        else
        {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 57
     * 
     * AND the W register with contents of register 'f'.
     * If 'd' is 0 the result is stored in the W regis-
     * ter. If 'd' is 1 the result is stored back in
     * register 'f'.
     */
    public void ANDWF(int destinationBit, int registerFileAddress)
    {
        //Get Value of 
        int wRegValue = get_WRegister();
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        
        //Calculate result.
        int result = wRegValue & regFileAddrValue;

        //If result is zero.
        if (result == 0)
        {
            Ram.set_Zeroflag(true);
        }
        
        //If result is not zero.
        else
        {
            Ram.set_Zeroflag(false);
        }

        //If the destinationbit is 0, the result is written into the 
        if (destinationBit == 0)
        {
            setWRegister(result);
        }

        //If the destinationbit is 1, the result is written into the RAM.
        else
        {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 58
     * 
     * Bit ’b’ in register ’f’ is cleared .
     */
    public void BCF(int bitaddress, int registerFileAddress)
    {
        //Get bitmask to clear bit in fileaddress.
        int bitMask = bitMaskClearBitArray[bitaddress];
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //Result is bitMask AND value at fileaddress.
        //(For example bit 0: 11111110 & 11111111 = 11111110).
        int result = bitMask & regFileAddrValue;

        //Write result back into fileregister.
        Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 58
     * 
     * If bit ’b’ in register ’f’ is ’1’ then the next
     * instruction is executed.
     * If bit ’b’, in register ’f’, is ’0’ then the next
     * instruction is discarded, and a NOP is
     * executed instead, making this a 2TCY
     * instruction.
     */
    public void BTFSC(int bitaddress, int registerFileAddress)
    {
        //Get bitmask to AND with fileaddress to get bit.
        int bitMask = bitMaskSetBitArray[bitaddress];
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //If bit at bitaddress is 1, next instruction is executed.
        if ((bitMask & regFileAddrValue) == bitMask)
        {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
        }

        //If bit at bitaddress is 0, next instruction will be replaced by a NOP.
        else
        {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
            //Execute NOP.
            NOP();
        }

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 58
     * 
     * Bit ’b’ in register ’f’ is set.
     */
    public void BSF(int bitaddress, int registerFileAddress) {
        //Get bitmask to OR with fileaddress to set bit.
        int bitMask = bitMaskSetBitArray[bitaddress];
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //Result is bitMask OR file-register-value.
        int result = bitMask | regFileAddrValue;

        //Write result into file-register.
        Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 59
     * 
     * If bit ’b’ in register ’f’ is ’0’ then the next
     * instruction is executed.
     * If bit ’b’ is ’1’, then the next instruction is
     * discarded and a NOP is executed
     * instead, making this a 2TCY instruction.
     */
    public void BTFSS(int bitaddress, int registerFileAddress) {
        //Get bitmask to AND with fileaddress to get bit.
        int bitMask = bitMaskSetBitArray[bitaddress];
        //Get Value of RAM-Bank-RP0Bit Address.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //If bit at bitaddress is 0, next instruction is executed.
        if ((bitMask & regFileAddrValue) != bitMask) {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
        }

        //If bit at bitaddress is 1, next instruction will be replaced by a NOP.
        else {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
            //Execute NOP.
            NOP();
        }

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 59
     * 
     * Call Subroutine. First, return address
     * (PC+1) is pushed onto the stack. The
     * eleven bit immediate address is loaded
     * into PC bits <10:0>. The upper bits of
     * the PC are loaded from PCLATH. CALL
     * is a two cycle instruction.
     */
    public void CALL(int elevenK) {
        //Push next instruction on STACK.
        Stack.pushReturnAdressOnStack(Ram.get_Programcounter() + 1);
        
        //Get upper two bits of PCLATH and shift it by eight.
        int shiftedPCLATH = (Ram.get_PCLATH() & 0b11000) << 8;

        //Create new address out of shifted PCLATH and elevenK.
        int newPC = shiftedPCLATH | elevenK;

        //Set Programmcounter to new address.
        Ram.set_Programcounter(newPC);
        //Increment TMR0 if interbnal instruction cycle assigned to TMR0.
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();


        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 60
     * 
     * The contents of register ’f’ are cleared
     * and the Z bit is set.
     */
    public void CLRF(int registerFileAddress) {
        //Value at fileregisteraddress is set to zero.
        Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), 0);
        //Zeroflag is set.
        Ram.set_Zeroflag(true);
        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 60
     * 
     * W register is cleared. Zero bit (Z) is
     * set.
     */
    public void CLRW() {
        //Set to zero.
        setWRegister(0);
        //Zeroflag is set.
        Ram.set_Zeroflag(true);
        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 60
     * 
     * CLRWDT instruction resets the Watch-
     * dog Timer. It also resets the prescaler
     * of the WDT. Status bits TO and PD are
     * set.
     */
    public void CLRWDT() {
        Ram.set_TimeOutFlag(true);
        Ram.set_PowerDownFlag(true);
        Ram.set_PS0(false);
        Ram.set_PS1(false);
        Ram.set_PS2(false);
        //Programcounter will be incremented by 1.

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 61
     * 
     * The contents of register ’f’ are comple-
     * mented. If ’d’ is 0 the result is stored in
     * W. If ’d’ is 1 the result is stored back in
     * register ’f’.
     */
    public void COMF(int destinationBit, int registerFileAddress) {
        //Get value at registerFileAddress
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        
        //Inverts the value.
        int result = (~regFileAddrValue);

        //If result is zero, zeroflag is set true.
        if (result == 0) {
            Ram.set_Zeroflag(true);
        }

        //If result is not zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }

        result &= 255;

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into fileRegister.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 61
     * 
     * The contents of register ’f’ are decre-
     * mented. If ’d’ is 0 the result is placed in the
     * W register. If ’d’ is 1 the result is placed
     * back in register ’f’.
     * If the result is not 0, the next instruction, is
     * executed. If the result is 0, then a NOP is
     * executed instead making it a 2T CY instruc-
     * tion.
     */
    public void DECFSZ(int destinationBit, int registerFileAddress) {
        //Get value of fileregisteraddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Decrement value by one.
        regFileAddrValue--;

        //If the destinationbit is 0, the decremented value is written into the 
        if (destinationBit == 0) {
            setWRegister(regFileAddrValue);
        }

        //If the destinationbit is 1, the decremented value is written bach into the fileregister.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), regFileAddrValue);
        }

        //If the dekremented value doesn't equal zero, the next instruction is executed.
        if (regFileAddrValue != 0) {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
        }

        //If the dekremented value equals zero, a NOP is executed and the other instruction will be dismissed.
        else {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
            NOP();
        }

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 61
     * 
     * Decrement contents of register ’f’. If ’d’ is 0 the
     * result is stored in the W register. If ’d’ is
     * 1 the result is stored back in register ’f’ .
     */
    public void DECF(int destinationBit, int registerFileAddress) {
        //Get value of fileregisteraddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Decrement value by one.
        regFileAddrValue--;

        //If dekremented value equals zero, zeroflag is set true.
        if (regFileAddrValue == 0) {
            Ram.set_Zeroflag(true);
        }

        //If dekremented value doesn't equal zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }

        //If value gets smaller than zero it is AND with 255 to become positive, because only values between 0 and 255 are allowed.
        if (regFileAddrValue < 0) {
            regFileAddrValue = regFileAddrValue & 255;
        }

        //If destinationbit equals zero, value is written into 
        if (destinationBit == 0) {
            setWRegister(regFileAddrValue);
        }

        //If destinationbit doesn't equal zero, value is written into fileregister.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), regFileAddrValue);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 62
     * 
     * GOTO is an unconditional branch. The
     * eleven bit immediate value is loaded
     * into PC bits <10:0>. The upper bits of
     * PC are loaded from PCLATH<4:3>.
     * GOTO is a two cycle instruction.
     */
    public void GOTO(int elevenK) {
        //elevenK OR ((PCLATH AND 0b11000) << 8)
        int result = elevenK | ((Ram.get_PCLATH() & 0b11000) << 8);

        Ram.set_Programcounter(result);
        //Increment TMR0 if internal instruction cycle assigned to TMR0.
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 62
     * 
     * The contents of register ’f’ are incre-
     * mented. If ’d’ is 0 the result is placed in
     * the W register. If ’d’ is 1 the result is
     * placed back in register ’f’.
     */
    public void INCF(int destinationBit, int registerFileAddress) {
        //Get value of fileregisteraddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Increment value by one.
        regFileAddrValue++;

        //If value becomes zero, zeroflag is set true.
        if (regFileAddrValue == 0) {
            Ram.set_Zeroflag(true);
        }

        //If value doesn't become zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }

        //If value becomes greater than 255, it will be set to a value between 0 and 255.
        if (regFileAddrValue > 255) {
            regFileAddrValue &= 255;
        }

        //If destinationbit is set 0, value will be written into 
        if (destinationBit == 0) {
            setWRegister(regFileAddrValue);
        }

        //If destinationbit is not 0, value will be written back to RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), regFileAddrValue);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 63
     * 
     * The contents of register ’f’ are incre-
     * mented. If ’d’ is 0 the result is placed in
     * the W register. If ’d’ is 1 the result is
     * placed back in register ’f’.
     * If the result is not 0, the next instruction is
     * executed. If the result is 0, a NOP is exe-
     * cuted instead making it a 2TCY instruc-
     * tion .
     */
    public void INCFSZ(int destinationBit, int registerFileAddress) {
        //Get value of fileregisteraddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Increment value by one.
        regFileAddrValue++;

        //If value increases over uper line, it is set to a value between 0 and 255.
        if (regFileAddrValue > 255) {
            regFileAddrValue &= 255;
        }

        //If destinationbit is zero, the value is written into 
        if (destinationBit == 0) {
            setWRegister(regFileAddrValue);
        }

        //If destinationbit is one, the value is written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), regFileAddrValue);
        }

        //If value is not zero, next instruction will be executed.
        if (regFileAddrValue != 0) {
            //Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();
        }

        //If value is zero, next instruction will not be executed and NOP is executed instead.
        else {
            ///Increment programcounter and TMR0 if assigned to TMR0.
            Ram.inkrement_Programcounter(1, 0); //Kind of call
            if (Ram.get_T0CS() == false)
                Ram.increment_TMR0();

            //Call NOP.
            NOP();
        }

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 63
     * 
     * The contents of the W register is
     * OR’ed with the eight bit literal 'k'. The
     * result is placed in the W register .
     */
    public void IORLW(int eightK) {
        //and eightK are OR'ed and result is written into result.
        int result = get_WRegister() | eightK;

        //If result is zero, zeroflag is set true.
        if (result == 0) {
            Ram.set_Zeroflag(true);
        }

        //If result is not zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }

        //Set to result.
        setWRegister(result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 64
     * 
     * Inclusive OR the W register with contents of
     * register ’f’. If ’d’ is 0 the result is placed in the
     * W register. If ’d’ is 1 the result is placed
     * back in register ’f’.
     */
    public void IORWF(int destinationBit, int registerFileAddress) {
        //Result is value OR fileregister-value.
        int result = get_WRegister() | Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(),
                                                                                                   registerFileAddress);
        //If result is zero, zeroflag is set true.
        if (result == 0) {
            Ram.set_Zeroflag(true);
        }

        //If result is not zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }
        
        //If destinationbit is zero, result is written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is not zero, result is written into fileregister.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 64
     * 
     * The eight bit literal ’k’ is loaded into W
     * register . The don’t cares will assemble
     * as 0’s.
     */
    public void MOVLW(int eightK) {
        //is set to eightK bit literal.
        setWRegister(eightK);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 64
     * 
     * Move does not mean delete at source!
     * The contents of register f is moved to a
     * destination dependant upon the status
     * of d. If d = 0, destination is W register. If
     * d = 1, the destination is file register f
     * itself. d = 1 is useful to test a file regis-
     * ter since status flag Z is affected.
     */
    public void MOVF(int destinationBit, int registerFileAddress) {
        //If destinationbit is zero, the value from fileregister is written to 
        if (destinationBit == 0) {
            setWRegister(Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress));
        }

        //If destinationbit is one, value will still be in fileregister.

        //If value in fileregister is zero, zeroflag is set true.
        if (Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress) == 0) {
            Ram.set_Zeroflag(true);
        }

        //If value in fileregister is not zero, zeroflag is set false.
        else {
            Ram.set_Zeroflag(false);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 64
     * 
     * Move data from W register to file register
     */
    public void MOVWF(int registerFileAddress) {
        //Data from is moved to fileregister.
        Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), get_WRegister());

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 65
     * 
     * No operation
     */
    public void NOP() {
        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 65
     * 
     * Return from Interrupt. Stack is POPed
     * and Top of Stack (TOS) is loaded in the
     * PC. Interrupts are enabled by setting
     * Global Interrupt Enable bit, GIE
     * (INTCON<7>). This is a two cycle
     * instruction.
     */
    public void RETFIE() {
        //Set Global Interrupt Enable Bit true.
        Ram.set_GIE(true);

        //Pop address from STACK.
        int returnAddress = Stack.popReturnAdressFromStack();

        //Write returnAddress into programcounter.
        Ram.set_Programcounter(returnAddress);
        //Increment TMR0 if assigned to TMR0.
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**OPTION
     * Datasheet Page 65
     */
    public void OPTION() {
        //Nothing happens, only programmcounter gets incremented by 1.

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }
    

    /**
     * Datasheet Page 66
     * 
     * The W register is loaded with the eight
     * bit literal ’k’. The program counter is
     * loaded from the top of the stack (the
     * return address). This is a two cycle
     * instruction.
     */
    public void RETLW(int eightK) {
        //Load with eight bit literal.
        setWRegister(eightK);

        //Pop address from STACK.
        int returnAddress = Stack.popReturnAdressFromStack();

        //Write returnAddress into programcounter.
        Ram.set_Programcounter(returnAddress);
        //Increment TMR0 if assigned to TMR0.
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 66
     * 
     * Return from subroutine. The stack is
     * POPed and the top of the stack (TOS)
     * is loaded into the program counter. This
     * is a two cycle instruction.
     */
    public void RETURN() {
        //Pop address from STACK.
        int returnAddress = Stack.popReturnAdressFromStack();

        //Write returnAddress into programcounter.
        Ram.set_Programcounter(returnAddress);
        //Increment TMR0 if assigned to TMR0.
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 67
     * 
     * The contents of register ’f’ are rotated
     * one bit to the left through the Carry
     * Flag. If ’d’ is 0 the result is placed in the
     * W register. If ’d’ is 1 the result is stored
     * back in register ’f’.
     */
    public void RLF(int destinationBit, int registerFileAddress) {
        //Get value at fileaddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Shift result to left by one bit.
        int result = regFileAddrValue << 1;

        boolean set_Carryflag = false;

        //If the first bit which will be shifted is 1, the Carryflag will be set at the end to true.
        if ((0b10000000 & regFileAddrValue) == 0b10000000) {
            set_Carryflag = true;
        }

        //If carryflag is true, last bit is set 1.
        if (Ram.get_Carryflag() == true) {
            result |= 0b00000001;
        }

        //If carryflag is false, last bit is set 0. 
        else {
            result &= 0b11111110;
        }

        result &= 255;

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Set value of carryflag.
        Ram.set_Carryflag(set_Carryflag);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 67
     * 
     * The contents of register ’f’ are rotated
     * one bit to the right through the Carry
     * Flag. If ’d’ is 0 the result is placed in the
     * W register. If ’d’ is 1 the result is placed
     * back in register ’f’.
     */
    public void RRF(int destinationBit, int registerFileAddress)
    {
        //Get value at fileaddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);
        //Shift result to right by one bit.
        int result = (regFileAddrValue >> 1) & 255;

        boolean set_Carryflag = false;

        //If the first bit which will be shifted is 1, the Carryflag will be set at the end to true.
        if ((0b00000001 & regFileAddrValue) == 0b00000001) {
            set_Carryflag = true;
        }

        //If carryflag is true, first bit is set 1.
        if (Ram.get_Carryflag() == true) {
            result |= 0b10000000;
        }

        //If carryflag is false, first bit is set 0. 
        else {
            result &= 0b01111111;
        }

        //Set value of carryflag.
        Ram.set_Carryflag(set_Carryflag);

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 68
     * TODO
     * The power-down status bit, PD is
     * cleared. Time-out status bit, TO is
     * set. Watchdog Timer and its pres-
     * caler are cleared.
     * The processor is put into SLEEP
     * mode with the oscillator stopped. See
     * Section 14.8 for more details.
     */
    public void SLEEP() {
        //TODO pause running (no Thread.sleep()!!!)
        //Not implemented 
        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 68
     * 
     * The contents of W register is subtracted (2’s comple-
     * ment method) from the eight bit literal 'k'.
     * The result is placed in the W register.
     */
    public void SUBLW(int eightK) {
        int wRegValue = get_WRegister();

        //Build Two's complement
        //eightK = (eightK ^ 0b11111111) + 1;

        //Build Two's complement
        wRegValue = (wRegValue ^ 0b11111111) + 1;

        //Add both complemented values.
        int result = eightK + wRegValue;

        int carry = (result & 0b100000000) >> 8;

        //The lower four bits are bitwise and with 1111b (15d).
        int dcResult = (eightK & 15) + (wRegValue & 15) + 1;

        //If the result is greater than 15, DC-Flag is set true.
        if (dcResult > 15) {
            Ram.set_Digitcarryflag(true);
        }

        //If the result is smaller or equal to 15, DC-Flag is set false.
        else {
            Ram.set_Digitcarryflag(false);
        }

        if (result == 0) {
            Ram.set_Zeroflag(true);
        } else {
            Ram.set_Zeroflag(false);
        }

        if (carry == 1) {
            Ram.set_Carryflag(true);
        } else {
            Ram.set_Carryflag(false);
        }

        result &= 255;

        setWRegister(result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 69
     * 
     * Subtract (2’s complement method) contents
     * of W register from register 'f'. If 'd' is 0 the
     * result is stored in the W register. If 'd' is 1 the
     * result is stored back in register 'f'.
     */
    public void SUBWF(int destinationBit, int registerFileAddress) {

        int wRegValue = get_WRegister();

        //Get value at fileaddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        //Build Two's complement
        //regFileAddrValue = (regFileAddrValue ^ 0b11111111) + 1;

        //Build Two's complement
        wRegValue = (wRegValue ^ 0b11111111) + 1;

        //Add both complemented values.
        int result = regFileAddrValue + wRegValue;

        int carry = (result & 0b100000000) >> 8;

        //The lower four bits are bitwise and with 1111b (15d).
        int dcResult = (regFileAddrValue & 15) + (wRegValue & 15) + 1;

        //If the result is greater than 15, DC-Flag is set true.
        if (dcResult > 15)
        {
            Ram.set_Digitcarryflag(true);
        }

        //If the result is smaller or equal to 15, DC-Flag is set false.
        else {
            Ram.set_Digitcarryflag(false);
        }

        //Set/clear zeroflag
        if (result == 0) {
            Ram.set_Zeroflag(true);
        } else {
            Ram.set_Zeroflag(false);
        }

        //Set/clear carryflag
        if (carry == 1) {
            Ram.set_Carryflag(true);
        } else {
            Ram.set_Carryflag(false);
        }

        result &= 255;

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 69
     * 
     * The upper and lower nibbles of contents of
     * register 'f' are exchanged. If 'd' is 0 the result
     * is placed in W register. If 'd' is 1 the result
     * is placed in register 'f'.
     */
    public void SWAPF(int destinationBit, int registerFileAddress) {
        //Get value at fileaddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        int lowerNibble = regFileAddrValue & 0b00001111;

        int higherNibble = (regFileAddrValue & 0b11110000) >> 4;

        int result = (lowerNibble << 4) | higherNibble;

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 69
     * 
     * The instruction is supported for code
     * compatibility with the PIC16C5X prod-
     * ucts. Since TRIS registers are read-
     * able and writable, the user can directly
     * address them.
     * 
     * To maintain upward compatibility
     * with future PIC16CXX products,
     * do not use this instruction!
     */
    public void TRIS() {
        //Nothing happens, only programcounter will be incremented by 1.

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 70
     * 
     * The contents of the W register are
     * XOR’ed with the eight bit literal 'k'.
     * The result is placed in the W register.
     */
    public void XORLW(int eightK) {
        int wRegValue = get_WRegister();

        int result = wRegValue ^ eightK;

        if (result == 0) {
            Ram.set_Zeroflag(true);
        }

        else {
            Ram.set_Zeroflag(false);
        }

        setWRegister(result);

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }

    /**
     * Datasheet Page 70
     * 
     * Exclusive OR the contents of the W
     * register with contents of register 'f'. If 'd' is
     * 0 the result is stored in the W register. If 'd' is
     * 1 the result is stored back in register 'f'.
     */
    public void XORWF(int destinationBit, int registerFileAddress) {
        int wRegValue = get_WRegister();

        //Get value at fileaddress.
        int regFileAddrValue = Ram.get_Value_Of_Bank_RP0_Bit_Of_Element_At_Adress(Ram.get_RP0Bit(), registerFileAddress);

        int result = wRegValue ^ regFileAddrValue;

        if (result == 0) {
            Ram.set_Zeroflag(true);
        }

        else {
            Ram.set_Zeroflag(false);
        }

        //If destinationbit is zero, result will be written into 
        if (destinationBit == 0) {
            setWRegister(result);
        }

        //If destinationbit is one, result will be written into RAM.
        else {
            Ram.set_Element_X_Of_Bank_Y_To_Z(registerFileAddress, Ram.get_RP0Bit(), result);
        }

        //Increment programcounter and TMR0 if assigned to TMR0.
        Ram.inkrement_Programcounter(1, 0); //Kind of call
        if (Ram.get_T0CS() == false)
            Ram.increment_TMR0();

        //Each Instruction has to split Programmcounter to PC and PCLATH because Ram can't see RAM.
        Ram.set_PCL(Ram.get_Programcounter() & 0b0000011111111);
    }
}