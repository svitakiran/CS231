/*
 * The purpose of this file is to create and represent one single
 * cell that is in a Sudoku board. 
 * Author: Svita Kiran
 * Date: 4/2/24
*/

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

    public int row;
    public int col;
    public int value;
    public boolean locked;
    
    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        locked = false;
    }

    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newval) {
        this.value = newval;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean lock) {
        this.locked = lock;
    }

    public void draw(Graphics g, int x, int y, int scale) {
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawChars(new char[] { toDraw }, 0, 1, x, y);
    }

    public String toString() {
        String val = ("row: " + row + ", col: " + col + ", value: " + value);
        return val;
    }

}
