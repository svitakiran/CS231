/*
file name:      LandscapeTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea LandscapeTests
*/


import java.util.ArrayList;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing reset()
        {
            // set up
            Landscape l = new Landscape(3, 3);

            // verify
            System.out.println("\nTesting reset():");
            System.out.println("Initial Landscape:");
            System.out.println(l);

            // test
            l.reset();
            System.out.println("Reset Landscape:");
            System.out.println(l);
        }

        // case 3: testing getRows()
        {
            // set up
            Landscape l = new Landscape(5, 7);

            // verify
            System.out.println("\nTesting getRows():");
            System.out.println("Number of Rows: " + l.getRows());

            // test
            assert l.getRows() == 5 : "Error in Landscape::getRows()";
        }

        // case 4: testing getCols()
        {
            // set up
            Landscape l = new Landscape(5, 7);

            // verify
            System.out.println("\nTesting getCols():");
            System.out.println("Number of Columns: " + l.getCols());

            // test
            assert l.getCols() == 7 : "Error in Landscape::getCols()";
        }

        // case 5: testing getCell(int, int)
        {
            // set up
            Landscape l = new Landscape(3, 3);

            // verify
            System.out.println("\nTesting getCell(int, int):");
            System.out.println("Cell at (1, 1): " + l.getCell(1, 1));

            // test
            assert l.getCell(1, 1) != null : "Error in Landscape::getCell(int, int)";
        }

        // case 6: testing getNeighbors()
        {
            // set up
            Landscape l = new Landscape(3, 3);

            // verify
            System.out.println("\nTesting getNeighbors():");
            ArrayList<Cell> neighbors = l.getNeighbors(1, 1);
            System.out.println("Neighbors of Cell at (1, 1): " + neighbors);

            // test
            assert neighbors.size() == 8 : "Error in Landscape::getNeighbors()";
        }

        // case 7: testing advance()
        {
            // set up
            Landscape l = new Landscape(3, 3);

            // verify
            System.out.println("\nTesting advance():");
            System.out.println("Initial Landscape:");
            System.out.println(l);

            // test
            l.advance();
            System.out.println("Landscape after advancing:");
            System.out.println(l);
        }

    }


    public static void main(String[] args) {

        landscapeTests();
    }
}