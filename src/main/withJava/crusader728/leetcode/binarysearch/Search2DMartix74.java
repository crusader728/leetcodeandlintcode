package withJava.crusader728.leetcode.binarysearch;

public class Search2DMartix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null) {
            throw new IllegalArgumentException();
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0;
        int high = m;
        while(low < high) {
            int mid = low + (high - low) / 2;
            int value = matrix[mid][0];
            if(value < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if(low == 0) {
            if(matrix[low][0] == target) {
                return true;
            } else {
                return false;
            }
        } else {
            if(low == m) {
                low = low - 1;
            } else {
                if(matrix[low][0] == target) {
                    return true;
                } else {
                    low = low - 1;
                }
            }
            int left = 0;
            int right = n;
            while(left < right) {
                int mid = left + (right - left) / 2;
                int value = matrix[low][mid];
                if(value < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left == n ? false : matrix[low][left] == target;
        }
    }

    public static void main(String[] args) {
        Search2DMartix74 search2DMartix74 = new Search2DMartix74();
        System.out.println(search2DMartix74.searchMatrix(new int[][] {
            {1,3,5,7},
            {10, 11, 16, 20},
                {23, 30, 34,60}
        }, 3));
    }


}
