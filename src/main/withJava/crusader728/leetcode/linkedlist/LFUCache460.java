package withJava.crusader728.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache460 {
    static class LFUCache {
        private static class CacheNode {
            int value;
            int key;
            int freq;
            CacheNode prev;
            CacheNode next;
        }

        private static class DoubleLinkedList {
            CacheNode first;
            CacheNode last;
            int size;

            DoubleLinkedList() {
                first = new CacheNode();
                last = new CacheNode();
                first.next = last;
                last.prev = first;
                size = 0;
            }

            void appendFirst(CacheNode n) {
                CacheNode prevHead = first.next;
                n.next = prevHead;
                n.prev = first;
                prevHead.prev = n;
                first.next = n;
                size++;
            }

            CacheNode removeLast() {
                if(last.prev != first) {
                    CacheNode prevTail = last.prev;
                    last.prev = prevTail.prev;
                    prevTail.prev.next = last;
                    size--;
                    return prevTail;
                } else {
                    return null;
                }
            }

            void removeCacheNode(CacheNode n) {
                CacheNode prev = n.prev;
                CacheNode next = n.next;
                prev.next = next;
                next.prev = prev;
                n.prev = null;
                n.next = null;
                size--;
            }
        }

        private int capacity;
        private TreeMap<Integer, DoubleLinkedList> freqNodeMap;
        private Map<Integer, CacheNode> keyNodeMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            freqNodeMap = new TreeMap<>();
            keyNodeMap = new HashMap<>();
        }
        
        public int get(int key) {
            if(!keyNodeMap.containsKey(key)) {
                return -1;
            } else {
                CacheNode cacheNode = keyNodeMap.get(key);
                DoubleLinkedList linkedList = freqNodeMap.get(cacheNode.freq);
                linkedList.removeCacheNode(cacheNode);
                if(linkedList.size == 0) {
                    freqNodeMap.remove(cacheNode.freq);
                }
                cacheNode.freq = cacheNode.freq + 1;
                if(!freqNodeMap.containsKey(cacheNode.freq)) {
                    freqNodeMap.put(cacheNode.freq, new DoubleLinkedList());
                }
                freqNodeMap.get(cacheNode.freq).appendFirst(cacheNode);
                return cacheNode.value;
            }
        }
        
        public void put(int key, int value) {
            if(this.capacity == 0) {
                return;
            }
            if(keyNodeMap.size() == capacity && !keyNodeMap.containsKey(key)) {
                Integer lowestFreq = freqNodeMap.firstKey();
                CacheNode last = freqNodeMap.get(lowestFreq).removeLast();
                keyNodeMap.remove(last.key);
                if(freqNodeMap.get(lowestFreq).size == 0) {
                    freqNodeMap.remove(lowestFreq);
                }
            }
            CacheNode node;
            if(!keyNodeMap.containsKey(key)) {
                node = new CacheNode();
                node.key = key;
                node.value = value;
                node.freq = 1;
                keyNodeMap.put(key, node);
            } else {
                node = keyNodeMap.get(key);
                node.value = value;
                freqNodeMap.get(node.freq).removeCacheNode(node);
                if(freqNodeMap.get(node.freq).size == 0) {
                    freqNodeMap.remove(node.freq);
                }
                node.freq = node.freq + 1;
            }
            if(!freqNodeMap.containsKey(node.freq)) {
                freqNodeMap.put(node.freq, new DoubleLinkedList());
            }
            freqNodeMap.get(node.freq).appendFirst(node);
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        System.out.println(lfuCache.get(2));
        lfuCache.put(2, 6);
        System.out.println(lfuCache.get(1));
        lfuCache.put(1, 5);
        lfuCache.put(1, 2);
        System.out.println(lfuCache.get(1));
    }
}
