package withJava.crusader728.leetcode.greedy;

import java.util.Arrays;

public class BoatsToSavePeople881 {
    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);
        int l = 0;
        int r = people.length - 1;
        int count = 0;
        while(l <= r) {
            count++;
            if(people[l] + people[r] <= limit) {
                l++;
            }
            r--;
        }
        return count;
    }
}
