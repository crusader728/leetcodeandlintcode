package withJava.crusader728.lintcode;

public class ID207_IntervalSumII {
    /* you may need to use some attributes here */

    /*
     * @param A: An integer array
     */
    private class Node {
        int start;
        int end;
        int sum;
        Node left;
        Node right;
    }

    private Node root;

    public ID207_IntervalSumII(int[] A) {
        // do intialization if necessary
        root = buildTree(A);
    }

    private Node buildTree(int[] A) {
        return buildTreeHelper(A, 0, A.length - 1);
    }

    private Node buildTreeHelper(int[] A, int start, int end) {
        if(start > end) {
            return null;
        } else if(start == end) {
            Node leaf = new Node();
            leaf.start = start;
            leaf.end = end;
            leaf.sum = A[start];
            return leaf;
        } else {
            Node branch = new Node();
            branch.start = start;
            branch.end = end;
            int mid = start + (end - start) / 2;
            Node left = buildTreeHelper(A, start, mid);
            Node right = buildTreeHelper(A, mid + 1, end);
            branch.sum = left.sum + right.sum;
            branch.left = left;
            branch.right = right;
            return branch;
        }
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return queryHelper(root, start, end);
    }

    private long queryHelper(Node node, int start, int end) {
        if (node == null || start > end || start > node.end || end < node.start){
            return 0;
        }
        if (start <= node.start && end >= node.end){
            return node.sum;
        }
        long ans = 0;
        int mid = (node.start + node.end) / 2;
        if (start <= mid){
            ans += queryHelper(node.left, start, Math.min(mid, end));
        }
        if (mid + 1 <= end){
            ans += queryHelper(node.right, Math.max(mid + 1, start), end);
        }
        return ans;
    }



    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        modifyHelper(root, index, value);
    }

    private void modifyHelper(Node node, int index, int value) {
        if (node == null || index < node.start || index > node.end){
            return;
        }
        if (node.start == node.end){
            if (node.start == index){
                node.sum = value;
            }
            return;
        }
        int mid = (node.start + node.end) / 2;
        modifyHelper(index <= mid ? node.left : node.right, index, value);
        node.sum = node.left.sum + node.right.sum;
    }
}
