package withJava.crusader728.leetcode.dfs;

public class StepByStepDirectionsFromBinaryTreeNodeToAnother2096 {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder builder = new StringBuilder();
        getPath(root, startValue, builder);
        String p1 = builder.toString();
        builder.delete(0, builder.length());
        getPath(root, destValue, builder);
        String p2 = builder.toString();
        return directions(p1, p2);
    }

    private String directions(String p1, String p2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        while(i < p1.length() && j < p2.length() && p1.charAt(i) == p2.charAt(j)) {
            i++;
            j++;
        }
        for(int x = p1.length() - 1; x >= i; --x) {
            result.append('U');
        }
        for(int x = j; x < p2.length(); ++x) {
            result.append(p2.charAt(x));
        }
        return result.toString();
    }

    private boolean getPath(TreeNode root, int value, StringBuilder builder) {
        if(root == null) {
            return false;
        } else if(root.val == value) {
            return true;
        } else {
            builder.append('L');
            boolean found = getPath(root.left, value, builder);
            if(!found) {
                builder.deleteCharAt(builder.length() - 1);
                builder.append('R');
                found = getPath(root.right, value, builder);
                if(!found) {
                    builder.deleteCharAt(builder.length() - 1);
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
