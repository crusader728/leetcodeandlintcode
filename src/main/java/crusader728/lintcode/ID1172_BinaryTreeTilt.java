package java.crusader728.lintcode;

import java.util.ArrayList;
import java.util.List;

public class ID1172_BinaryTreeTilt {
    private static class Info {
        int sum;
        int skewness;

        Info(int sum, int skewness) {
            this.sum = sum;
            this.skewness = skewness;
        }
    }

    public int findTilt(TreeNode root) {
        List<Info> infos = new ArrayList<>();
        collectInfo(root, infos);
        int sum = 0;
        for (Info info : infos) {
            sum += info.skewness;
        }
        return sum;
    }

    private Info collectInfo(TreeNode root, List<Info> acc) {
        if (root == null) {
            Info info = new Info(0, 0);
            acc.add(info);
            return info;
        } else {
            Info left = collectInfo(root.left, acc);
            Info right = collectInfo(root.right, acc);
            Info result = new Info(left.sum + right.sum + root.val, Math.abs(left.sum - right.sum));
            acc.add(result);
            return result;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
