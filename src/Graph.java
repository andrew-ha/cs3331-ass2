import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

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
        Edge startEdge = new Edge(startNode, destNode, propDelay, simVirtualCircuitCapacity);
        Edge endEdge = new Edge(startNode, destNode, propDelay, simVirtualCircuitCapacity);

        HashMap<Node, Edge> startNodeAdj = adjMatrix.get(startNode);
        startNodeAdj.put(destNode, startEdge);
        HashMap<Node, Edge> destNodeAdj = adjMatrix.get(destNode);
        destNodeAdj.put(startNode, endEdge);
    }

    public Edge getEdge(Node src, Node dst) {
        return adjMatrix.get(src).get(dst);
    }

    public Node getNode(String name) {
        return nodeMap.get(name);
    }

    public void shortestHopPath(Stats stats, Node src, Node dst) {
        // Create queues and maps for toVisit, distances from source, and predecessors
        PriorityQueue<Node> toVisit = new PriorityQueue<>();
        HashMap<Node, Integer> dist = new HashMap<>();
        HashMap<Node, Node> pred = new HashMap<>();

        for (Node n : nodeMap.values()) {
           dist.put(n, Integer.MAX_VALUE);  // Initialise distance from source to nodes as max
           pred.put(n, null);               // Initialise previous nodes in the optimal path to be null
           toVisit.add(n);                  // Add the nodes to our toVisit queue
        }

        dist.put(src, 0);                    // Distance from source to source is zero;

        // Need to implement sorting by dist hashMap
        while (!toVisit.isEmpty()) {
            Node curr = toVisit.poll();

            // Check each neighbouring node of curr
            for (Node neighbour : adjMatrix.get(curr).keySet()) {
                int distance = dist.get(curr) + 1 ;

                // If a shorter path to this neighbour exists
                // Update its distance and its predecessor
                if (distance < dist.get(neighbour))  {
                    dist.put(neighbour, distance);
                    pred.put(neighbour, curr);
                }
            }
        }

        // Now go through your predecessors from dst to src and chuck edges in a list
        ArrayList<Edge> path = new ArrayList<Edge>();
        Node curr = dst;
        while (curr != src) {
            Edge e = getEdge(curr, pred.get(curr));
            path.add(e);
        }
    }
}
