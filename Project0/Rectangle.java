/*
 * The purpose of this program is to define a rectangle given the
 * dimensions.
 * Date: 2/15/24
 * Author: Svita
*/

public class Rectangle {

    private float width;
    private float height;

    /*
    This function creates the rectangle with the measurements.
    */ 
    public Rectangle(float width, float height){
        this.width = width;
        this.height = height;
    }

    /*
    This function creates a square with just the width.
    */ 
    public Rectangle(float width){
        this(width, width);
    }

    /*
    This function gets the width from the initial constructor.
    */ 
    public float getWidth(){
        return width;
    }
    /*
    This function get the height from the initial constructor.
    */ 
    public float getHeight(){
        return height;
    }

    /* 
    This function sets the new width of the rectangle.
    */
    public void setWidth(float newWidth){
        this.width = newWidth;
    }

    /*
    This function sets the new height of the rectangle.
    */
    public void setHeight(float newHeight){
        this.height = newHeight;
    }

    /*  This function calculates and computes the diagonal of the rectangle
        using the pythagorean theorem.
    */
    public double computeDiagonal(){
        // this part uses the pythagorean theorem.
        double diagSqrd = (Math.pow(width, 2) + Math.pow(height, 2));
        double diagonal = Math.sqrt(diagSqrd);
        return diagonal;
    }

    public String toString() {
        return "Height: " + height+ ", Width: " + width + ", Diagonal: " + computeDiagonal();
    }
}