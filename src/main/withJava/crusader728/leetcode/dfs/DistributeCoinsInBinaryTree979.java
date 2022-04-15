package withJava.crusader728.leetcode.dfs;

public class DistributeCoinsInBinaryTree979 {
    public int distributeCoins(TreeNode root) {
        Pair result = helper(root);
        return result.moves;
    }

    private Pair helper(TreeNode root) {
        if(root == null) {
            return new Pair(0, 0);
        }

        Pair left = helper(root.left);
        Pair right = helper(root.right);

        int canProvide = left.numberOfCoin + right.numberOfCoin + (root.val - 1);
        int minimumMove = right.moves + left.moves + Math.abs(canProvide);
        return new Pair(canProvide, minimumMove);
    }

    private class Pair {
        int numberOfCoin;
        int moves;

        Pair(int n, int m) {
            this.numberOfCoin = n;
            this.moves = m;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
