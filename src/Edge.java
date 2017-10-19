
public class Edge {

    private String start;
    private String dest;
    private int propDelay;
    private int simVirtualCircuitCapacity;
    private int currConnections;

    public Edge(String start, String dest, Integer propDelay, int simVirtualCircuitCapacity) {
        this.start = start;
        this.dest = dest;
        this.propDelay = propDelay;
        this.simVirtualCircuitCapacity = simVirtualCircuitCapacity;
        this.currConnections = 0;
    }


    public String getStartNode() {
        return start;
    }

    public String getDestNode() {
        return dest;
    }

    public Integer getPropDelay() {
        return propDelay;
    }

    public int getSimVirtualCircuitCapacity() {
        return simVirtualCircuitCapacity;
    }

    public boolean updateCurrConnections(boolean isEstablish) {

        if (isEstablish) {

            if (currConnections < simVirtualCircuitCapacity) {
                this.currConnections++;
                return true;
            }

        } else {
            this.currConnections--;
        }

        return false;
    }

}
