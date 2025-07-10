/*
 * The purpose of this file is to create the evader in the game.
 * Author: Svita Kiran
 * Date: 3/6/24
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class MoveAwayPlayerAlgorithm extends AbstractPlayerAlgorithm {
    Random rand = new Random();
    ArrayList<Vertex> verts;
    private Vertex cur;
    int moveCount = 0;

    public MoveAwayPlayerAlgorithm(Graph graph) {
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

    public Vertex chooseNext(Vertex otherPlayer) {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        HashMap<Vertex,Double> values = graph.distanceFrom(otherPlayer);

        for (Vertex elem : cur.adjacentVertices()) {
            neighbors.add(elem);
        }

        Vertex farthestVert = null;
        double maxDist = Double.NEGATIVE_INFINITY;
        
        for (Vertex neighbor : neighbors) {
            Edge edge = cur.getEdgeTo(neighbor);
            if (edge != null && neighbor != otherPlayer) {
                Double neighborDist = values.get(neighbor);
                if (neighborDist != null) { 
                    double edgeDist = neighborDist + edge.distance();
                    if (edgeDist > maxDist) {
                        maxDist = edgeDist;
                        farthestVert = neighbor;
                    }
                }
            }
        }
        
        setCurrentVertex(farthestVert);
        moveCount++;
        System.out.println(moveCount);
        return farthestVert;
         
    }
}
