package withJava.crusader728.leetcode.dp;

public class RegularExpressionMatching10 {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];

        for(int i = s.length(); i >= 0; --i) {
            for(int j = p.length(); j >= 0; --j) {
                if(i == s.length() && j == p.length()) {
                    match[i][j] = true;
                } else if(i == s.length()) {
                    if(j == p.length() - 1) {
                        match[i][j] = false;
                    } else if(p.charAt(j) != '*' && p.charAt(j + 1) == '*') {
                        match[i][j] = match[i][j + 2];
                    } else {
                        match[i][j] = false;
                    }
                } else if(j == p.length()) {
                    match[i][j] = false;
                } else {
                    if(p.charAt(j) == '*') {
                        match[i][j] = false;
                    } else if(p.charAt(j) == '.') {
                        if(j == p.length() - 1) {
                            match[i][j] = match[i + 1][j + 1];
                        } else if(p.charAt(j + 1) == '*') {
                            match[i][j] = match[i][j + 2] || match[i + 1][j];
                        } else {
                            match[i][j] = match[i + 1][j + 1];
                        }
                    } else {
                        if(j < p.length() - 1 && p.charAt(j + 1) == '*') {
                            match[i][j] = match[i][j + 2] || (p.charAt(j) == s.charAt(i) && match[i + 1][j]);
                        } else {
                            match[i][j] = p.charAt(j) == s.charAt(i) && match[i + 1][j + 1];
                        }
                    }
                }
            }
        }
        return match[0][0];
    }

    public static void main(String[] args) {
        RegularExpressionMatching10 sol = new RegularExpressionMatching10();
        System.out.println(sol.isMatch("ab", ".*c"));
    }
}
