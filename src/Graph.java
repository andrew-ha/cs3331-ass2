import java.util.*;

public class Graph {

    private HashMap<String, HashMap<String, Edge>> adjMatrix;

    public Graph() {
        adjMatrix = new HashMap<>();
    }

    public void addNode(String name) {
        HashMap<String, Edge> newHashMap = new HashMap<>();
        if (!adjMatrix.containsKey(name)) {
            adjMatrix.put(name, newHashMap);
        }
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

    public ArrayList<Edge> shortestHopPath(Stats stats, String src, String dst) {
        // Create queues and maps for toVisit, distances from source, and predecessors
        PriorityQueue<String> toVisit = new PriorityQueue<>();
        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, String> pred = new HashMap<>();
        ArrayList<String> seen = new ArrayList<>();

        for (String node : adjMatrix.keySet()) {
            dist.put(node, Integer.MAX_VALUE);  // Initialise distance from source to nodes as max
            pred.put(node, null);               // Initialise previous nodes in the optimal path to be null
        }

        dist.put(src, 0);                   // Distance from source to source is zero;
        toVisit.add(src);                   // Add the src to our toVisit queue

        //Put in tuple of the node (String) and its distance from the source (
        // Need to implement sorting by dist hashMap
        while (!toVisit.isEmpty()) {
            String curr = toVisit.poll();
            seen.add(curr);

            if (curr.equals(dst)) break;

            // Check each neighbouring node of curr
            for (String neighbour : adjMatrix.get(curr).keySet()) {
                int distance = dist.get(curr) + 1 ;
//                System.out.println("FROM " + curr + " " + distance + " to " + neighbour);
                // If a shorter path to this neighbour exists
                // Update its distance and its predecessor
                if (distance < dist.get(neighbour))  {
                    dist.put(neighbour, distance);
                    pred.put(neighbour, curr);
                }

                //If it has not been visited then add it to the
                if (!seen.contains(neighbour)) {
                    toVisit.add(neighbour);
                }
            }
        }

        // Now go through your predecessors from dst to src and chuck edges in a list in the right order
        ArrayList<Edge> path = new ArrayList<>();
        Stack<Edge> temp = new Stack<>();

        String curr = dst;
        while (!curr.equals(src)) {
            Edge e = getEdge(curr, pred.get(curr));
//            System.out.println(curr + " " + pred.get(curr));
            temp.push(e);
            curr = pred.get(curr);
        }
        while (!temp.isEmpty()) {
            path.add(temp.pop());
        }
        return path;
    }
}
