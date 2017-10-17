/**
 * Created by andrewha on 10/17/17.
 */
public class Stats {

    private int numOfRequests;
    private int numOfPackets;
    private int numOfSuccessPackets;
    private int numOfBlockedPackets;
    private int numOfHopsTotal;
    private int propDelayTotal;
    private int numOfSuccessCiruits;

    public Stats() {

        numOfRequests = 0;
        numOfPackets = 0;
        numOfSuccessPackets = 0;
        numOfBlockedPackets= 0;
        numOfHopsTotal = 0;
        propDelayTotal = 0;
        numOfSuccessCiruits = 0;

    }

    public void newRequest() {
        numOfRequests++;
    }

    public void newPacket() {
        numOfPackets++;
    }

    public void packetSuccess() {
        numOfSuccessPackets++;
    }

    public void packetBlocked() {
        numOfBlockedPackets++;
    }

    public void hopPerformed() {
        numOfHopsTotal++;
    }

    public void addPropDelay(int propDelay) {
        propDelayTotal += propDelay;
    }

    public void successCircuits() {
        numOfSuccessCiruits++;
    }

}