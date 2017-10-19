import java.util.*;

public class Request {
    private double time;
    private ArrayList<Edge> edges;
    private boolean isEstablish;

    public Request(double time, ArrayList<Edge> edges, boolean isEstablish) {
        this.time = time;
        this.edges = edges;
        this.isEstablish = isEstablish;
    }

    public double getTime() {
        return time;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public boolean isEstablish() {
        return isEstablish;
    }
}
