import java.util.ArrayList;
import java.util.Random;

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