/*
 * 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    
    public Graph() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public Graph(int n) {
        this();
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex());
        }
    }

    public Graph(int n, double probability) {
        this();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex());
            for (int j = 0; j < i; j++) {
                if (rand.nextDouble() < probability) {
                    Edge edge = new Edge(vertices.get(i), vertices.get(j), 1);
                    edges.add(edge);
                    vertices.get(i).addEdge(edge);
                    vertices.get(j).addEdge(edge);
                }
            }
        }
    }

    /* *
	 * A graph constructor that takes in a filename and builds
	 * the graph with the number of vertices and specific edges 
	 * specified.  
	 * */
	public Graph( String filename ) {

    	try {
    		//Setup for reading the file
    		FileReader fr = new FileReader(filename);
    		BufferedReader br = new BufferedReader(fr);

    		//Get the number of vertices from the file and initialize that number of verticies
			vertices = new ArrayList() ;
        	Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
			for ( int i = 0 ; i < numVertices ; i ++ ) {
				vertices.add( new Vertex() );
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
 	           	Edge edge = new Edge( vertices.get( start ) , vertices.get( end ) , 1. ) ;
 				//Add the edge to the set of edges for each of the vertices
 				vertices.get( start ).addEdge( edge ) ;
				vertices.get( end ).addEdge( edge ) ;
				//Add the edge to the collection of edges in the graph
            	this.edges.add( edge );
            	
            	//Read the next line
            	line = br.readLine();
            }
        	// call the close method of the BufferedReader:
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
        return vertices.size();
    }

    public Iterable<Vertex> getVertices() {
        return vertices;
    }

    public Iterable<Edge> getEdges() {
        return edges;
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
