package withJava.crusader728.leetcode.ood;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AllOOneDataStructure432 {
    private static class AllOne {
        Map<String, Integer> keyCounterMapping;
        Map<Integer, Node> counterNodeMapping;
        Node head;
        Node tail;

        private static class Node {
            int freq;
            Set<String> keys;
            Node prev;
            Node next;
        }

        public AllOne() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.next = head;
            keyCounterMapping = new HashMap<>();
            counterNodeMapping = new HashMap<>();   
        }
        
        public void inc(String key) {
            
        }
        
        public void dec(String key) {
            
        }
        
        public String getMaxKey() {
            
        }
        
        public String getMinKey() {
            
        }
    }
}
