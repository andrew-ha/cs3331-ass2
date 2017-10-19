import java.util.*;

public class Request implements Comparable<Request> {
    private float time;
    private ArrayList<Edge> edges;
    private boolean isEstablish;

    public Request(float time, ArrayList<Edge> edges, boolean isEstablish) {
        this.time = time;
        this.edges = edges;
        this.isEstablish = isEstablish;
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

    @Override
    public int compareTo(Request comparedRequest) {

        return (time < comparedRequest.getTime()) ? 1 : -1;

    }

}
