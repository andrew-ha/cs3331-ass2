import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

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

    public static void main (String[] args) {
        Graph g = new Graph();

        //Read in the topology
        Scanner sc = null;
        try {
            sc = new Scanner(new File(args[0]));

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                g.addNode(line[0]);
                g.addNode(line[1]);

                g.addEdge(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]));

            }

        } catch (FileNotFoundException e) {
            System.err.println("No topology file inputted");
        } finally {
            if (sc != null) sc.close();
        }
    }
}
