package withJava.crusader728.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInArray448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < nums.length; ++i) {
            int key = Math.abs(nums[i]) - 1;
            if(key < n && key >= 0 && nums[key] > 0) {
                nums[key] *= -1;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
