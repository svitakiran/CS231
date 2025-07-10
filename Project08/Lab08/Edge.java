/*
 * 
*/

import java.util.ArrayList;

public class Edge {
    private Vertex u;
    private Vertex v;
    private double distance;
    ArrayList<Edge> edges = new ArrayList<>();
    
    public Edge(Vertex u, Vertex v, double distance) {
        this.u = u;
        this.v = v;
        this.distance = distance;
        edges = new ArrayList<>();
    }

    public double distance() {
        return distance;
    }

    public Vertex other(Vertex vertex) {
        if (u == vertex) {
            return v;
        } else if (v == vertex) {
            return u;
        } else {
            return null;
        }
    }

    public Vertex[] vertices() {
        Vertex[] pair = {u, v};
        return pair;
    }
}
