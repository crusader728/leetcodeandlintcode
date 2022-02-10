package withJava.crusader728.leetcode.twopointer;

public class Flatten2DVector251 {
    private int row;
    private int col;
    private int[][] content;
    public Flatten2DVector251(int[][] vec) {
        row = 0;
        col = 0;
        this.content = vec;
    }

    public int next() {
        int value = content[row][col];
        col++;
        if(col >= content[row].length) {
            row += 1;
            col = 0;
        }
        return value;
    }

    public boolean hasNext() {
        while(row < content.length && (col >= content[row].length)) {
            row += 1;
            col = 0;
        }
        return row < content.length && col < content[row].length;
    }
}
