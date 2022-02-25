package withJava.crusader728.leetcode.parser;

import java.util.ArrayList;
import java.util.List;

public class CompaerVersionNumbers165 {
    public int compareVersion(String version1, String version2) {
       List<Integer> r1 = parse(version1);
       List<Integer> r2 = parse(version2);
       
       int i = 0;
       while(i < r1.size() && i < r2.size()) {
           if(r1.get(i) < r2.get(i)) {
               return -1;
           } else if(r1.get(i) > r2.get(i)) {
               return 1;
           } else {
               i++;
           }
       }
       if(i == r1.size() && i == r2.size()) {
            return 0;
       } else if(i == r1.size()) {
           for(int j = i; j < r2.size(); ++j) {
               if(r2.get(j) != 0) {
                   return -1;
               }
           }
           return 0;
       } else {
        for(int j = i; j < r1.size(); ++j) {
            if(r1.get(j) != 0) {
                return 1;
            }
        }
        return 0;
       }
    }

    List<Integer> parse(String version) {
        String[] tokens = version.split("\\.");
        List<Integer> result = new ArrayList<>();
        for(String token: tokens) {
            if(token.isEmpty()) {
                result.add(0);
            } else {
                result.add(Integer.parseInt(token));
            }
        }
        return result;
    }
}
