package withJava.crusader728.leetcode.greedy;

import java.util.Arrays;

public class GasStation134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = Arrays.stream(gas).sum();
        int totalCost = Arrays.stream(cost).sum();
        if(totalCost > totalGas) {
            return -1;
        }
        int remaining = 0;
        int start = 0;
        for(int i = 0; i < gas.length; ++i) {
            remaining = remaining + gas[i] - cost[i];
            if(remaining < 0) {
                start = i + 1;
                remaining = 0;
            }
        }
        return start;
    }
}
