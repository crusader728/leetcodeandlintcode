package withJava.crusader728.leetcode.ood;

import java.util.*;

public class InsertDeleteGetRandom380 {
    private static class RandomizedSet {
        List<Integer> values;
        Map<Integer, Integer> valToIndex;
        ThreadLocal<Random> rng = ThreadLocal.withInitial(() -> new Random());

        public RandomizedSet() {
            this.values = new ArrayList<>();   
        }
        
        public boolean insert(int val) {
            if(!valToIndex.containsKey(val)) {
                values.add(val);
                valToIndex.put(val, values.size() - 1);
                return true;
            } else {
                return false;
            }
        }
        
        public boolean remove(int val) {
            if(!valToIndex.containsKey(val)) {
                return false;
            } else if(values.size() == 1) {
                valToIndex.clear();
                values.clear();
                return true;
            } else {
                int idx = valToIndex.get(val);
                int toSwapIdx = values.size() - 1;
                int toSwapVal = values.get(toSwapIdx);
                swap(idx, toSwapIdx);
                values.remove(values.size() - 1);
                valToIndex.put(toSwapVal, idx);
                valToIndex.remove(val);
                return true;
            }
        }
        
        private void swap(int idx, int toSwapIdx) {
            int temp = values.get(idx);
            values.set(idx, values.get(toSwapIdx));
            values.set(toSwapIdx, temp);
        }

        public int getRandom() {
            int idx = this.rng.get().nextInt(values.size());
            return values.get(idx);
        }
    }
    
}
