/*
 * The purpose of this file is to use A*.
 * Author: Svita Kiran
 * Date: 4/29/30
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MazeAStarSearch extends AbstractMazeSearch {
    private PriorityQueue<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze) {
        super(maze);
        priorityQueue = new PriorityQueue<>(new CellComparator());
    }

    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    public void addCell(Cell next) {
        priorityQueue.add(next);
    }

    public int numRemainingCells() {
        return priorityQueue.size();
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {
        setStart(start);
        setTarget(target);
        setCur(start);

        MazeSearchDisplay mazeDisplay = null;
        if (display) {
            mazeDisplay = new MazeSearchDisplay(this, 20);
        }

        // add the starting cell to the set of Cells to explore
        addCell(start);

        while (numRemainingCells() > 0) {
            setCur(findNextCell());

            for (Cell neighbor : getMaze().getNeighbors(getCur())) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(getCur());
                    addCell(neighbor);

                    if (neighbor == getTarget()) {
                        LinkedList<Cell> path = traceback(target);
                        if (display) {
                            mazeDisplay.repaint();
                        }
                        return path;
                    }
                }
            }

            if (display) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    // Handle InterruptedException
                }
                mazeDisplay.repaint();
            }
        }

        return null;
    }

    private class CellComparator implements Comparator<Cell> {
    
        public int compare(Cell cell1, Cell cell2) {
            int dist1 = numRemainingCells() + estimateDist(cell1, getTarget());
            int dist2 = numRemainingCells() + estimateDist(cell2, getTarget());
            return Integer.compare(dist1, dist2);
        }
    }

    private int estimateDist(Cell cell, Cell target) {
        return Math.abs(cell.getRow() - target.getRow()) + Math.abs(cell.getCol() - target.getCol());
    }

        
}
