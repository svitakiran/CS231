import java.util.ArrayList;

public class PrimitivesAndPointers {

    public static void addOne(int x){
        x = x + 1;
    }

    public static void addOne(ArrayList<Integer> list){
        list.add(1);
    }

    public static void main(String[] args){
        int x = 5; 
        System.out.println("Before, x = " + x);
        addOne(x);
        System.out.println("After, x = " + x);


        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Before, list = " + list);
        addOne(list);
        System.out.println("After, list = " + list);
    }
    
}