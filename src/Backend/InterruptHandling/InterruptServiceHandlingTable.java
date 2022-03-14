package Backend.InterruptHandling;

/**
 * Class contains methods which calls a method to an overhanded value.
 */
public class InterruptServiceHandlingTable {

    private final int ISR_TMR0_OVERFLOW = 1; //tmr0 overflow interrupt
    private final int INT_RB0 = 2; //External interrupt over int/rb0-pin
    private final int RB4 = 3; //PortB change interrupts
    private final int RB5 = 4; //PortB change interrupts
    private final int RB6 = 5; //PortB change interrupts
    private final int RB7 = 6; //PortB change interrupts
    private final int EEPROM_DATA_WRITE = 7;

    public InterruptServiceHandlingTable() {}


}
