/*
 * The purpose of this file is to test the Sudoku file and the methods 
 * in it.
 * Author: Svita Kiran
 * Date: 4/11/24
 */

public class SudokuTests {

    public static void main(String[] args) {
        // Case 1: Testing Sudoku()
        {
            // Setup
            Sudoku sudoku = new Sudoku();

            // Test
            assert sudoku.getBoard() != null : "Error in Sudoku::Sudoku()";
        }

        // Case 2: Testing Sudoku(Board board)
        {
            // Setup
            Board board = new Board();
            Sudoku sudoku = new Sudoku(board);

            // Test
            assert sudoku.getBoard() == board : "Error in Sudoku::Sudoku(Board board)";
        }

        // Case 3: Testing findNextValue(Cell cell)
        {
            // Setup
            Sudoku sudoku = new Sudoku();
            Board board = sudoku.getBoard();
            Cell cell = board.get(0, 0);
            cell.setValue(1);

            // Test
            assert sudoku.findNextValue(cell) == 2 : "Error in Sudoku::findNextValue(Cell cell)";
        }

        // Case 4: Testing findNextCell()
        {
            // Setup
            Sudoku sudoku = new Sudoku();
            Board board = sudoku.getBoard();
            board.set(0, 0, 1);
            board.set(0, 1, 2);

            // Test
            assert sudoku.findNextCell() != null : "Error in Sudoku::findNextCell()";
        }

        // Case 5: Testing solve(int delay)
        {
            // Setup
            Sudoku sudoku = new Sudoku();
            int delay = 0;

            // Test
            try {
                assert sudoku.solve(delay) : "Error in Sudoku::solve(int delay)";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All Sudoku tests passed!");
    }
}
