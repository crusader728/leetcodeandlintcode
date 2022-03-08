package withJava.crusader728.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedTheJumbledNumbers2191 {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<Info> infos = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            infos.add(new Info(nums[i], i, getMapped(mapping, nums[i])));
        }
        Collections.sort(infos, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                int compareMapped = Integer.compare(o1.mapped, o2.mapped);
                if(compareMapped != 0) {
                    return compareMapped;
                } else {
                    return Integer.compare(o1.idx, o2.idx);
                }
            }
        });

        int[] ans = new int[nums.length];
        for(int i = 0; i < infos.size(); ++i) {
            ans[i] = infos.get(i).num;
        }
        return ans;
    }

    private int getMapped(int[] mapping, int x) {
        int ans = 0;
        int pow = 1;
        if(x == 0) {
            return mapping[x];
        }
        while(x != 0) {
            ans += pow * mapping[x % 10];
            x = x / 10;
            pow = pow * 10;
        }
        return ans;
    }

    private static class Info {
        int num;
        int idx;
        int mapped;

        Info(int n, int i, int m) {
            this.num = n;
            this.idx = i;
            this.mapped = m;
        }
    }
}
