import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {

    private HashMap<String, HashMap<String, Edge>> adjMatrix;

    public Graph() {
        adjMatrix = new HashMap<>();
    }

    public void addNode(String name) {
        HashMap<String, Edge> newHashMap = new HashMap<>();
        adjMatrix.put(name, newHashMap);

    }

    public void addEdge(String start, String dest, int propDelay, int simVirtualCircuitCapacity) {

        Edge startEdge = new Edge(start, dest, propDelay, simVirtualCircuitCapacity);
        Edge endEdge = new Edge(dest, start, propDelay, simVirtualCircuitCapacity);

        HashMap<String, Edge> startNodeAdj = adjMatrix.get(start);
        startNodeAdj.put(dest, startEdge);
        HashMap<String, Edge> destNodeAdj = adjMatrix.get(dest);
        destNodeAdj.put(start, endEdge);
    }

    public Edge getEdge(String src, String dst) {
        return adjMatrix.get(src).get(dst);
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

        dist.put(src, 0);                   // Distance from source to source is zero;

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
