import java.util.LinkedList;
import java.util.Random;

public class BSTMapTester {
    
    public static void test1(){
        System.out.println("-".repeat(30) + "\nTest1: ");

        String expectedResult = "\t\t<1 -> 1>\n\t<2 -> 2>\n\t\t<3 -> 3>\n<4 -> 4>\n\t\t<5 -> 5>\n\t<6 -> 6>\n\t\t<7 -> 7>";
        System.out.println("Expected result: \n" + expectedResult );
        System.out.println( "-".repeat(10) );

        MapSet<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {4, 2, 6, 1, 3, 5, 7}){
            map.put(i, "" + i);
        }
        System.out.println("Your result: \n" + map );
        System.out.println("-".repeat(30));
    }

    public static void test2(){
        //Note: This one is implemented for you, but you need to figure out what it should look like!
        System.out.println("-".repeat(30) + "\nTest2: ");
        BSTMap<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {1, 2, 3, 4, 5, 6, 7}){
            map.put(i, "" + i);
        } System.out.println(map);
        System.out.println("size: " + map.size() + " == " + 7);
        System.out.println("maxDepth: " + map.maxDepth() + " == " + 7);
        System.out.println("entrySet: " + map.entrySet());
        System.out.println("-".repeat(30));
    }

    public static void test3(){
        //This test should put a bunch of key/value pairs into the BSTMap, and remove them 
        //one by one--not in order, checking that the size of the BSTMap and its remaining 
        //Values stay correct.
        System.out.println("-".repeat(30) + "\nTest3: ");

        BSTMap<Integer, String> map = new BSTMap<>();
        LinkedList<Integer> keys = new LinkedList<>();
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            int key = rand.nextInt(100);
            keys.add(key);
            map.put(key, String.valueOf(i));
        }

        while (!keys.isEmpty()) {
            int keyToRemove = keys.removeFirst();
            map.remove(keyToRemove);
            System.out.println("Size after removal: " + map.size());
            System.out.println("Remaining values: " + map.values());
        }
        System.out.println("-".repeat(30));
    }

    public static void test4(){
        //This test should put a bunch of key/value pairs into the BSTMap, 
        //and check that the correct value gets returned when each key is removed.
        System.out.println("-".repeat(30) + "\nTest4: ");

        BSTMap<Integer, String> map = new BSTMap<>();
        LinkedList<Integer> keys = new LinkedList<>();
        Random rand = new Random();
    
        for (int i = 0; i < 20; i++) {
            int key = rand.nextInt(100);
            keys.add(key);
            map.put(key, String.valueOf(i));
        }
    
        while (!keys.isEmpty()) {
            int keyToRemove = keys.removeFirst();
            String removedValue = map.remove(keyToRemove);
            System.out.println("Removed value for key " + keyToRemove + ": " + removedValue);
        }
        System.out.println("-".repeat(30));
    }

    public static void main(String[] args){
        //test1();
        //test2();
        test3();
        //test4();
    }

}