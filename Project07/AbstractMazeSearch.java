/*
 * The purpose of this file is to search the maze abstractly.
 * Author: Svita Kiran
 * Date: 4/29/24
 */

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class AbstractMazeSearch {
    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;

    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    public abstract Cell findNextCell();

    public abstract void addCell(Cell next);

    public abstract int numRemainingCells();

    public Maze getMaze() {
        return maze;
    }

    public void setTarget(Cell target) {
        this.target = target;
    }

    public Cell getTarget() {
        return target;
    }

    public void setCur(Cell cell) {
        this.cur = cell;
    }

    public Cell getCur() {
        return cur;
    }

    public void setStart(Cell start) {
        this.start = start;
        start.setPrev(start);
    }

    public Cell getStart() {
        return start;
    }

    public void reset() {
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    public LinkedList<Cell> traceback(Cell cell) {
        LinkedList<Cell> path = new LinkedList<>();
        while (cell != null) {
            path.addFirst(cell);
            cell = cell.getPrev();
        }
        if (path.getFirst() == start) {
            return path;
        } else {
            return null;
        }
    }

    public LinkedList<Cell> search(Cell start, Cell target) {
        setStart(start);
        setTarget(target);
        setCur(start);

        // add the starting cell to the set of Cells to explore
        addCell(start);
        // while (there are still Cells left to explore){
        while (numRemainingCells() > 0) {
            setCur(findNextCell());
        
            for (Cell neighbor : getMaze().getNeighbors(getCur())) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(getCur());
                    addCell(neighbor);
                    
                    if (neighbor == getTarget()) {
                        return traceback(target);
                    }
                }
            }
        }

        return null; // we couldn't find the target, but we're done
    }

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);

        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
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


}
