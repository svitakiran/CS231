/*
file name:      DumbShuffle.java
Authors:        Ike Lage & Max Bender & Allen Harper
last modified:  02/26/2024

How to run:     java -ea DumbShuffle
*/

import java.util.ArrayList;
import java.util.Random;

public class DumbShuffle {

    /**
     * "Shuffles" the arraylist by reversing the order
     */
    public static ArrayList<Integer> dumbShuffle( ArrayList<Integer> integerArray ) {

        ArrayList<Integer> newIntegerArray = new ArrayList<Integer>() ;

        //Add the last to the new list and remove it until the end
        for ( int i = integerArray.size() - 1 ; i >= 0 ; i -- ) {
            newIntegerArray.add( integerArray.get( i ) );
        }

        return newIntegerArray ;
    }

    /**
     * Doesn't shuffle the arraylist, just returns a copy
     */
    public static ArrayList<Integer> noShuffle( ArrayList<Integer> integerArray ) {

        ArrayList<Integer> newIntegerArray = new ArrayList<Integer>() ;

        //Add the last to the new list and remove it until the end
        for ( int i = 0 ; i < integerArray.size() ; i ++ ) {
            newIntegerArray.add( integerArray.get( i ) );
        }

        return newIntegerArray ;
    }

    public static ArrayList<Integer> smartShuffle(ArrayList<Integer> numsArr) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        Random ran = new Random();
        for (int i = numsArr.size() - 1; i >= 0; i--) {
            nums.add(numsArr.get(i));
        }
        for (int i = 0; i < nums.size(); i++) {
            int index = ran.nextInt(nums.size());
            int temp = nums.get(i);
            nums.set(i, nums.get(index));
            nums.set(index, temp);
        }
        return nums;
    }

}