package withJava.crusader728.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class ClosestBinarySearchTreeValueII272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> allvalues = new ArrayList<>();
        inorder(root, allvalues);

        int l = binarySearch(allvalues, target);
        List<Integer> result = new ArrayList<>();
        if(l == 0) {
            for(int i = 0; i < k; ++i) {
                result.add(allvalues.get(i));
            }
            return result;
        } else if(l == allvalues.size()) {
            for(int i = l - 1; i >= l - k; --i) {
                result.add(allvalues.get(i));
            }
            return result;
        } else {
            int left = l - 1;
            int right = l;
            int count = 0;
            while(count < k) {
                while(left >= 0 && right < allvalues.size() && count < k) {
                    if(target - allvalues.get(left) <= allvalues.get(right) - target) {
                        result.add(allvalues.get(left));
                        left--;
                    } else {
                        result.add(allvalues.get(right));
                        right++;
                    }
                    count++;
                }
                if(count < k) {
                    if(left < 0) {
                        while(right < allvalues.size() && count < k) {
                            result.add(allvalues.get(right++));
                            count++;
                        }
                    } else {
                        while(left >= 0 && count < k) {
                            result.add(allvalues.get(left--));
                            count++;
                        }
                    }
                }
            }
            return result;
        }
    }

    private void inorder(TreeNode root, List<Integer> allvalues) {
    }

    private int binarySearch(List<Integer> allvalues, double target) {
        return 0;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
