/**
 * @author Aaron Moser
 */
package Model.ProgramLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.Microcontroller.Pic;

public class ReadProgramFile {

    ArrayList<String> dataLines;
    ArrayList<String> oPCode;
    /**
     * Get OP-Code out of String-data-array.
     * @param dataArray String-array where OP-Code is.
     * @return String-array with only OP-Code.
     */
    public void setOPCode(ArrayList<String> data) {

        oPCode = new ArrayList<String>();

        for (int i = 0; i < data.size(); i++) {
            //If element in dataArray at position i is null or a space, this element is not added into the new oPCode-Array.
            if (data.get(i) != null && data.get(i).charAt(0) != 32) {
                oPCode.add(data.get(i));
            }
        }
    }

    public ArrayList<String> getOPCode() {
        return oPCode;
    }

    /**
     * Gets whole data of the file.
     * @param file
     * @throws IOException
     */
    public void setData(File file) {
        //Creates String-arraylist with whole data.
        dataLines = new ArrayList<String>();

        try {
            FileReader fR = new FileReader(file);
            BufferedReader br = new BufferedReader(fR);

            String sLine;
            while ((sLine = br.readLine()) != null) {
                dataLines.add(sLine);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return ArrayList<String> dataLines from current this object.
     */
    public ArrayList<String> getData() {
        return dataLines;
    }

    /**
     * Turns hex-values of an overhanded String-array into an int-array with decimal-values.
     * [0] = memoryCountIndex
     * [1] = commandAsInt
     * [2] = dataLineRepresentationIndex
     * @param oPCode String-array
     * @return int-array
     */
    public int[][] getOPCodeArrayCommandAsInt(ArrayList<String> oPCode) {
        int[][] aiReturnArray = new int[oPCode.size()][3];

        for (int i = 0; i < oPCode.size(); i++) {
            for (int k = 0; k < dataLines.size(); k++)
            if (oPCode.get(i).equals(dataLines.get(k))) {
                aiReturnArray[i][2] = k;
            }
        }

        for (int indexRowOPCode = 0; indexRowOPCode < oPCode.size(); indexRowOPCode++) {
            int indexOPCode = 0;
            int commandAsInt = 0;
            int memoryCount = 0;

            //Stops if second space is reached. (After command is read.)
            while (indexOPCode < 9) {
                switch (indexOPCode) {
                    case 0: {
                        memoryCount = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 0, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[0]);
                    }break;

                    case 1: {
                        memoryCount = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 1, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[0]);
                    }break;

                    case 2: {
                        memoryCount = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 2, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[0]);
                    }break;

                    case 3: {
                        memoryCount = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 3, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[0]);
                    }break;

                    case 5: {
                        commandAsInt = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 5, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[1]);
                    }break;

                    case 6: {
                        commandAsInt = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 6, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[1]);
                    }break;

                    case 7: {
                        commandAsInt = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 7, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[1]);
                    }break;

                    case 8: {
                        commandAsInt = (getOPCodeArrayCommandAsIntSubFunction(oPCode, 8, indexRowOPCode, indexOPCode, memoryCount, commandAsInt)[1]);
                    }break;
                }      

                //Increase location which will be checked by one.
                indexOPCode++;
            }
            aiReturnArray[indexRowOPCode][0] = memoryCount;
            aiReturnArray[indexRowOPCode][1] = commandAsInt;
        }
        return aiReturnArray;
    }

    /**
     * @param iCase Location of the actually checked character.
     * @param iIndexRowOPCode Actual row of oPCode.
     * @param iMemoryCount 
     * @param iCommandAsInt
     * @return Int-Array with memoryCount [0] and commmandAsInt [1].
     */
    public int[] getOPCodeArrayCommandAsIntSubFunction(ArrayList<String> oPCode, int iCase, int iIndexRowOPCode, int iIndexOPCode, int iMemoryCount, int iCommandAsInt) {

        int[] aiCaseArray = {4096, 256, 16, 1, 0, 4096, 256, 16, 1};
        int[] aiResultArray = new int[2];

        int iMemoryCountReturn = iMemoryCount;
        int iCommandAsIntReturn = iCommandAsInt;

        switch (iCase) {
            //0123 0 == 4096 1 == 256 2 == 16 3 == 1
            //0(Ascii: 48) 1(49) ... 9(57) / A(Ascii: 65) B(66) ... F(70)
            //Checks first four characters of one line of the simulation-file which are hex-represented-characters(0-F)
            //These characters are converted into integer-representation to process further.
            case 0: //Fallthrough is wanted
            case 1: //Fallthrough is wanted
            case 2: //Fallthrough is wanted
            case 3: {
                //0-9
                if (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) > 47 && oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) < 58) {
                    iMemoryCountReturn += (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) - 48) * aiCaseArray[iCase];
                }

                //A-F
                else if (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) > 64 && oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) < 71) {
                    iMemoryCountReturn += (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) - 55) * aiCaseArray[iCase];
                }

                //Else
                else {
                    System.out.println("Expected value between 48 and 57 or 65 and 70 but was " + oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode));
                }
            }break;
            
            //Checks 5th, 6th, 7th, 8th character of one line of the simulation-file which are hex-represented-characters(0-F)
            //These characters are converted into integer-representation to process further.
            case 5: //Fallthrough is wanted
            case 6: //Fallthrough is wanted
            case 7: //Fallthrough is wanted
            case 8: {
                //0-9
                if (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) > 47 && oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) < 58) {
                    iCommandAsIntReturn += (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) - 48) * aiCaseArray[iCase];
                }

                //A-F
                else if (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) > 64 && oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) < 71) {
                    iCommandAsIntReturn += (oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode) - 55) * aiCaseArray[iCase];
                }

                //Else
                else {
                    System.out.println("Expected value between 48 and 57 or 65 and 70 but was " + oPCode.get(iIndexRowOPCode).charAt(iIndexOPCode));
                }
            }break;
        }

        aiResultArray[0] = iMemoryCountReturn;
        aiResultArray[1] = iCommandAsIntReturn;

        return aiResultArray;
    }

    /**
     * Reads file and writes instructions to EEPROM.
     * @param files String-Array with paths of files.
     * @param k index of file in String-Array "files".
     * @param oPIC of the main-function.
     */
    public void readFileAndWriteToEEPROM(Pic oPIC) {
        if (oPCode.size() > 0) {
            //Integer-array which will contain oPCode as int-values.
            //Get an twodimensional array with int-values.
            int[][] oPCodeAsInt = getOPCodeArrayCommandAsInt(oPCode);

            //asCommands are written into EEPROM
            for (int i = 0; i < oPCodeAsInt.length; i++) {
                //The adress where the command will be written in the EEPROM
                int memoryAdress = oPCodeAsInt[i][0];
                //System.out.println(memoryAdress);

                //Command that will be written into the EEPROM
                int command = oPCodeAsInt[i][1];
                //System.out.println(command);

                int iIndex = oPCodeAsInt[i][2];

                //asCommands are written into EEPROM
                oPIC.getEeprom().setElementXToValueY(memoryAdress, command);

                oPIC.getEeprom().setElementXToIndexY(memoryAdress, iIndex);
            }
        } 
    }
}
