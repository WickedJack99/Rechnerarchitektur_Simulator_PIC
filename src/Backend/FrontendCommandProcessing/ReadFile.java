package Backend.FrontendCommandProcessing;
//package SIMULATOR;

import java.io.*;

/**
 * Class reads line out of file.
 */
public class ReadFile {

    private File fileFrontendToBackend = new File("C:/Users/Windows 10/Desktop/Studium/SS_21/Rechnerarchitektur_Projekt/gui_change.dat");
    private String sActualCommand;

    public ReadFile() {}

    /**
     * Resets file from where data is read to new overhanded path.
     * @param fileFrontendToBackend
     */
    public ReadFile(String fileFrontendToBackend) {
        this.fileFrontendToBackend = new File(fileFrontendToBackend);
    }

    /**
     * Reads FrontendToBackend-file and updates (-)sActualCommand.
     * @return 1 at success, -1 at error
     */
    private int readInputFile() {

        int iReturn = 0;
        try {
            FileReader fileReader = new FileReader(fileFrontendToBackend);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            sActualCommand = bufferedReader.readLine();
            bufferedReader.close();
            fileFrontendToBackend.delete();
            iReturn = 1; // success
        } catch (IOException e) {
            iReturn = -1; // error
        }
        return iReturn;
    }

    /**
     * Reads file and returns actual command as string
     * @return command as string at success, ERROR at error
     */
    public String getActualCommand() {
        String line = null;
        int iReadInputFile = readInputFile();
        if (iReadInputFile == 1) {
            line = sActualCommand;
        } else if (iReadInputFile == -1) {
            line = "ERROR FILEREAD";
        }
        return line;
    }
}
