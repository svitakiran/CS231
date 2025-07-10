/*
 * The purpose of this file is to create the vertices used in the program.
 * Author: Svita Kiran
 * Date: 5/10/24
*/

import java.util.ArrayList;

public class Vertex {
    ArrayList<Edge> edges = new ArrayList<>();
    ArrayList<Vertex> verts = new ArrayList<>();

    public Vertex() {
        edges = new ArrayList<>();
        verts = new ArrayList<>();
    }

    public Edge getEdgeTo(Vertex vertex) {
        if (vertex != null) {
            for(Edge elem : vertex.incidentEdges()) {
                for(Edge elem2 : this.incidentEdges()) {
                    if(elem == elem2) {
                        return elem;
                    }
                }
            }
        }
        return null;
    }

    
    public void addEdge(Edge edge) {
        edges.add(edge);
        verts.add(edge.other(this));  
    }

    public boolean removeEdge(Edge edge) {
        for(Edge elem : edges) {
            if(elem == edge) {
                edges.remove(edge);
                return true;
            }
        }
        return false;
    }

    public Iterable<Vertex> adjacentVertices() {
        return verts;
    }

    public Iterable<Edge> incidentEdges() {
        return edges;
    }
}