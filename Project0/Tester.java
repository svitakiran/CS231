//Write a file header with your name and class purpose
//See Lab0.java for an example of the format we want

//Once you have completed both the Rectangle class and this Tester class, 
//You should be able to run this file, Tester.java and get the following 
//Output to your terminal.  When evaluating your code, we will run our own 
//version of this tester file and confirm that this is the output we get.
/*** 
4.0
5.0
2.2
2.2
Height: 2.2, Width: 2.2, Diagonal: 3.111269904655767
Height: 1.0, Width: 1.0, Diagonal: 1.4142135623730951
Height: 2.0, Width: 2.0, Diagonal: 2.8284271247461903
Height: 3.0, Width: 3.0, Diagonal: 4.242640687119285
Height: 4.0, Width: 4.0, Diagonal: 5.656854249492381
Height: 1.0, Width: 1.0, Diagonal: 1.4142135623730951
Height: 2.0, Width: 2.0, Diagonal: 2.8284271247461903
Height: 3.0, Width: 3.0, Diagonal: 4.242640687119285
Height: 5.0, Width: 5.0, Diagonal: 7.0710678118654755
*/

/* The purpose of this program is to test if the given measurements
    create a rectangle or not.
    Author: Svita Kiran
    Date: 12/16/24
*/


public class Tester {

    public static void testRectangle(){
        Rectangle rectangle = new Rectangle(3, 7);
        rectangle.setHeight(4);
        System.out.println(rectangle.getHeight());
        System.out.println(rectangle.computeDiagonal());

        Rectangle square = new Rectangle(2.2f, 2.2f);
        System.out.println(square.getWidth());
        System.out.println(square.getHeight());

        System.out.println(square);
    }

    public static void main(String[] args) {
        testRectangle();
        Rectangle[] objs = new Rectangle[4];
        for(int i = 0; i < 4; i++){
            objs[i] = new Rectangle(i + 1, i + 1);
        }

        for(int i = 0; i < 4; i++){
            System.out.println(objs[i]);
        }

        objs[3] = new Rectangle(5, 5);

        for(int i = 0; i < 4; i++){
            System.out.println(objs[i]);
        }

    }
    
}