package Model.Microcontroller;
/**
 * @author Aaron Moser
 * @date 23.06.2021
 */

 /**
  * Electrical Eraseable Programmable Read Only Memory of the PIC (Programmspeicher)
  */
public class EEPROM
{
    private int[][] eeprom;
    private int eepromLength = 1024;

    private int[] aiProgramLines;

    public EEPROM()
    {
        eeprom = new int[eepromLength][2];
    }

    public EEPROM(int iLength) {
        eeprom = new int[iLength][2];
    }

    /**
     * Returns array of program lines in data
     * @return
     */
    public int[] getProgramLines() {
        return aiProgramLines;
    }

    /**
     * Return element i of program lines in data
     * @param i
     * @return
     */
    public int getProgramLine(int i) {
        return aiProgramLines[i];
    }

    /**
     * Sets program lines of data
     * @param aiProgLines
     */
    public void setProgramLines(int[] aiProgLines) {
        aiProgramLines = aiProgLines;
    }

    /**
     * Returns element of index element in eeprom-array.
     * @param element index of array.
     * @return element at index.
     */
    public int getElement(int index) {
        int returnValue = -1;
        if (index >= 0 && index < 1024) {
            returnValue = eeprom[index][0];
        }
        return returnValue;
    }

    /**
     * Returns index of line at data lines
     * @param iIndex
     * @return
     */
    public int getIndex(int iIndex) {
        int returnValue = -1;
        if (iIndex >= 0 && iIndex < 1024) {
            returnValue = eeprom[iIndex][1];
        }
        return returnValue;
    }

    /**
     * Returns length of EEPROM
     */
    public int getLengthEEPROM() {
        return eepromLength;
    }

    /**
     * Set element to value.
     * @param index of element.
     * @param value of what the element is set to.
     * @return true if set worked.
     */
    public boolean setElementXToValueY(int index, int value) {
        boolean bSetWorked = false;
        if ((index >= 0) && (index < 1024)) {
            eeprom[index][0] = value;
            bSetWorked = true;
        }
        return bSetWorked;
    }  

    /**
     * 
     * @param iElement
     * @param iIndex
     * @return
     */
    public boolean setElementXToIndexY(int iElement, int iIndex) {
        boolean bSetWorked = false;
        if ((iElement >= 0) && (iElement < 1024)) {
            eeprom[iElement][1] = iIndex;
            bSetWorked = true;
        }
        return bSetWorked;
    }
}
