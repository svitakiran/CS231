/*
 * The purpose of this file is to use DFS.
 * Author: Svita Kiran
 * Date: 4/29/24
 */

import java.util.Stack;

public class MazeDepthFirstSearch extends AbstractMazeSearch {
    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        stack = new Stack<>();
    }

    public Cell findNextCell() {
        return stack.pop();
    }

    public void addCell(Cell next) {
        stack.push(next);
    }

    public int numRemainingCells() {
        return stack.size();
    }

}