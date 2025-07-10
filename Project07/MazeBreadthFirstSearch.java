/*
 * The purpose of this file is to use BFS.
 * Author: Svita Kiran
 * Date: 4/29/24
 */

import java.util.LinkedList;
import java.util.Queue;

public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        queue = new LinkedList<>();
    }

    public Cell findNextCell() {
        return queue.poll();
    }

    public void addCell(Cell next) {
        queue.offer(next);
    }

    public int numRemainingCells() {
        return queue.size();
    }


}
