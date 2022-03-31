package withJava.crusader728.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindKCloesetElements658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int idx = binarySearch(arr, x);
        if(idx >= arr.length) {
            for(int i = 0; i < k; ++i) {
                result.add(arr[arr.length - k + i]);
            }
        } else if(idx == 0) {
            for(int i = 0; i < k; ++i) {
                result.add(arr[idx + i]);
            }
        } else {
            int l = idx - 1;
            int r = idx;
            int count = 0;
            while(count < k) {
                while(l >= 0 && r < arr.length && count < k) {
                    if(Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)) {
                        l--;
                    } else {
                        r++;
                    }
                    count++;
                }
                if(count < k) {
                    while(l >= 0 && count < k) {
                        l--;
                        count++;
                    }
                    while(r < arr.length && count < k) {
                        r++;
                        count++;
                    }
                } 
            }
            for(int i = l + 1; i < r; ++i) {
                result.add(arr[i]);
            }
        }

        return result;
    }

    private int binarySearch(int[] arr, int x) {
        int l = 0;
        int r = arr.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            int probe = arr[mid];
            if(probe < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
