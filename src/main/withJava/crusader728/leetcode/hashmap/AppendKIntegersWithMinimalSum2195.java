package withJava.crusader728.leetcode.hashmap;

import java.util.Arrays;

public class AppendKIntegersWithMinimalSum2195 {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        int remaining = k;
        long sum = 0;
        int i = 0;
        while(remaining > 0 && i < nums.length) {
            if(i == 0) {
                if(nums[i] != 1) {
                    //1  to nums[i] - 1
                    int count = nums[i] - 1;
                    if(count >= remaining) {
                        sum += (long)remaining * (1 + remaining) / 2;
                        remaining = 0;
                    } else {
                        sum += (long)count * nums[i] / 2;
                        remaining = remaining - count;
                    }
                }
            } else {
                //from nums[i - 1] + 1 to nums[i] - 1
                int count = nums[i] - 1 - nums[i - 1];
                if(count >= remaining) {
                    sum += (long)remaining * (long)(nums[i - 1] + 1 + nums[i - 1] + remaining) / 2;
                    remaining = 0;
                } else if(count >= 0) {
                    sum += (long)(nums[i - 1] + nums[i]) * (long)(count) / 2;
                    remaining = remaining - count;

                }
            }
            i++;
        }
        if(remaining > 0) {
            //from nums[nums.length - 1] + 1 to nums[nums.length - 1] + remaining
            sum += (long)remaining * (nums[nums.length - 1] + 1 + nums[nums.length - 1] + remaining) / 2;
        } 

        return sum;
    }

    public static void main(String[] args) {
        AppendKIntegersWithMinimalSum2195 appendKIntegersWithMinimalSum2195 = new AppendKIntegersWithMinimalSum2195();
        appendKIntegersWithMinimalSum2195.minimalKSum(new int[]{96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84},
        35);
    }
}
