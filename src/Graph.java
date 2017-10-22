import java.awt.geom.RoundRectangle2D;
import java.nio.channels.FileLock;
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
        PriorityQueue<QueueNode> toVisit = new PriorityQueue<>();
        HashMap<QueueNode, QueueNode> pred = new HashMap<>();
        HashMap<String, QueueNode> nodeList = new HashMap<>();

        for (String node : adjMatrix.keySet()) {
            // Initialise distance from source to nodes as max
            QueueNode qNode = new QueueNode(node, Integer.MAX_VALUE);
            // Initialise previous nodes in the optimal path to be null
            pred.put(qNode, null);
            nodeList.put(node, qNode);
        }
        // Set the source node distance value to 0 and add it to the toVisit Queue
        QueueNode srcNode = nodeList.get(src);
        srcNode.setVal(0);
        toVisit.add(srcNode);
        while (!toVisit.isEmpty()) {
            QueueNode currNode = toVisit.poll();
            if (currNode.getName().equals(dst)) {
                break;
            }
            // Check each neighbouring node of curr
            for (String neighbour : adjMatrix.get(currNode.getName()).keySet()) {
                QueueNode neighbourNode = nodeList.get(neighbour);
                float distance = currNode.getVal() + 1 ;
                // If a shorter path to this neighbour exists
                // Update its distance and its predecessor
                if (distance < neighbourNode.getVal())  {
                    neighbourNode.setVal(distance);
                    pred.put(neighbourNode, currNode);
                    toVisit.add(neighbourNode);
                }
            }
        }

        // Now go through your predecessors from dst to src and chuck edges in a list in the right order
        ArrayList<Edge> path = new ArrayList<>();
        Stack<Edge> temp = new Stack<>();

        QueueNode curr = nodeList.get(dst);
        while (curr != (nodeList.get(src))) {
            Edge e = getEdge(pred.get(curr).getName(), curr.getName());
            temp.push(e);
            curr = pred.get(curr);
        }
        while (!temp.isEmpty()) {
            Edge e = temp.pop();
//            System.out.print(e.getStartNode() + " ---> " + e.getDestNode());
            path.add(e);
        }
        return path;
    }

    public ArrayList<Edge> shortestDelayPath(Stats stats, String src, String dst) {
        // Create queues and maps for toVisit, distances from source, and predecessors
        PriorityQueue<QueueNode> toVisit = new PriorityQueue<>();
        HashMap<QueueNode, QueueNode> pred = new HashMap<>();
        HashMap<String, QueueNode> nodeList = new HashMap<>();

        for (String node : adjMatrix.keySet()) {
            // Initialise distance from source to nodes as max
            QueueNode qNode = new QueueNode(node, Integer.MAX_VALUE);
            // Initialise previous nodes in the optimal path to be null
            pred.put(qNode, null);
            nodeList.put(node, qNode);
        }
        // Set the source node distance value to 0 and add it to the toVisit Queue
        QueueNode srcNode = nodeList.get(src);
        srcNode.setVal(0);
        toVisit.add(srcNode);
        while (!toVisit.isEmpty()) {
            QueueNode currNode = toVisit.poll();
            if (currNode.getName().equals(dst)) {
                break;
            }
            // Check each neighbouring node of curr and get the load on each edge
            for (String neighbour : adjMatrix.get(currNode.getName()).keySet()) {
                QueueNode neighbourNode = nodeList.get(neighbour);
                Edge neighbourEdge = getEdge(currNode.getName(), neighbour);
                float distance = currNode.getVal() + neighbourEdge.getPropDelay();
                // If a shorter path to this neighbour exists
                // Update its distance and its predecessor
                if (distance < neighbourNode.getVal())  {
                    neighbourNode.setVal(distance);
                    pred.put(neighbourNode, currNode);
//                    System.out.println("adding");
                    toVisit.add(neighbourNode);
                }
            }
        }

        // Now go through your predecessors from dst to src and chuck edges in a list in the right order
        ArrayList<Edge> path = new ArrayList<>();
        Stack<Edge> temp = new Stack<>();

        QueueNode curr = nodeList.get(dst);
        while (curr != (nodeList.get(src))) {
            Edge e = getEdge(pred.get(curr).getName(), curr.getName());
            temp.push(e);
            curr = pred.get(curr);
        }
        while (!temp.isEmpty()) {
            Edge e = temp.pop();
            path.add(e);
        }
        return path;
    }

    public ArrayList<Edge> leastLoadedPath(Stats stats, String src, String dst) {
        // Create queues and maps for toVisit, distances from source, and predecessors
        PriorityQueue<QueueNode> toVisit = new PriorityQueue<>();
        HashMap<QueueNode, QueueNode> pred = new HashMap<>();
        HashMap<String, QueueNode> nodeList = new HashMap<>();

        for (String node : adjMatrix.keySet()) {
            // Initialise distance from source to nodes as max
            QueueNode qNode = new QueueNode(node, Integer.MAX_VALUE);
            // Initialise previous nodes in the optimal path to be null
            pred.put(qNode, null);
            nodeList.put(node, qNode);
        }
        // Set the source node distance value to 0 and add it to the toVisit Queue
        QueueNode srcNode = nodeList.get(src);
        srcNode.setVal(0);
        toVisit.add(srcNode);
        while (!toVisit.isEmpty()) {
            QueueNode currNode = toVisit.poll();
            if (currNode.getName().equals(dst)) {
                break;
            }
            // Check each neighbouring node of curr
            for (String neighbour : adjMatrix.get(currNode.getName()).keySet()) {
                QueueNode neighbourNode = nodeList.get(neighbour);
                Edge neighbourEdge = getEdge(currNode.getName(), neighbour);
                float distance = currNode.getVal() + neighbourEdge.getEdgeLoad();
                // If a shorter path to this neighbour exists
                // Update its distance and its predecessor
                if (distance < neighbourNode.getVal())  {
                    neighbourNode.setVal(distance);
                    pred.put(neighbourNode, currNode);
                    toVisit.add(neighbourNode);
                }
            }
        }

        // Now go through your predecessors from dst to src and chuck edges in a list in the right order
        ArrayList<Edge> path = new ArrayList<>();
        Stack<Edge> temp = new Stack<>();

        QueueNode curr = nodeList.get(dst);
        while (curr != (nodeList.get(src))) {
            Edge e = getEdge(pred.get(curr).getName(), curr.getName());
            temp.push(e);
            curr = pred.get(curr);
        }
        while (!temp.isEmpty()) {
            Edge e = temp.pop();
            path.add(e);
        }
        return path;
    }
}
