/*
 * The purpose of this class is to create the graph used in the program.
 * Author: Svita Kiran
 * Date: 4/10/24
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

    public class Graph {
        ArrayList<Vertex> verts = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();

        public Graph() {
            edges = new ArrayList<>();
            verts = new ArrayList<>();
        }

        public Graph(int n) {
            this();
            for (int i = 0; i < n; i++) {
                verts.add(new Vertex());
            }
        }

        public Graph(int n, double prob) {
            this();
            Random rand = new Random();

            for (int i = 0; i < n; i++) {
                verts.add(new Vertex());

                for (int j = 0; j < i; j++) {
                    if (rand.nextDouble() < prob) {
                        Edge edge = new Edge(verts.get(i), verts.get(j), 1);
                        edges.add(edge);
                        verts.get(i).addEdge(edge);
                        verts.get(j).addEdge(edge);
                    }
                }
            }
        }
 
        public Graph( String filename ) {

            try {
                //Setup for reading the file
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
    
                //Get the number of vertices from the file and initialize that number of verticies
                verts = new ArrayList() ;
                Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
                for ( int i = 0 ; i < numVertices ; i ++ ) {
                    verts.add( new Vertex() );
                }
    
                //Read in the edges specified by the file and create them
                edges = new ArrayList() ; //If you used a different data structure to store Edges, you'll need to update this line
                String header = br.readLine(); //We don't use the header, but have to read it to skip to the next line
                //Read in all the lines corresponding to edges
                String line = br.readLine();
                   while(line != null){
                       //Parse out the index of the start and end vertices of the edge
                        String[] arr = line.split(",");
                        Integer start = Integer.valueOf( arr[ 0 ] ) ;
                        Integer end = Integer.valueOf( arr[ 1 ] ) ;
                        
                        //Make the edge that starts at start and ends at end with weight 1
                        Edge edge = new Edge( verts.get( start ) , verts.get( end ) , 1. ) ;
                     //Add the edge to the set of edges for each of the vertices
                     verts.get( start ).addEdge( edge ) ;
                    verts.get( end ).addEdge( edge ) ;
                    //Add the edge to the collection of edges in the graph
                    this.edges.add( edge );
                    
                    //Read the next line
                    line = br.readLine();
                }
                br.close();
                System.out.println( this.edges );
              }
              catch(FileNotFoundException ex) {
                System.out.println("Graph constructor:: unable to open file " + filename + ": file not found");
              }
              catch(IOException ex) {
                System.out.println("Graph constructor:: error reading file " + filename);
              }
        }

        public int size() {
            return verts.size();
        }

        public Iterable<Vertex> getVertices() {
            return verts;
        }

        public Iterable<Edge> getEdges() {
            return edges;
        }

        public Vertex addVertex() {
            Vertex newVertex = new Vertex();
            verts.add(newVertex);
            return newVertex;
        }

        public Edge addEdge(Vertex u, Vertex v, double dist) {
            Edge newEdge = new Edge(u, v, dist);
            edges.add(newEdge);
            return new Edge(u, v, dist);
        }

        public Edge getEdge(Vertex u, Vertex v) {
            if(u != null && v != null) {
                return u.getEdgeTo(v);
            }

            return null;
        }

        public boolean remove(Vertex vertex) {
            for(Vertex elem : verts) {
                if(elem == vertex) {
                    verts.remove(elem);
                    return true;
                }
            }

            return false;
        }
        
        public <T> HashMap<Vertex, Double> distanceFrom(Vertex source) {
            HashMap<Vertex, Double> hashMap = new HashMap<>();
            PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<>(new Comparator<Vertex>() {
                public int compare(Vertex vertex1, Vertex vertex2) {
                    return Double.compare(hashMap.get(vertex1), hashMap.get(vertex2));
                }

            }
            );

            if (source == null) {
                System.out.println("source is null");
                return null;
            }

            hashMap.put(source, 0.0);
            vertexPriorityQueue.offer(source);

            if (verts.size() != 0) {
                for(Vertex elem : verts) {
                    if(elem != source) {
                        hashMap.put(elem, Double.POSITIVE_INFINITY);
                        vertexPriorityQueue.offer(elem);
                    }
                }

                while (vertexPriorityQueue.isEmpty() != true) {
                    Vertex next = vertexPriorityQueue.poll();
                    for(Vertex neighbor : next.adjacentVertices()) {
                        Double newDistance = hashMap.get(next) + neighbor.getEdgeTo(next).distance();
                        if(newDistance < hashMap.get(neighbor)) {
                            hashMap.put(neighbor, newDistance);
                            vertexPriorityQueue.remove(next);
                            vertexPriorityQueue.offer(neighbor);
                        }
                        
                    }

                }

            } else {
                System.out.println("list of vertices is empty");
            }
            return hashMap;
        }

        public static void main(String[] args) {
            Graph graph1 = new Graph("graph1.txt");
            System.out.println("graph: ");
            for (Vertex v : graph1.getVertices()) {
                System.out.println(v);
            }
            for (Edge e : graph1.getEdges()) {
                System.out.println(e);
            }
        }

}
	