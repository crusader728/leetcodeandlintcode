package withJava.crusader728.lintcode;

import java.util.*;

public class ID16_PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        return helper(nums, 0);
    }

    private List<List<Integer>> helper(int[] nums, int start) {
        if(start == nums.length - 1) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> r = new ArrayList<>();
            r.add(nums[start]);
            result.add(r);
            return result;
        } else {
            List<List<Integer>> perms = helper(nums, start + 1);
            Set<List<Integer>> visited = new HashSet<>();
            for(List<Integer> perm: perms) {
                for(int pos = 0; pos <= perm.size(); ++pos) {
                    List<Integer> newPerm = new ArrayList<>();
                    for(int i = 0; i < pos; ++i) {
                        newPerm.add(perm.get(i));
                    }
                    newPerm.add(nums[start]);
                    for(int i = pos; i < perm.size(); ++i) {
                        newPerm.add(perm.get(i));
                    }
                    visited.add(newPerm);
                }

            }
            return new ArrayList<>(visited);
        }
    }
}
