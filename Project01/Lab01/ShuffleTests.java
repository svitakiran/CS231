/*
file name:      ShuffleTests.java
Authors:        Ike Lage & Max Bender & Allen Harper
last modified:  02/26/2024

How to run:     java -ea ShuffleTests
*/

import java.util.ArrayList;

public class ShuffleTests {

    public static void shuffleTestsLab() {
        // case 1: testing that the arrays before and after shuffle are not equal
        {
            // set up
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            //Make an array of integers from 0 to 10
            for(int i = 0; i < 11; i++) {
                numbers.add(i);
            }

            //Shuffle it and save the output as a new array

            ArrayList<Integer> newNumbers = DumbShuffle.dumbShuffle(numbers);
            //ArrayList<Integer> newNumbers = DumbShuffle.noShuffle(numbers);


            // verify
            // Print the original and the shuffled arrays 

            System.out.println(newNumbers);
            System.out.println(numbers);

            // test
            //Assert that the original and new arrays aren't equal
            assert !numbers.equals(newNumbers): "Error: array not shuffled correctly";
        }
       
        //Print that your tests have all passed!
        System.out.println("All tests passed");
    }

    
    public static void shuffleTestsReflection() {
        //This is where you will write your code for reflection question 1
    }

    public static void main(String[] args) {
        shuffleTestsLab();
    }
}