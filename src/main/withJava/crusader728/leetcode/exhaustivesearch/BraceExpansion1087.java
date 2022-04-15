package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion1087 {
    public String[] expand(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfs(s, 0, builder, result);
        return result.toArray(new String[result.size()]);
    }

    private void dfs(String s, int i, StringBuilder builder, List<String> result) {
        if(i == s.length()) {
            result.add(builder.toString());
            return;
        }

        if(s.charAt(i) != '{') {
            builder.append(s.charAt(i));
            dfs(s, i + 1, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        } else {
            int pos = s.indexOf('}', i);
            String[] parts = s.substring(i + 1, pos).split(",");
            Arrays.sort(parts);
            for(int k = 0; k < parts.length; ++k) {
                if(parts[k].length() != 1) {
                    continue;
                } else {
                    builder.append(parts[k].charAt(0));
                    dfs(s, pos + 1, builder, result);
                    builder.deleteCharAt(builder.length() - 1);
                }
            }
        }
    }
}
