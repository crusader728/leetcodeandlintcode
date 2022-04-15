package withJava.crusader728.leetcode.binarysearch;

public class Search2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length;
        while(l < r) {
            int mid = (r - l) / 2 + l;
            if(matrix[mid][0] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        if(l == 0 && matrix[l][0] == target) {
            return true;
        } else if(l == 0) {
            return false;
        } else if(l < matrix.length && matrix[l][0] == target) {
            return true;
        } else {
            int x = l - 1;
            int low = 0;
            int high = matrix[x].length;
            while(low < high) {
                int mid = low + (high - low) / 2;
                if(matrix[x][mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if(low >= matrix[x].length) {
                return false;
            } else {
                return matrix[x][low] == target;
            }
        }
    }

    public static void main(String[] args) {
        Search2DMatrix74 search2DMatrix74 = new Search2DMatrix74();
        System.out.println(search2DMatrix74.searchMatrix(new int[][] {
            {1, 3}
        }, 3));
    }


}
