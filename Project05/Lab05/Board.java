import java.io.*;
import java.lang.reflect.Array;

public class Board {

    private Cell board[][]; // = new Cell[9][9];
    
    public Board() {
        board = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell(i, j, 0);
            }
        }
    }
    
    public Cell get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, int value) {
        board[row][col].setValue(value);
    }

    public void set(int row, int col, boolean locked) {
        board[row][col].setLocked(locked);
    }

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

    }

}
