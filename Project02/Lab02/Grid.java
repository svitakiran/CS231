/*
 * Lab 02
 * Author: Svita Kiran
 * Date: 2/27/24
*/

import java.util.Random;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: add one or more arguments to the command line");
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        Integer[][] grid = new Integer[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Random rand = new Random();
                grid[i][j] = rand.nextInt(11);
            }
        }

        print(grid);

        int[][] arr1 = new int[2][2];
        int[][] arr2 = new int[2][2];
        System.out.println(gridEquals(arr1, arr2));
        System.out.println(rotate(arr2));

        if (args.length < 2) {
            System.out.println("Usage: java Grid <rows> <cols> [<min_value> <max_value>]");
            System.out.println("    <rows>: Number of rows in the grid (integer)");
            System.out.println("    <cols>: Number of columns in the grid (integer)");
            System.out.println("    <min_value>: Minimum value for random numbers (optional, default: 0)");
            System.out.println("    <max_value>: Maximum value for random numbers (optional, default: 10)");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int minValue = args.length > 2 ? Integer.parseInt(args[2]) : 0;
        int maxValue = args.length > 3 ? Integer.parseInt(args[3]) : 10;

        Integer[][] gri = new Integer[rows][cols];
        print(gri, minValue, maxValue);
    }

    public static void print(Integer[][] grid) {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = rand.nextInt(11);
                grid[i][j] = num;
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean gridEquals(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].length != arr2[i].length) {
                return false;
            }
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] rotate(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return null;
        }
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] rotArr = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotArr[j][rows - 1 - i] = arr[i][j];
            }
        }
        return arr;
    }

    public static void print(Integer[][] grid, int minValue, int maxValue) {
        Random rand = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int num = rand.nextInt(maxValue - minValue + 1) + minValue;
                grid[i][j] = num;
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
