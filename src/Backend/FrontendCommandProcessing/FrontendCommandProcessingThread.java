package Backend.FrontendCommandProcessing;

import java.io.File;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Thread-class responsibility is to check every few milliseconds if fileBackendToFrontend exists and if so, then read it and send string through queue to main-thread.
 */
public class FrontendCommandProcessingThread extends Thread {

    private static File fileBackendToFrontend = new File("../SIMULATOR_DAT/gui_set.dat");

    private ConcurrentLinkedQueue<String> queue;

    private int iSleepTime = 100;

    ReadFile readFile = new ReadFile();

    /**
     * 
     * @param inputQueue overhanded from main-method
     */
    public FrontendCommandProcessingThread(ConcurrentLinkedQueue<String> inputQueue) {
        queue = inputQueue;
    }

    /**
     * FrontendCommandProcessingThread-class responsibility is to check every few milliseconds if fileBackendToFrontend exists and if so, then read it and send string through queue to main-thread.
     */
    @Override
    public void run() {

        String sActualCommand = "";

        while (true) {
            try {
                //Sleeps iSleepTime milli seconds 
                Thread.sleep(iSleepTime);
            } catch(InterruptedException e) {
                System.out.println(e);
            }
            if (fileBackendToFrontend.isFile()) {
                sActualCommand = readFile.getActualCommand();
                queue.add(sActualCommand);
            }
        }
    }
}
