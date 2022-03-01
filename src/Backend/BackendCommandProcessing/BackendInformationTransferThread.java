package Backend.BackendCommandProcessing;

//package SIMULATOR;

import java.util.concurrent.ConcurrentLinkedQueue;

import java.io.*;

public class BackendInformationTransferThread extends Thread {

    private static File fileBackendToFrontend = new File("../SIMULATOR_DAT/gui_set.dat");

    private ConcurrentLinkedQueue<String> queue;

    private int iSleepTime = 100;

    public BackendInformationTransferThread(ConcurrentLinkedQueue<String> outputQueue) {
        queue = outputQueue;
    }

    private int writeOutputFile() {
        int iSuccess = 0;
        if(!queue.isEmpty()) {
            String sPICInformation = queue.poll();
            FileWriter fw;
            try {
                fw = new FileWriter(fileBackendToFrontend);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(sPICInformation);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            iSuccess = 1;
        }
        return iSuccess;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(iSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeOutputFile();
        }
    }
}
