/*
 * The purpose of this file is to create the abstract player
 * Author: Svita Kiran
 * Date: 5/5/24
*/


public abstract class AbstractPlayerAlgorithm {
    protected Graph graph;
    protected Vertex cur;

    public AbstractPlayerAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public Vertex getCurrentVertex() {
        return cur;
    }

    public void setCurrentVertex(Vertex vertex) {
        this.cur = vertex;
    }

    public abstract Vertex chooseStart();

    public abstract Vertex chooseStart(Vertex other);

    public abstract Vertex chooseNext(Vertex otherPlayer);
}
