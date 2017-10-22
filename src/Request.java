import java.util.*;

public class Request implements Comparable<Request> {
    private float time;
    private ArrayList<Edge> edges;
    private boolean isEstablish;
    private int numPackets;

    public Request(float time, ArrayList<Edge> edges, int numPackets, boolean isEstablish) {
        this.time = time;
        this.edges = edges;
        this.isEstablish = isEstablish;
        this.numPackets = numPackets;
    }

    public float getTime() {
        return time;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public boolean isEstablish() {
        return isEstablish;
    }

    public int getNumPackets() {
        return numPackets;
    }

    @Override
    public int compareTo(Request comparedRequest) {

        if (time < comparedRequest.getTime()) {
            return -1;
        }
        else if (time > comparedRequest.getTime()) {
            return 1;
        }

        // If two values are equal assign them at random
        else {
            Random randomGenerator = new Random();
            if (randomGenerator.nextBoolean()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
