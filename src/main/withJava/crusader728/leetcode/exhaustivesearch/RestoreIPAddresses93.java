package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RestoreIPAddresses93 {
    public List<String> restoreIpAddresses(String s) {
        if(s == null || s.length() < 4 || s.length() > 12) {
            throw new IllegalArgumentException();
        }

        Set<String> result = new HashSet<>();
        List<String> parts = new ArrayList<>();

        backtrack(s, 0, 0, parts, result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int idx, int acc, List<String> parts, Set<String> results) {
        if(idx == s.length()) {
            if(0 < acc && acc < 256 && parts.size() == 3) {
                parts.add(String.valueOf(acc));
                results.add(String.join(".", parts));
                parts.remove(parts.size() - 1);
            } else if(parts.size() == 4 && acc == 0) {
                results.add(String.join(".", parts));
            }
            return;
        }
        int newAcc = acc * 10 + s.charAt(idx) - '0';
        if(newAcc == acc) {
            parts.add(String.valueOf(newAcc));
            backtrack(s, idx + 1, 0, parts, results);
            parts.remove(parts.size() - 1);
            return;
        }
        if(newAcc < 256) {
            backtrack(s, idx + 1, newAcc, parts, results);
            parts.add(String.valueOf(newAcc));
            backtrack(s, idx + 1, 0, parts, results);
            parts.remove(parts.size() - 1);
        }
    }


    public static void main(String[] args) {
        RestoreIPAddresses93 restoreIPAddresses93 = new RestoreIPAddresses93();
        List<String> strings = restoreIPAddresses93.restoreIpAddresses("0000");
        strings.forEach(System.out::println);
    }
}
