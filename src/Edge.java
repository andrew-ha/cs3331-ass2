
public class Edge {

    private String start;
    private String dest;
    private int propDelay;
    private int simVirtualCircuitCapacity;

    public Edge(String start, String dest, Integer propDelay, int simVirtualCircuitCapacity) {
        this.start = start;
        this.dest = dest;
        this.propDelay = propDelay;
        this.simVirtualCircuitCapacity = simVirtualCircuitCapacity;
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
}
