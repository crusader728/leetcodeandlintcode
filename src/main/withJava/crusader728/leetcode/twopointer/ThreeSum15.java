package withJava.crusader728.leetcode.twopointer;


import java.util.*;

public class ThreeSum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            if(i == 0 || nums[i -1] != nums[i]) {
                int low = i + 1;
                int high = nums.length - 1;
                while(low < high) {
                    int sum = nums[low] + nums[high] + nums[i];
                    if(sum > 0) {
                        high--;
                    } else if(sum < 0) {
                        low++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        low++;
                        while(low < high && nums[low] == nums[low - 1]) {
                            low++;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum15 threeSum15 = new ThreeSum15();
        threeSum15.threeSum(new int[] {-1, 0, 1, 2, -1, -4});
    }
}
