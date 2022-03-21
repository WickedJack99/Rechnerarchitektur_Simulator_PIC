package Model.Backend.Microcontroller;

/**
 * @author Aaron Moser
 * @date 23.06.2021
 * @lastchange 23.06.2021
 */

//Doesn't work correctly, status is changed too fast.
public class WATCHDOG extends Thread
{
    private long time;
    private int watchdogStatus;
    private long timeStart;
    private long timeEnd;

    /**
     * Constructor of watchdog.
     */
    public WATCHDOG()
    {
        time = 0;
        watchdogStatus = 0;
        timeStart = 0;
        timeEnd = 0;
    }

    /**
     * @return time in microseconds of watchdog.
     */
    public synchronized long getTime()
    {
        return this.time / 100;
    }

    /**
     * Sets time of watchdog.
     * @param value of new time.
     */
    public synchronized void setTime(long value)
    {
        this.time = value;
    }

    /**
     * @return Status of watchdog.
     * 0: Waiting for start
     * 1: Start Watchdog
     * 2: GetTime Watchdog
     * 3: End Watchdog
     * 4: Stop Watchdog
     */
    public synchronized int getWatchdogStatus()
    {
        return this.watchdogStatus;
    }

    /**
     * 0: Waiting for start
     * 1: Start Watchdog
     * 2: GetTime Watchdog
     * 3: End Watchdog
     * 4: Stop Watchdog
     * @param value
     */
    public synchronized void setWatchdogStatus(int value)
    {
        this.watchdogStatus = value;
    }

    /**
     * Makes it possible to create a thread of watchdog.
     */
    @Override
    public void run()
    {
        //If status is changed to 3, watchdog is stopped.
        while (this.watchdogStatus != 3)
        {
            if (watchdogStatus != 4)
            {
                //If status changes to 1, starts to count time and sets status to 4.
                if (this.watchdogStatus == 1)
                {
                    this.timeStart = System.nanoTime();
                    if (this.getWatchdogStatus() != 2)
                    {
                        this.setWatchdogStatus(4);
                    }
                }

                //If status changes to 2, stops count time and sets time to new value, then sets status to 4.
                if (this.watchdogStatus == 2)
                {
                    this.timeEnd = System.nanoTime();
                    if (this.getWatchdogStatus() != 1)
                    {
                        this.setWatchdogStatus(4);
                    }
                    this.setTime(this.timeEnd - this.timeStart);
                }
            }
        }
    }
}