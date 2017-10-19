import javafx.scene.layout.Priority;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();

        // list of requests to be made from the workload file
        PriorityQueue<Request> listOfRequests = new PriorityQueue<>();
        // statistics for the SHP request
        Stats shpStats = new Stats();

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
            System.exit(0);
        }

        // Read in the workload file
        try {
            sc = new Scanner(new File(args[1]));

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                String source = line[1];
                String destination = line[2];

                Float timeStart = Float.parseFloat(line[0]);
                Float duration = Float.parseFloat(line[3]);
                Stats stats = new Stats();

                // Get path using chosen protocol
                ArrayList<Edge> path = g.shortestHopPath(stats, source, destination);

                //Insert path into Request constructor and add to a Priority Queue
                Request newStartRequest = new Request(timeStart, path, true);
                Request newEndRequest = new Request(timeStart + duration, path, false);
                listOfRequests.add(newStartRequest);
                listOfRequests.add(newEndRequest);

            }

            boolean connectionSuccess = true;

            // While PQueue is not empty process the Results
            while (!listOfRequests.isEmpty()) {

                // take the earliest request
                Request currRequest = listOfRequests.poll();

                // for each edge in the request
                for (Edge currEdge : currRequest.getEdges()) {

                    // if the request is to establish a connection
                    if (currRequest.isEstablish()) {

                        // try to add the connection
                        connectionSuccess = currEdge.updateCurrConnections(true);
                        if (connectionSuccess) {
                            shpStats.recordSuccessfulRequest();
                        } else {
                            shpStats.recordBlockedRequest();
                            break;
                        }

                    } else {
                        // remove a connection
                        connectionSuccess = currEdge.checkConnectionValid(false);
                    }

                }

            }

            shpStats.printStats();


        } catch (FileNotFoundException e) {
            System.err.println("No workload file inputted");
            System.exit(1);

        } finally {
            if (sc != null) sc.close();
        }
    }
}

