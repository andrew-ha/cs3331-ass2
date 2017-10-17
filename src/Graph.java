import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private HashMap<String, Node> nodeMap;
    private HashMap<Node, HashMap<Node, Edge>> adjMatrix;

    public Graph() {
        nodeMap = new HashMap<>();
        adjMatrix = new HashMap<>();
    }

    public void addNode(String name) {

        Node newNode = new Node(name);
        nodeMap.put(name, newNode);

        HashMap<Node, Edge> newHashMap = new HashMap<>();
        adjMatrix.put(newNode, newHashMap);

    }

    public void addEdge(String startNodeName, String destNodeName, int propDelay, int simVirtualCircuitCapacity) {

        Node startNode = nodeMap.get(startNodeName);
        Node destNode = nodeMap.get(destNodeName);
        Edge newEdge = new Edge(startNode, destNode, propDelay, simVirtualCircuitCapacity);

        HashMap<Node, Edge> startNodeAdj = adjMatrix.get(startNode);
        startNodeAdj.put(destNode, newEdge);

    }

}
