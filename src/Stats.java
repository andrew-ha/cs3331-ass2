/**
 * Created by andrewha on 10/17/17.
 */
public class Stats {

    private int numOfRequests;
    private int numOfSuccessRequests;
    private int numOfBlockedRequests;
    private int numOfHopsTotal;
    private int propDelayTotal;
    private int numRoutedPackets;
    private int numBlockedPackets;
    private int totalPackets;


    public Stats() {

        numOfRequests = 0;
        numOfSuccessRequests = 0;
        numOfBlockedRequests = 0;
        numOfHopsTotal = 0;
        propDelayTotal = 0;
        numRoutedPackets = 0;
        numBlockedPackets = 0;
        totalPackets = 0;

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

    public int getNumRoutedPackets() {
        return numRoutedPackets;
    }

    public void addRoutedPackets(int numRoutedPackets) {
        this.numRoutedPackets += numRoutedPackets;
    }

    public int getNumBlockedPackets() {
        return numBlockedPackets;
    }

    public void addNumBlockedPackets(int numBlockedPackets) {
        this.numBlockedPackets += numBlockedPackets;
    }

    public int getTotalPackets() {
        return totalPackets;
    }

    public void addTotalPackets(int totalPackets) {
        this.totalPackets += totalPackets;
    }

    public void printStatsOld() {

        System.out.println("number of virtual circuit requests: " + numOfRequests);
        System.out.println("number of successfully routed requests: " + numOfSuccessRequests);
        System.out.println("percentage of routed request: " + ((float)numOfSuccessRequests/numOfRequests)*100);
        System.out.println("number of blocked requests: " + numOfBlockedRequests);
        System.out.println("percentage of blocked request: " + ((float)numOfBlockedRequests/numOfRequests)*100);
        System.out.println("average number of hops per circuit: " + (float)numOfHopsTotal/numOfRequests);
        System.out.println("average cumulative propagation delay per circuit: " + (float)propDelayTotal/numOfRequests);

    }

    public void printStats() {

        System.out.println("number of virtual connection requests: " + numOfRequests);
        System.out.println("total number of packets: " + totalPackets);
        System.out.println("number of successfully routed packets: " + numRoutedPackets);
        System.out.printf("percentage of successfully routed packets: %.2f\n", ((float)numRoutedPackets/totalPackets)*100);
        System.out.println("number of blocked packets: " + numBlockedPackets);
        System.out.printf("percentage of blocked packets: %.2f\n", ((float)numBlockedPackets/totalPackets)*100);
        System.out.printf("average number of hops per circuit: %.2f\n", (float)numOfHopsTotal/numOfSuccessRequests);
        System.out.printf("average cumulative propagation delay per circuit: %.2f\n", (float)propDelayTotal/numOfSuccessRequests);

    }
}