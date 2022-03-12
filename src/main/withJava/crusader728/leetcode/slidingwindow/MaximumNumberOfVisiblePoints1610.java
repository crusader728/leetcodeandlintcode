package withJava.crusader728.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaximumNumberOfVisiblePoints1610 {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int zx = location.get(0);
        int zy = location.get(1);
        List<Double> ps = new ArrayList<>();
        int same = 0;
        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);
            if (x == zx && y == zy) {
                same++;
            } else {
                ps.add(Math.atan2(y - zy, x - zx));
            }
        }
        Collections.sort(ps);
        int size = ps.size();
        for(int i = 0 ; i < size; ++i) {
            ps.add(ps.get(i) + 2 * Math.PI);
        }



        int l = 0;
        int r = 1;
        int max = 0;
        double d = angle / 180.0 * Math.PI;
        while(l < ps.size()) {
            while(r < ps.size() && ps.get(r) - ps.get(l) <= d) {
                r++;
            }
            max = Math.max(r - l, max);
            l++;
        }
        return max + same;
    }

    public static void main(String[] args) {
        MaximumNumberOfVisiblePoints1610 maximumNumberOfVisiblePoints1610 = new MaximumNumberOfVisiblePoints1610();
        maximumNumberOfVisiblePoints1610.visiblePoints(Arrays.asList(Arrays.asList(1, 1), Arrays.asList(2, 2), Arrays.asList(3,3), Arrays.asList(4, 4), Arrays.asList(1,2), Arrays.asList(2, 1)), 0, Arrays.asList(1, 1));
    }
}
