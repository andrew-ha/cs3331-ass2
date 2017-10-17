
public class Edge {

    private Node startNode;
    private Node destNode;
    private int propDelay;
    private int simVirtualCircuitCapacity;

    public Edge(Node startNode, Node destNode, Integer propDelay, int simVirtualCircuitCapacity) {
        this.startNode = startNode;
        this.destNode = destNode;
        this.propDelay = propDelay;
        this.simVirtualCircuitCapacity = simVirtualCircuitCapacity;
    }


    public Node getStartNode() {
        return startNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public Integer getPropDelay() {
        return propDelay;
    }

    public int getSimVirtualCircuitCapacity() {
        return simVirtualCircuitCapacity;
    }
}
