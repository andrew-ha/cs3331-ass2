import java.io.*;
import java.util.*;

public class RoutingPerformance {

    public static void main(String[] args) {
        Graph g = new Graph();
        String NETWORK_SCHEME = args[0];
        String ROUTING_SCHEME = args[1];
        int PACKET_RATE = Integer.parseInt(args[4]);
        // list of requests to be made from the workload file
        PriorityQueue<Request> listOfRequests = new PriorityQueue<>();
        // statistics for the SHP request
        Stats shpStats = new Stats();

        //Read in the topology
        Scanner sc = null;
        try {
            sc = new Scanner(new File(args[2]));

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
            sc = new Scanner(new File(args[3]));

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                String source = line[1];
                String destination = line[2];

                Float timeStart = Float.parseFloat(line[0]);
                Float duration = Float.parseFloat(line[3]);
                Stats stats = new Stats();

                // Get path using chosen protocol
                ArrayList<Edge> path = null;
                if (ROUTING_SCHEME.equals("SHP")) {
                    path = g.shortestHopPath(stats, source, destination);
                }
                if (ROUTING_SCHEME.equals("SDP")) {
                    path = g.shortestDelayPath(stats, source, destination);
                }
                if (ROUTING_SCHEME.equals("LLP")) {
                    path = g.leastLoadedPath(stats, source, destination);
                }

                //Insert path into Request constructor and add to a Priority Queue
                // Also put in times and num packets
                Request newStartRequest = new Request(timeStart, path, (int)(PACKET_RATE * duration),true);
                Request newEndRequest = new Request(timeStart + duration, path, (int)(PACKET_RATE * duration),false);
                listOfRequests.add(newStartRequest);
                listOfRequests.add(newEndRequest);

            }

            boolean connectionSuccess = true;

            // While PQueue is not empty process the Results
            while (!listOfRequests.isEmpty()) {

                // take the earliest request
                Request currRequest = listOfRequests.poll();
                boolean requestBlocked = false;

                if (currRequest.isEstablish()) {
                    shpStats.recordNewRequest();
                    shpStats.addTotalPackets(currRequest.getNumPackets());
                }

                // for each edge in the request
                for (Edge currEdge : currRequest.getEdges()) {

                    // if the request is to establish a connection
                    if (currRequest.isEstablish()) {

                        // try to add the connection
                        connectionSuccess = currEdge.updateConnection(true);
                        shpStats.recordHop(false);
                        shpStats.recordPropDelay(currEdge.getPropDelay());
                        if (!connectionSuccess) {
                            requestBlocked = true;
                        }

                        // If the edge is full, raise a flag.
//                        } else {
//                            //shpStats.recordBlockedRequest();
//
//                        }

                    } else {
                        // remove a connection
                        connectionSuccess = currEdge.updateConnection(false);
                        if(!connectionSuccess) requestBlocked = true;
                    }

                }

                // If the connection was blocked go back through the edges and revert changes
                if(requestBlocked) {

                    if (currRequest.isEstablish()) {
                        shpStats.recordBlockedRequest();
                        shpStats.addNumBlockedPackets(currRequest.getNumPackets());
                    }

                    for (Edge currEdge : currRequest.getEdges()) {

                        if(currRequest.isEstablish()) {
                            shpStats.recordHop(true);
                            currEdge.updateConnection(false);
                            shpStats.recordPropDelay(-currEdge.getPropDelay());
                        } else {
                            currEdge.updateConnection(true);
                        }

                    }

                } else {

                    if (currRequest.isEstablish()) {
                        shpStats.recordSuccessfulRequest();
                        shpStats.addRoutedPackets(currRequest.getNumPackets());
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

