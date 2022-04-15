package withJava.crusader728.leetcode.divideandconquer;

public class SearchIn2DMatrixII240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return searchInRetangle(matrix, 0, 0, matrix.length, matrix[matrix.length -1].length, target);
    }

    private boolean searchInRetangle(int[][] matrix, int lx, int ly, int rx, int ry, int target) {
        if(lx >= rx || ly >= ry) {
            return false;
        }
        int rowMid = lx + (rx - lx) / 2;
        int colMid = ly + (ry - ly) / 2;
        if(matrix[rowMid][colMid] == target) {
            return true;
        } else if(matrix[rowMid][colMid] < target) {
            return searchInRetangle(matrix, rowMid + 1, ly, rx, colMid + 1, target)
                || searchInRetangle(matrix, lx, colMid + 1, rowMid + 1, ry, target)
                || searchInRetangle(matrix, rowMid + 1, colMid + 1, rx, ry, target);
        } else {
            return searchInRetangle(matrix, lx, ly, rowMid, colMid, target)
                || searchInRetangle(matrix, lx, colMid, rowMid, ry, target)
                || searchInRetangle(matrix, rowMid, ly, rx, colMid, target);
        }
    }

    public static void main(String[] args) {
        SearchIn2DMatrixII240 searchIn2DMatrixII240 = new SearchIn2DMatrixII240();
        System.out.println(searchIn2DMatrixII240.searchMatrix(new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        }, 20));
    }
}
