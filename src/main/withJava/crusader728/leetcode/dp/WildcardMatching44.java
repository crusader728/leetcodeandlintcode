package withJava.crusader728.leetcode.dp;

public class WildcardMatching44 {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        String pSimplified = simplify(p);
        if(s.equals(pSimplified)) {
            return true;
        }
        if("*".equals(pSimplified)) {
            return true;
        }

        int sLen = s.length();
        int pLen = pSimplified.length();
        boolean[][] match = new boolean[sLen + 1][pLen + 1];
        for(int i = sLen; i >= 0; --i) {
            for(int j = pLen; j >= 0; --j) {
                if(i == sLen && j == pLen) {
                    match[i][j] = true;
                } else if(i == sLen) {
                    if(pSimplified.charAt(j) != '*') {
                        match[i][j] = false;
                    } else {
                        match[i][j] = match[i][j + 1];
                    }
                } else if(j == pLen) {
                    match[i][j] = false;
                } else if(pSimplified.charAt(j) != '*' && pSimplified.charAt(j) != '?') {
                    match[i][j] = s.charAt(i) == pSimplified.charAt(j) ? match[i + 1][j + 1] : false;
                } else if(pSimplified.charAt(j) == '?') {
                    match[i][j] = match[i + 1][j + 1];
                } else {
                    match[i][j] = match[i][j + 1] || match[i + 1][j];
                }
            }
        }
        return match[0][0];
    }

    private String simplify(String p) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while(i < p.length()) {
            if(p.charAt(i) != '*') {
                builder.append(p.charAt(i));
                i++;
            } else {
                int j = i;
                while(j < p.length() && p.charAt(j) == '*') {
                    j++;
                }
                builder.append('*');
                i = j;
            }
        }
        return builder.toString();
    }
}
