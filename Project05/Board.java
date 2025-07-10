/*
 * The purpose of this file is to create a Sudoku board that represents
 * all of the cells in a Sudoku puzzle. It initializes and draws the 
 * board.
 * Author: Svita Kiran
 * Date: 4/2/24
*/

import java.io.*;
import java.lang.reflect.Array;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class Board {

    private static final int SIZE = 9;
    private Cell board[][];
    private boolean finished;
    
    public Board() {
        board = new Cell[SIZE][SIZE];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public Board(String filename) {
        this();
        read(filename);
    }

    public Board(int numLocked) {
        this();
        initializeRandom(numLocked);
    }

    public int getCols() {
        return SIZE;
    }

    public int getRows() {
        return SIZE;
    }

    public Cell get(int r, int c) {
        return board[r][c];
    }

    public boolean isLocked(int r, int c) {
        return board[r][c].isLocked();
    }

    public int numLocked() {
        int num = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isLocked()) {
                    num++;
                }
            }
        }
        return num;
    }

    public int value(int r, int c) {
        return board[r][c].getValue();
    }

    public void set(int r, int c, int value) {
        board[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked) {
        board[r][c].setValue(value);
        board[r][c].setLocked(locked);
    }
    
    // public Cell get(int row, int col) {
    //     return board[row][col];
    // }

    // public void set(int row, int col, int value) {
    //     board[row][col].setValue(value);
    // }

    // public void set(int row, int col, boolean locked) {
    //     board[row][col].setLocked(locked);
    // }

    public boolean read(String filename) {
        int row = 0;
        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);
        
            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = br.readLine();
            // start a while loop that loops while line isn't null
            while(line != null){
                // print line
                System.out.println( line );
                // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
                String[] arr = line.split( "[ ]+" );
                // let's see what this array holds: 
                System.out.println("the first item in arr: " + arr[0] + ", the second item in arr: " + arr[1]);
                // print the size of the String array (you can use .length)
                System.out.println( arr.length );
                // use the line to set various Cells of this Board accordingly
                for (int col = 0; col < 9; col++) {
                    if(Integer.valueOf(arr[col]) > 0) {
                        board[row][col] = new Cell(row, col, Integer.valueOf(arr[col]), true);
                    }
                }
                row++;
                // assign to line the result of calling the readLine method of your BufferedReader object.
                line = br.readLine();
            }
            // call the close method of the BufferedReader
            br.close();
            return true;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public int getValue() {
        return SIZE;
    }

    public boolean validValue(int row, int col, int value) {
        if (!(1 <= value && value <= 9)) {
            return false;
        }
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i].getValue() == value && i != col) {
                return false;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col].getValue() == value && i != row) {
                return false;
            }
        }

        int checkRow = (row / 3) * 3;
        int checkCol = (col / 3) * 3;
        for (int i = checkRow; i < checkRow + 3; i++) {
            for (int j = checkCol; j < checkCol + 3; j++) {
                if (board[i][j].getValue() == value && (i != row || j != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validSolution() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j].getValue();
                if (value == 0 || !validValue(i, j, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initializeRandom(int numLocked) {
        Random random = new Random();
        int count = 0;
        while (count < numLocked) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            int value = random.nextInt(9) + 1;
            if (validValue(row, col, value) && !board[row][col].isLocked()) {
                board[row][col].setValue(value);
                board[row][col].setLocked(true);
                count++;
            }
        }
    }    

    public void draw(Graphics g, int scale) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
            }
        }
        if (finished) {
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
            }
        }
    }

    /**
    * Randomly permutes the symbols used in the solution.
    */
    private void randomPermute(){
        int[] permutation = new int[getRows()];
        Random rand = new Random();
        for(int i = 0; i < getRows(); i++){
            int swapIndex = rand.nextInt(i + 1);
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = i;
        }

        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getCols(); c++){
                set(r, c, permutation[value(r, c) - 1] + 1);
            }
        }
    }

    public String toString() {
        String text = "";
        System.out.print(" ");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getValue());
                System.out.print(" ");
            }
            System.out.println("");
            System.out.print(" ");
        }
        return text;
    }   

    public static void main(String[] args) {
        Board board = new Board();
        //board.read("board1.txt");
        board.read("board2.txt");
        System.out.println(board.toString());

        if (board.validSolution()) {
            System.out.println("The board is solved.");
        } else {
            System.out.println("The board is not solved.");
        }

    }

}
