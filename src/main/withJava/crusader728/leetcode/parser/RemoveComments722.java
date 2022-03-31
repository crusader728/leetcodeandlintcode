package withJava.crusader728.leetcode.parser;

import java.util.*;

public class RemoveComments722 {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        State currentState = State.NORMAL;
        StringBuilder builder = new StringBuilder();
        for(String line: source) {
            for(int i = 0; i < line.length();) {
                switch(currentState) {
                    case IN_LINE_COMMENT:
                        if(i == line.length() - 1) {
                            currentState = State.NORMAL;
                        }
                        i++;
                        break;
                    case IN_BLOCK_COMMENT:
                        if(i < line.length() - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                            currentState = State.NORMAL;
                            i = i + 2;
                        } else {
                            i++;
                        }
                        break;
                    case NORMAL:
                        if(i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                            currentState = State.IN_LINE_COMMENT;
                            i = i + 2;
                        } else if(i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                            currentState = State.IN_BLOCK_COMMENT;
                            i = i + 2;
                        } else if(line.charAt(i) == '"') {
                            currentState = State.IN_STRING;
                            builder.append(line.charAt(i));
                            i++;
                        } else {
                            builder.append(line.charAt(i));
                            i++;
                        }
                        break;
                    case IN_STRING:
                        if(i < line.length() - 1 && line.charAt(i) == '\\' && line.charAt(i + 1) == '"') {
                            builder.append("\"");
                            i += 2;
                        } else if(line.charAt(i) == '"') {
                            currentState = State.NORMAL;
                            builder.append('"');
                            i += 1;
                        } else {
                            builder.append(line.charAt(i));
                            i += 1;
                        }
                    default:
                        break;
                }
            }
            if(!currentState.equals(State.IN_BLOCK_COMMENT)) {
                result.add(builder.toString());
                builder.delete(0, builder.length());
            }
        }
        return result;
    }

    private static enum State {
        IN_LINE_COMMENT,
        IN_BLOCK_COMMENT,
        IN_STRING,
        NORMAL
    }
}
