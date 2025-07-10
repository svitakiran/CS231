/*
 * The purpose of this file is to create the random player.
 * Author: Svita Kiran
 * Date: 5/5/24
*/

import java.util.Random;
import java.util.ArrayList;

public class RandomPlayer extends AbstractPlayerAlgorithm {
    Random rand = new Random();
    ArrayList<Vertex> verts;


    public RandomPlayer(Graph graph) {
        super(graph);
        verts = new ArrayList<>(); 
        for (Vertex v : graph.getVertices()) {
            verts.add(v);
        }
    }

    public Vertex chooseStart() {
        int randIdx = rand.nextInt(verts.size());
        Vertex chosenVertex = verts.get(randIdx);
        setCurrentVertex(chosenVertex);
        return chosenVertex;
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
        ArrayList<Vertex> neighbors = (ArrayList<Vertex>) cur.adjacentVertices(); 
        if (neighbors.isEmpty()) {
            return cur; 
        }
        int randIdx = rand.nextInt(neighbors.size());
        Vertex next = neighbors.get(randIdx);
        setCurrentVertex(next);
        return next;
    }
    
}
