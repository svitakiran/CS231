/*
* Author: Svita Kiran
* 
* Purpose of the class
*/

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {
    public static void main (String[] args) {
        ArrayList<Integer> arr0 = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++) {
            Random ran = new Random();
            int val = ran.nextInt(100);
            arr0.add(val);
            System.out.println(val);
        }
    
        for (int i = 0; i < arr0.size(); i++) {
            int x = arr0.get(i);
            System.out.println(x);
        }

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        arr1.addAll(arr0);
        System.out.println(arr1);
        ArrayList<Integer> arr2 = arr0;
        System.out.println(arr2);

        System.out.println("arr0 == arr1: " + (arr0 == arr1) + "\narr1 == arr2: " + (arr1 == arr2) + "\narr2 == arr0: " + (arr2 == arr0));
        System.out.println("arr0.equals(arr1): " + (arr0.equals(arr1)) + "\narr1.equals(arr2): " + (arr1.equals(arr2)) + "\narr2.equals(arr0): " + (arr2.equals(arr0)));

    }

}
