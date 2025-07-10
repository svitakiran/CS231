/*
 * The purpose of this file is to run the maze simulation.
 * Author: Svita Kiran
 * Date: 4/30/30
 */

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10, 0.2);

        MazeDepthFirstSearch dfsSearch = new MazeDepthFirstSearch(maze);

        Cell start = maze.get(0, 0);
        Cell target = maze.get(9, 9);
        LinkedList<Cell> path = dfsSearch.search(start, target, true, 100);

        if (path != null) {
            System.out.println("Path found:");
            for (Cell cell : path) {
                System.out.println(cell);
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
