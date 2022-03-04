package withJava.crusader728.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache146 {
    static class LRUCache {
        private static class CacheNode {
            CacheNode prev;
            CacheNode next;
            int value;
            int key;

            CacheNode(int k, int v) {
                key = k;
                value = v;
            }
        }

        private static class DoubleLinkedList {
            CacheNode first;
            CacheNode last;

            DoubleLinkedList() {
                first = new CacheNode(-1, -1);
                last = new CacheNode(-1, -1);
                first.next = last;
                last.prev = first;
            }

            void appendFirst(CacheNode n) {
                CacheNode prevHead = first.next;
                n.next = prevHead;
                n.prev = first;
                prevHead.prev = n;
                first.next = n;
            }

            CacheNode removeLast() {
                if(last.prev != first) {
                    CacheNode prevTail = last.prev;
                    last.prev = prevTail.prev;
                    prevTail.prev.next = last;
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
            }
        }

        private Map<Integer, CacheNode> map;
        private DoubleLinkedList doubleLinkedList;
        int capacity;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            this.doubleLinkedList = new DoubleLinkedList();
        }
        
        public int get(int key) {
            if(map.containsKey(key)) {
                CacheNode node = map.get(key);
                doubleLinkedList.removeCacheNode(node);
                doubleLinkedList.appendFirst(node);
                return node.value;
            } else {
                return -1;
            }
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)) {
                CacheNode node = map.get(key);
                node.value = value;
                doubleLinkedList.removeCacheNode(node);
                doubleLinkedList.appendFirst(node);
            } else {
                CacheNode node = new CacheNode(key, value);
                this.doubleLinkedList.appendFirst(node);
                map.put(key, node);
            }

            if(map.size() > capacity) {
                CacheNode n = doubleLinkedList.removeLast();
                map.remove(n.key);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
