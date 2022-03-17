package withJava.crusader728.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler621 {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) {
            return tasks.length;
        }

        Map<Character, Integer> counter = new HashMap<>();
        int maxFrequency = 0;
        for(char task: tasks) {
            counter.put(task, counter.getOrDefault(task, 0) + 1);
            maxFrequency = Math.max(maxFrequency, counter.get(task));
        }

        int n_max = 0;
        for (int f : counter.values()) {
            if (f == maxFrequency) n_max++;
        }
        
        return Math.max(tasks.length, (maxFrequency - 1) * (n + 1) + n_max);
    }
}
