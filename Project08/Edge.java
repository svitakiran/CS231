/*
 * The purpose of this class is to create the edges used in the program.
 * Author: Svita Kiran
 * Date: 5/10/24
*/

import java.util.ArrayList;

public class Edge {
    private Vertex u;
    private Vertex v;
    private double dist;

    ArrayList<Edge> edges = new ArrayList<>();

    /*constructs an Edge consisting of the two vertices with a 
    distance of the given distance */
    public Edge(Vertex u, Vertex v, double dist) {
        edges = new ArrayList<>();
        this.u = u;
        this.v = v;
        this.dist = dist;
    }

    /*returns the distance between two points */
    public double distance() {
        return dist;
    }

    /*if vertex is one of the endpoints of this edge, 
    returns the other end point. Otherwise returns null. */
    public Vertex other(Vertex vertex) {
        if (u == vertex) {
            return v;
        } 
        
        else if (v == vertex) {
            return u;
        } 
        
        else {
            return null;
        }
    }

    /*returns an array of the two Vertices comprising this Edge. Order is arbitrary. */
    public Vertex[] vertices() {
        Vertex[] pair = {u, v};
        return pair;
    }
}