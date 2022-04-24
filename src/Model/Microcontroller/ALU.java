package Model.Microcontroller;

public class ALU {
    private boolean bCarryFlag = false;
    private boolean bDigitCarryFlag = false;
    private boolean bZeroFlag = false;

    /**
     * 
     * @param bI1 input 1 bit (x)
     * @param bI2 input 2 bit (y)
     * @param bOB overflow bit (c in)
     * @return [0] output (s), [1] overflow output (c out)
     * @source https://de.wikipedia.org/wiki/Volladdierer#/media/Datei:Volladdierer_Aufbau_DIN40900.svg
     */
    private boolean[] fullAdder(boolean bI1, boolean bI2, boolean bOB) {
        boolean[] abOutput = new boolean[2];

        abOutput[0] = ((bI1 ^ bI2) ^ bOB);
        abOutput[1] = ((bI1 & bI2) | ((bI1 ^ bI2) & bOB));

        return abOutput;
    }

    /**
     * 
     * @param iValue1
     * @param iWRegister
     * @param bSubtractionBit
     * @return
     */
    public int calcValue(int iValue1, int iWRegister, boolean bSubtractionBit) {
        //Array with booleans which represent the value of value 1 that will be used for calculation
        boolean[] abValue1 = new boolean[8];
        abValue1[0] = ((iValue1 & 0b1) == 0b1);
        abValue1[1] = ((iValue1 & 0b10) == 0b10);
        abValue1[2] = ((iValue1 & 0b100) == 0b100);
        abValue1[3] = ((iValue1 & 0b1000) == 0b1000);
        abValue1[4] = ((iValue1 & 0b10000) == 0b10000);
        abValue1[5] = ((iValue1 & 0b100000) == 0b100000);
        abValue1[6] = ((iValue1 & 0b1000000) == 0b1000000);
        abValue1[7] = ((iValue1 & 0b10000000) == 0b10000000);

        //Change boolean to integer
        int iSubtractionBitAsInt = bSubtractionBit ? 1 : 0;

        //XOR WRegister-value with subtraction-bit
        int iWRegisterXorSB = iWRegister ^ (0b11111111 * iSubtractionBitAsInt);

        //Boolean array which represents the value of the XOR-ed wregister
        boolean[] abValue2 = new boolean[8];
        abValue2[0] = ((iWRegisterXorSB & 0b1) == 0b1);
        abValue2[1] = ((iWRegisterXorSB & 0b10) == 0b10);
        abValue2[2] = ((iWRegisterXorSB & 0b100) == 0b100);
        abValue2[3] = ((iWRegisterXorSB & 0b1000) == 0b1000);
        abValue2[4] = ((iWRegisterXorSB & 0b10000) == 0b10000);
        abValue2[5] = ((iWRegisterXorSB & 0b100000) == 0b100000);
        abValue2[6] = ((iWRegisterXorSB & 0b1000000) == 0b1000000);
        abValue2[7] = ((iWRegisterXorSB & 0b10000000) == 0b10000000);

        //Boolean array which represents the value of the outcome
        boolean[] abOutcome = new boolean[8];

        //Calculate outcome with fulladder
        boolean[] abVals = fullAdder(abValue1[0], abValue2[0], bSubtractionBit);
        abOutcome[0] = abVals[0];

        abVals = fullAdder(abValue1[1], abValue2[1], abVals[1]);
        abOutcome[1] = abVals[0];

        abVals = fullAdder(abValue1[2], abValue2[2], abVals[1]);
        abOutcome[2] = abVals[0];

        abVals = fullAdder(abValue1[3], abValue2[3], abVals[1]);
        abOutcome[3] = abVals[0];

        //Set digitcarry if overflow of lower four bits appers
        bDigitCarryFlag = abVals[1];

        abVals = fullAdder(abValue1[4], abValue2[4], abVals[1]);
        abOutcome[4] = abVals[0];

        abVals = fullAdder(abValue1[5], abValue2[5], abVals[1]);
        abOutcome[5] = abVals[0];

        abVals = fullAdder(abValue1[6], abValue2[6], abVals[1]);
        abOutcome[6] = abVals[0];

        abVals = fullAdder(abValue1[7], abValue2[7], abVals[1]);
        abOutcome[7] = abVals[0];

        //Set carry if overflow of higher four bits appears
        bCarryFlag = abVals[1];

        //Calculate outcome as int
        int iOutcome = (0b1 * (abOutcome[0] ? 1 : 0))
                     + (0b10 * (abOutcome[1] ? 1 : 0))
                     + (0b100 * (abOutcome[2] ? 1 : 0))
                     + (0b1000 * (abOutcome[3] ? 1 : 0))
                     + (0b10000 * (abOutcome[4] ? 1 : 0))
                     + (0b100000 * (abOutcome[5] ? 1 : 0))
                     + (0b1000000 * (abOutcome[6] ? 1 : 0))
                     + (0b10000000 * (abOutcome[7] ? 1 : 0));

        //Check whether outcome is zero or not and set/reset zeroflag
        if (iOutcome > 0) {
            bZeroFlag = false;
        } else {
            bZeroFlag = true;
        }

        return iOutcome;
    }

    public boolean getCarryFlag() {
        return bCarryFlag;
    }

    public boolean getDigitCarryFlag() {
        return bDigitCarryFlag;
    }

    public boolean getZeroFlag() {
        return bZeroFlag;
    }
}