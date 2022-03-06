package withJava.crusader728.leetcode.parser;

import java.util.ArrayList;
import java.util.List;

public class CellsInARangeOnExcelSheet2194 {
    public List<String> cellsInRange(String s) {
        List<String> result = new ArrayList<>();

        char left = s.charAt(0);
        char right = s.charAt(3);
        int low = s.charAt(1) - '0';
        int high = s.charAt(4) - '0';
        for(char x = left; x <= right; x++) {
            for(int y = low; y <= high; y++) {
                result.add(String.format("%s%d", x, y));
            }
        }
        return result;
    }
}
