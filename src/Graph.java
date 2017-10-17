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
        //Read in the topology
        Scanner sc = null;
        try {
            sc = new Scanner(new File(args[0]));

            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.err.println("No topology file inputted");
        } finally {
            if (sc != null) sc.close();
        }
    }
}
