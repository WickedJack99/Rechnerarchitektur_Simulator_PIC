package Model.Backend.Microcontroller;
/**
 * @author Aaron Moser
 * @date 23.06.2021
 */

 /**
  * Electrical Eraseable Programmable Read Only Memory of the PIC (Programmspeicher)
  */
public class EEPROM
{
    private int[] eeprom;
    private int eepromLength = 0;

    public EEPROM()
    {
        eeprom = new int[1024];
    }

    /**
     * Returns element of index element in eeprom-array.
     * @param element index of array.
     * @return element at index.
     */
    public int getElement(int index)
    {
        int returnValue = -1;

        if (index >= 0 && index <= 1024)
        {
            returnValue = eeprom[index];
        }

        return returnValue;
    }

    /**
     * Returns length of EEPROM
     */
    public int getLengthEEPROM()
    {
        return eepromLength;
    }

    /**
     * Set element to value.
     * @param index of element.
     * @param value of what the element is set to.
     * @return true if set worked.
     */
    public boolean setElementXToValueY(int index, int value)
    {
        boolean setWorked = false;

        if ((index >= 0) && (index <= 1024))
        {
            eeprom[index] = value;
            setWorked = true;
        }

        return setWorked;
    }

    /**
     * Length of eeprom gets restricted.
     * @param length is new length.
     */
    public void setLengthEEPROM(int length)
    {
        eepromLength = length;
    }    
}
