package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PartitionToKEqualSumSubsets698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if(total % k != 0) {
            return false;
        }

        int target = total / k;
        boolean[] visited = new boolean[nums.length];
        int used = 0;
        return helper(nums, target, 0, target, visited, used);
    }

    private boolean helper(int[] nums, int target, int i, int remaining, boolean[] visited, int used) {
        if(used == nums.length) {
            return true;
        }

        if(i == nums.length && remaining != 0) {
            return false;
        }

        if(remaining == 0) {
            return helper(nums, target, 0, target, visited, used);
        }

        if(remaining >= nums[i] && !visited[i]) {
            visited[i] = true;
            boolean sub = helper(nums, target, i + 1, remaining - nums[i], visited, used + 1);
            if(!sub) {
                visited[i] = false;
                return helper(nums, target, i + 1, remaining, visited, used);
            } else {
                return true;
            }
        } else {
            return helper(nums, target, i + 1, remaining, visited, used);
        }
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets698 partitionToKEqualSumSubsets698 = new PartitionToKEqualSumSubsets698();
        partitionToKEqualSumSubsets698.canPartitionKSubsets(new int[] {4,3,2,3,5,2,1}, 4);
    }
}
