package withJava.crusader728.leetcode.ood;

import java.util.*;
import java.util.Map.Entry;

public class TimeBasedKeyValueStore981 {
    private static class TimeMap {
        Map<String, TreeMap<Integer, String>> keyTimestampedValueMapping;

        public TimeMap() {
            keyTimestampedValueMapping = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            if(!keyTimestampedValueMapping.containsKey(key)) {
                keyTimestampedValueMapping.put(key, new TreeMap<>());
            }

            keyTimestampedValueMapping.get(key).put(timestamp, value);
        }
        
        public String get(String key, int timestamp) {
            if(!keyTimestampedValueMapping.containsKey(key)) {
                return "";
            } else {
                Entry<Integer, String> floorEntry = keyTimestampedValueMapping.get(key).floorEntry(timestamp);
                if(floorEntry == null) {
                    return "";
                } else {
                    return floorEntry.getValue();
                }
            }
        }
    }
}
