/*
 * The purpose of this file is to create and solve a game of Sudoku. It 
 * has methods using DFS (depth first search) in order to complete a
 * partially completed board with locked values. 
 * Author: Svita Kiran
 * Date: 4/8/24
*/

import java.util.Random;
import java.util.Stack;
import java.awt.Graphics;

public class Sudoku {
    private Board board;
    private LandscapeDisplay ld;

    public Sudoku() {
        this.board = new Board(randVal());
        this.ld = new LandscapeDisplay(board);
    }

    public Sudoku(Board board) {
        this.board = board;
        this.ld = new LandscapeDisplay(board);
    }

    private int randVal() {
        Random random = new Random();
        return random.nextInt(30) + 10; 
    }

    public Board getBoard() {
        return board;
    }

    public int findNextValue(Cell cell) {
        int currentValue = cell.getValue();
        for (int value = currentValue + 1; value <= 9; value++) {
            if (board.validValue(cell.getRow(), cell.getCol(), value)) {
                return value;
            }
        }
        System.out.println("No valid value found for cell: " + cell);
        return 0;
    }

    public Cell findNextCell() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.get(row, col);
                if (cell.getValue() == 0) {
                    int validValue = findNextValue(cell);
                    if (validValue != 0) {
                        cell.setValue(validValue);
                        return cell;
                    } else {
                        System.out.println("No valid value found for cell: " + cell);
                        return null;
                    }
                }
            }
        }
        System.out.println("No empty cell found");
        return null;
    }

    public boolean solve(int delay) throws InterruptedException {
        Stack<Cell> stack = new Stack<>();
    
        while (board.numLocked() < board.getRows() * board.getCols()) {
            if (delay > 0)
                Thread.sleep(delay);
            if (ld != null) {
                ld.repaint();
            }
            Cell next = findNextCell();
    
            while (next == null && !stack.isEmpty()) {
                if (delay > 0)
                    Thread.sleep(delay);
                if (ld != null) {
                    ld.repaint();
                }
                Cell cell = stack.pop();
                int validValue = findNextValue(cell);
                if (validValue != 0) {
                    cell.setValue(validValue);
                    next = cell;
                }
            }
    
            if (next == null) {
                return false;
            } else {
                stack.push(next);
            }
        }
    
        return true;
    }
    
    

    public void draw(Graphics g, int scale) {
        this.board.draw(g, scale);
    }

    

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        // board.read("board1.txt");
        // board.read("board2.txt");
        // board.read("board3.txt");
        System.out.println(board.toString());
    
        Sudoku sudoku = new Sudoku(board);
        sudoku.solve(10);
    
        System.out.println("Final Board:");
        System.out.println(board.toString());
    
        if (sudoku.getBoard().validSolution()) {
            System.out.println("The board is solved.");
        } else {
            System.out.println("The board is not solved.");
        }
    
        sudoku.ld.repaint();

        
    }
    

}
