/**
 * @author Aaron Moser
 */
package Model.Microcontroller;

public class EepromThread extends Thread {

    private Eeprom oEeprom;
    private int iMethodCall = 0;

    public EepromThread(Eeprom oEeprom, int iMethodToCall) {
        this.oEeprom = oEeprom;
        iMethodCall = iMethodToCall;
    }

    @Override
    public void run() {
        switch (iMethodCall) {
            case 1: {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oEeprom.writeToFile();
            }break;
            case 2: {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oEeprom.readFromFile();
            }break;
        }
    }
}
