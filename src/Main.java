import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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



            }



        } catch (FileNotFoundException e) {
            System.err.println("No workload file inputted");
            System.exit(1);

        } finally {
            if (sc != null) sc.close();
        }
    }
}

