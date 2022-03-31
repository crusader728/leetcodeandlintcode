package withJava.crusader728.leetcode.stack;

import java.util.*;

public class ExclusiveTimeOfFunctions636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        List<Info> infos = parseLogs(logs);
        Collections.sort(infos, tsComparator);
        Stack<Info> stack = new Stack<>();
        int[] result = new int[n];

        for(Info info: infos) {
            if(info.isStart) {
                if(!stack.isEmpty()) {
                    int current = info.timestamp;
                    stack.peek().total += current - stack.peek().timestamp;
                }
                stack.push(info);
            } else {
                if(info.fId != stack.peek().fId) {
                    throw new IllegalStateException();
                } 
                Info pop = stack.pop();
                result[pop.fId] += info.timestamp - pop.timestamp + 1 + pop.total;
                if(!stack.isEmpty()) {
                    stack.peek().timestamp = info.timestamp + 1;
                }
            }
        }

        return result;
    }

    private List<Info> parseLogs(List<String> logs) {
        List<Info> infos = new ArrayList<>();
        for(String line: logs) {
            infos.add(parseLog(line));
        }
        return infos;
    }

    private Info parseLog(String line) {
        String[] split = line.split(":");
        return new Info(Integer.parseInt(split[0]), "start".equalsIgnoreCase(split[1]), Integer.parseInt(split[2]));
    }

    private static class Info {
        int fId;
        boolean isStart;
        int timestamp;
        int total;

        Info(int fId, boolean isStart, int ts) {
            this.fId = fId;
            this.isStart = isStart;
            this.timestamp = ts;
            this.total = 0;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "fId=" + fId +
                    ", isStart=" + isStart +
                    ", timestamp=" + timestamp +
                    ", total=" + total +
                    '}';
        }
    }

    private static Comparator<Info> tsComparator = new Comparator<Info>() {
        @Override
        public int compare(Info x, Info y) {
            return Integer.compare(x.timestamp, y.timestamp);
        }
    };

    public static void main(String[] args) {
        ExclusiveTimeOfFunctions636 exclusiveTimeOfFunctions636 = new ExclusiveTimeOfFunctions636();
        exclusiveTimeOfFunctions636.exclusiveTime(1, Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"));
    }
}
