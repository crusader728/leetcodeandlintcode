package withJava.crusader728.leetcode.ood;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class MaximumFrequencyStack895 {
    static class FreqStack {
        Map<Integer, Integer> valFreqMap;
        TreeMap<Integer, Stack<Integer>> freqStackMap;

        public FreqStack() {
            valFreqMap = new HashMap<>();
            freqStackMap = new TreeMap<>();
        }
        
        public void push(int val) {
            if(!valFreqMap.containsKey(val)) {
                if(!freqStackMap.containsKey(1)) {
                    freqStackMap.put(1, new Stack<>());
                }
                freqStackMap.get(1).push(val);
                valFreqMap.put(val, 1);
            } else {
                int freq = valFreqMap.get(val) + 1;
                freqStackMap.computeIfAbsent(freq, k -> new Stack<>()).push(val);
                valFreqMap.put(val, freq);
            }
        }
        
        public int pop() {
            int maxFreq = freqStackMap.lastKey();
            int result = freqStackMap.get(maxFreq).pop();
            if(freqStackMap.get(maxFreq).size() == 0) {
                freqStackMap.remove(maxFreq);
            }
            int newFreq = maxFreq - 1;
            if(newFreq == 0) {
                valFreqMap.remove(result);
            } else {
                valFreqMap.put(result, newFreq);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(1);
        freqStack.push(1);
        freqStack.push(1);
        freqStack.push(2);
        freqStack.pop();
        freqStack.pop();
        freqStack.push(2);
        freqStack.push(2);
        freqStack.push(1);
    }
}
