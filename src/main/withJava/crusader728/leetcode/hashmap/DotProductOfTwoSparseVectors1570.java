package withJava.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DotProductOfTwoSparseVectors1570 {
    class SparseVector {
        private Map<Integer, Integer> kvs;
        SparseVector(int[] nums) {
            kvs = new HashMap<>();
            for(int i = 0; i < nums.length; ++i) {
                if(nums[i] != 0) {
                    kvs.put(i, nums[i]);
                }
            }
        }

        public Map<Integer, Integer> getKvs() {
            return kvs;
        }

        public int dotProduct(SparseVector vec) {
            Set<Integer> thisKeys = this.kvs.keySet();
            thisKeys.retainAll(vec.getKvs().keySet());
            int sum = 0;
            for(Integer k: thisKeys) {
                sum += kvs.get(k) * vec.getKvs().get(k);
            }
            return sum;
        }
    }
}
