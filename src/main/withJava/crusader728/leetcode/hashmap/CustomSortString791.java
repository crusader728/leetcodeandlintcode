package withJava.crusader728.leetcode.hashmap;

public class CustomSortString791 {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for(int i = 0; i < s.length(); ++i) {
            int key = s.charAt(i) - 'a';
            cnt[key]++;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < order.length(); ++i) {
            int key = order.charAt(i) - 'a';
            for(int c = 0; c < cnt[key]; ++c) {
                builder.append(order.charAt(i));
            }
            cnt[key] = 0;
        }
        for(int i = 0; i < cnt.length; ++i) {
            if(cnt[i] != 0) {
                for(int c = 0; c < cnt[i]; ++c) {
                    builder.append((char)('a' + i));
                }
            }
        }
        return builder.toString();

    }
}
