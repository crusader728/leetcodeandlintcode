package withJava.crusader728.leetcode.dfs;

public class SecondMinimalNumberInBinaryTree671 {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) {
            return -1;
        }
        if(root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        if(left < right) {
            int candidate;
            candidate = findSecondMinimumValue(root.left);
            if(candidate == -1) {
                return right;
            } else {
                return Math.min(candidate, right);
            }
        } else if(left > right) {
            int candidate;
            candidate = findSecondMinimumValue(root.right);
            if(candidate == -1) {
                return left;
            } else {
                return Math.min(candidate, left);
            }
        } else {
            int leftcandidate = findSecondMinimumValue(root.left);
            int rightcandidate = findSecondMinimumValue(root.right);
            if(leftcandidate == -1 && rightcandidate == -1) {
                return -1;
            } else if(leftcandidate == -1) {
                return rightcandidate;
            } else if(rightcandidate == -1) {
                return leftcandidate;
            } else {
                return Math.min(leftcandidate, rightcandidate);
            }
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
