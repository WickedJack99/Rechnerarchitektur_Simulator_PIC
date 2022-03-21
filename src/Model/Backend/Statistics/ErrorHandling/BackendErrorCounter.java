package Model.Backend.Statistics.ErrorHandling;

public class BackendErrorCounter {
    private int iErrorCounter = 0;
    private int [] aiErrors;

    public void incrementErrorCounter() {
        iErrorCounter++;
    }

    public int getErrorCounter() {
        return iErrorCounter;
    }

    //TODO detailed error counts
}
