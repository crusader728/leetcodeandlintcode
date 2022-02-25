package withJava.crusader728.leetcode.unionfind;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EarliestMomentWhenEveryoneBecomeFriends1101 {
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


        Map<Integer, Integer> sizeMap = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();


        //initialize
        for(int i = 0; i < n; ++i) {
            parent.put(i, i);
            sizeMap.put(i, 1);
        }

        for(int i = 0; i < logs.length; ++i) {
            int ts = logs[i][0];
            int x = logs[i][1];
            int y = logs[i][2];

            int parentX = findParent(x, parent);
            int parentY = findParent(y, parent);

            if(parentX != parentY) {
                if(parentX < parentY) {
                    parent.put(parentY, parentX);
                    sizeMap.put(parentX, sizeMap.get(parentX) + sizeMap.get(parentY));
                    if(sizeMap.get(parentX) == n) {
                        return ts;
                    }
                } else {
                    parent.put(parentX, parentY);
                    sizeMap.put(parentY, sizeMap.get(parentX) + sizeMap.get(parentY));
                    if(sizeMap.get(parentY) == n) {
                        return ts;
                    }
                }
            }
        }
        return -1;
         
    }

    private int findParent(int x, Map<Integer, Integer> parent) {
        int p = parent.get(x);
        if(p != x) {
            parent.put(x, findParent(p, parent));
            return parent.get(x);
        } else {
            return p;
        }
    }
}
