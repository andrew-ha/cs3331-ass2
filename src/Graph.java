/**
 * Created by andrewha on 10/17/17.
 */
import java.io.*;
import java.util.Scanner;

public class Graph {



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
