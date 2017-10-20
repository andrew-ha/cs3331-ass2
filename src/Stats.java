/**
 * Created by andrewha on 10/17/17.
 */
public class Stats {

    private int numOfRequests;
    private int numOfSuccessRequests;
    private int numOfBlockedRequests;
    private int numOfHopsTotal;
    private int propDelayTotal;

    public Stats() {

        numOfRequests = 0;
        numOfSuccessRequests = 0;
        numOfBlockedRequests = 0;
        numOfHopsTotal = 0;
        propDelayTotal = 0;

    }

    public void recordNewRequest() {
        numOfRequests++;
    }

    public void recordSuccessfulRequest() {
        numOfSuccessRequests++;
    }

    public void recordBlockedRequest() {
        numOfBlockedRequests++;
    }

    public void recordHop(boolean isRevert) {
        if (isRevert) {
            numOfHopsTotal--;
        } else {
            numOfHopsTotal++;
        }
    }

    public void recordPropDelay(int propDelayInstance) {
        propDelayTotal += propDelayInstance;
    }

    public void printStats() {

        System.out.println("number of virtual circuit requests: " + numOfRequests);
        System.out.println("number of successfully routed requests: " + numOfSuccessRequests);
        System.out.println("percentage of routed request: " + ((float)numOfSuccessRequests/numOfRequests)*100);
        System.out.println("number of blocked requests: " + numOfBlockedRequests);
        System.out.println("percentage of blocked request: " + ((float)numOfBlockedRequests/numOfRequests)*100);
        System.out.println("average number of hops per circuit: " + (float)numOfHopsTotal/numOfRequests);
        System.out.println("average cumulative propagation delay per circuit: " + (float)propDelayTotal/numOfRequests);

    }

}