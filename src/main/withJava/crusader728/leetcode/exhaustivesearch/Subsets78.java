package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i = 0; i < nums.length; ++i) {
            List<List<Integer>> r = new ArrayList<>();
            for(int j = 0; j < result.size(); ++j) {
                List<Integer> subset = new ArrayList<>();
                subset.addAll(result.get(j));
                subset.add(nums[i]);
                r.add(subset);
            }
            result.addAll(r);
        }
        return result;
    }

    public static void main(String[] args) {
        Subsets78 subsets78 = new Subsets78();
        List<List<Integer>> subsets = subsets78.subsets(new int[]{1, 2, 3});
        System.out.println(subsets.size());
    }
}
