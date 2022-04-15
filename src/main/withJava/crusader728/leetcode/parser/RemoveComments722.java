package withJava.crusader728.leetcode.parser;

import java.util.*;

public class RemoveComments722 {
    public List<String> removeComments(String[] source) {
        if(source == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        State state = new Text(new StringBuilder());
        for(String line: source) {
            Output output = state.read(line, 0);
            if(!(output.builder.length() == 0)) {
                result.add(output.builder.toString());
            }
            state = output.state;
        }
        return result;
    }

    private static class Output {
        Output() {
            
        }
        State state;
        StringBuilder builder;
    }
    private static interface State {
        Output read(String line, int idx);
    }

    private final static class Text implements State {
        StringBuilder builder;

        public Text(StringBuilder stringBuilder) {
            this.builder = stringBuilder;
        }

        @Override
        public Output read(String line, int idx) {
            int i = idx;
            while(i < line.length()) {
                if(i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                    //block 
                    InBlock inBlock = new InBlock(this.builder);
                    return inBlock.read(line, i + 2);
                } else if(i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                    //line comment
                    Output output = new Output();
                    output.builder = this.builder;
                    output.state = new Text(new StringBuilder());
                    return output;
                } else if(line.charAt(i) == '"') {
                    builder.append('"');
                    InQuote inQuote = new InQuote(this.builder);
                    return inQuote.read(line, i + 1);
                } else {
                    builder.append(line.charAt(i));
                    i++;
                }
            }
            Output output = new Output();
            output.builder = this.builder;
            output.state = new Text(new StringBuilder());
            return output;

        }
    }



    private final static class InBlock implements State {
        public InBlock(StringBuilder builder) {
            this.builder = builder;
        }

        private StringBuilder builder;

        @Override
        public Output read(String line, int idx) {
            int i = idx;
            while(i < line.length()) {
                if(i < line.length() - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                    Text text = new Text(this.builder);
                    return text.read(line, i + 2);
                } else {
                    i++;
                }
            }
            Output output = new Output();
            output.builder = new StringBuilder();
            output.state = this;
            return output;
        }


    }

    private final static class InQuote implements State {
        private StringBuilder builder;
        public InQuote(StringBuilder builder) {
            this.builder = builder;
        }

        @Override
        public Output read(String line, int idx) {
            int i = idx;
            while(i < line.length()) {
                builder.append(line.charAt(i));
                if(line.charAt(i) == '"') {
                    Text text = new Text(this.builder);
                    return text.read(line, i + 1);
                } else {
                    i++;
                }
            }
            throw new IllegalStateException();
        }
    }
}
