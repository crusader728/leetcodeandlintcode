package withJava.crusader728.lintcode;

import java.util.List;

public class CountAnalogousArrays {
    public static int countAnalogousArrays(List<Integer> diff, int low, int high) {
        int[] offset = new int[diff.size() + 1];
        offset[0] = 0;
        for(int i = 0; i < diff.size(); ++i) {
            offset[i + 1] = offset[i] - diff.get(i);
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : offset) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int upper = high - max;
        int lower = low - min;
        return upper - lower + 1;
    }


}
