/*
 * 
*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Vertex {
    ArrayList<Edge> edges = new ArrayList<>();
    ArrayList<Vertex> vertices = new ArrayList<>();

    public Vertex() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public Edge getEdgeTo(Vertex vertex) {
        if (vertex != null) {
            for (Edge item : vertex.incidentEdges()) {
                for (Edge item2 : this.incidentEdges()) {
                    if (item == item2) {
                        return item;
                    }
                }
            }
        }
        return null;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public boolean removeEdge(Edge edge) {
        for (Edge item : edges) {
            if (item == edge) {
                edges.remove(edge);
                return true;
            }
        }
        return false;
    }

    public Iterable<Vertex> adjacentVertices() {
        return vertices;
    }

    public Iterable<Edge> incidentEdges() {
        return edges;
    }
}
