/*
 * The purpose of this file is to create the pursuer in the game.
 * Author: Svita Kiran
 * Date: 5/6/24
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm{
    Random rand = new Random();
    ArrayList<Vertex> verts;
    private Vertex cur;

    public MoveTowardsPlayerAlgorithm(Graph graph) {
        super(graph);
        verts = new ArrayList<>();
        for (Vertex v : graph.getVertices()) {
            verts.add(v);
        }

        cur = chooseStart();
    }

    public Vertex chooseStart() {
        int randIdx = rand.nextInt(verts.size());
        Vertex chosenVert = verts.get(randIdx);
        setCurrentVertex(chosenVert);
        return chosenVert;
    }

    public Vertex chooseStart(Vertex other) {
        Vertex start = chooseStart();
        while(start.equals(other)) {
            start = chooseStart();
        }

        setCurrentVertex(start);
        return start; 
    }

    public Vertex chooseNext(Vertex randPlayer) {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        HashMap<Vertex,Double> values = graph.distanceFrom(randPlayer);
        int moveCount = 0;
    
        for (Vertex elem : cur.adjacentVertices()) {
            neighbors.add(elem);
        }
        
        Vertex nearestVert = null;
        double minDist = Double.POSITIVE_INFINITY;
        
        for (Vertex neighbor : neighbors) {
            Edge edge = cur.getEdgeTo(neighbor);
            if (edge != null && neighbor != randPlayer) {
                double edgeDist = values.get(neighbor) + edge.distance();
                if (edgeDist < minDist) {
                    minDist = edgeDist;
                    nearestVert = neighbor;
                }
            }
        }
        
        setCurrentVertex(nearestVert);
        moveCount++;
        System.out.println(moveCount);
        return nearestVert;
    }
    
}
