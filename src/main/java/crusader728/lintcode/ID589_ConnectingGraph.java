package java.crusader728.lintcode;

import java.util.HashMap;
import java.util.Map;

public class ID589_ConnectingGraph {
    Map<Integer, Integer> father;
    /*
     * @param n: An integer
     */public ID589_ConnectingGraph(int n) {
        // do intialization if necessary
        father = new HashMap<>();
        for(int i = 1; i <= n; ++i) {
            father.put(i, i);
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);
        if(rootA != rootB) {
            father.put(rootB, rootA);
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);
        return rootA == rootB;
    }

    private int getRoot(int i) {
        int root = i;
        while(father.get(root) != root) {
            root = father.get(root);
        }
        return root;
    }
}
