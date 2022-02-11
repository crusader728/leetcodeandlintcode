package withJava.crusader728.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath71 {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) {
            throw new IllegalArgumentException();
        }

        Deque<String> nodes = new ArrayDeque<>();
        String[] tokens = path.split("/");
        for(int i = 0; i < tokens.length; ++i) {
            if(tokens[i].isEmpty()) {
                continue;
            } else if(tokens[i].equals(".")) {
                continue;
            } else if(tokens[i].equals("..")) {
                if(!nodes.isEmpty()) {
                    nodes.pop();
                }
            } else {
                nodes.push(tokens[i]);
            }
        }

        if(nodes.isEmpty()) {
            return "/";
        } else {
            StringBuilder builder = new StringBuilder();
            while(!nodes.isEmpty()) {
                builder.append("/").append(nodes.removeLast());
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        SimplifyPath71 simplifyPath71 = new SimplifyPath71();
        System.out.println(simplifyPath71.simplifyPath("/home//.././foo/"));
    }
}
