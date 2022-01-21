package withJava.crusader728.lintcode;

import java.util.HashMap;
import java.util.Map;

public class ID590_ConnectingGraphII {
    Map<Integer, Integer> father;
    Map<Integer, Integer> sizeMap;
    /*
     * @param n: An integer
     */public ID590_ConnectingGraphII(int n) {
        father = new HashMap<>();
        sizeMap = new HashMap<>();
        for(int i = 1; i <= n; ++i) {
            father.put(i, i);
            sizeMap.put(i, 1);
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int rootA = a;
        while(father.get(rootA) != rootA) {
            rootA = father.get(rootA);
        }

        int rootB = b;
        while(father.get(rootB) != rootB) {
            rootB = father.get(rootB);
        }

        if(rootA != rootB) {
            father.put(rootB, rootA);
            sizeMap.put(rootA, sizeMap.get(rootB) + sizeMap.get(rootA));
        }

    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        int root = a;
        while(father.get(root) != root) {
            root = father.get(root);
        }
        return sizeMap.get(root);
    }
}
