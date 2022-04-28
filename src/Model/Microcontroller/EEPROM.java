package Model.Microcontroller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EEPROM {

    private int[] aiEeprom;
    private File oFile;

    public EEPROM() {
        aiEeprom = new int[64];
        oFile = new File("./eeprom.dat");
    }

    public void setElementXOfEepromToY(int iX, int iY) {
        aiEeprom[iX] = iY;
    }

    public void setEeprom(int[] aiNewEeprom) {
        aiEeprom = aiNewEeprom;
    }

    public int[] getEeprom() {
        return aiEeprom;
    }

    public void changeEepromLocation(String sLocation) {
        oFile = new File(sLocation + "eeprom.dat");
    }

    public boolean writeToFile() {
        boolean bSuccess = false;
        try {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileWriter fw = new FileWriter(oFile);
            BufferedWriter bw = new BufferedWriter(fw);
            String sLine;
            for (int i = 0; i < aiEeprom.length; i++) {
                sLine = i + " " + aiEeprom[i] + "\n";
                bw.write(sLine);
            }
            bw.close();
            bSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
            bSuccess = false;
        }
        return bSuccess;
    }

    public boolean readFromFile() {
        boolean bSuccess = false;
        try {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileReader fr = new FileReader(oFile);
            BufferedReader br = new BufferedReader(fr);
            String sLine;
            while ((sLine = br.readLine()) != null) {
                int i = Integer.parseInt(sLine.substring(0, 1));
                int iValue = Integer.parseInt(sLine.substring(2, 5));
                aiEeprom[i] = iValue;
            }
            br.close();
            bSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
            bSuccess = false;
        }
        return bSuccess;
    }
}