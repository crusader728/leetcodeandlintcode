package withJava.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountArtifactsThatCanBeExtracted2201 {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        Map<Integer, Map<Integer, Integer>> index = new HashMap<>();
        int[] remaining = new int[artifacts.length];
        for(int i = 0; i < artifacts.length; ++i) {
            int r1 = artifacts[i][0];
            int c1 = artifacts[i][1];
            int r2 = artifacts[i][2];
            int c2 = artifacts[i][3];
            for(int x = r1; x <= r2; ++x) {
                for(int y = c1; y <= c2; ++y) {
                    index.computeIfAbsent(x, k -> new HashMap<>()).put(y, i);
                    remaining[i]++;
                }
            }
        }
        int count = 0;
        for(int i = 0; i < dig.length; ++i) {
            int x = dig[i][0];
            int y = dig[i][1];
            if(index.containsKey(x) && index.get(x).containsKey(y)) {
                int id = index.get(x).get(y);
                remaining[id]--;
                if(remaining[id] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountArtifactsThatCanBeExtracted2201 countArtifactsThatCanBeExtracted2201 = new CountArtifactsThatCanBeExtracted2201();
        countArtifactsThatCanBeExtracted2201.digArtifacts(2, new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        }, new int[][] {
                {0, 0},
                {0, 1}
        });
    }
}
