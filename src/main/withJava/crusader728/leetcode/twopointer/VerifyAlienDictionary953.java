package withJava.crusader728.leetcode.twopointer;

public class VerifyAlienDictionary953 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] o = new int[26];
        for(int i = 0; i < order.length(); ++i) {
            int key = order.charAt(i) - 'a';
            o[key] = i;
        }

        for(int i = 0; i < words.length - 1; ++i) {
            if(!valid(words[i], words[i + 1], o)) {
                return false;
            }
        }
        return true;
    }

    private boolean valid(String string, String string2, int[] o) {
        int i = 0;
        int l1 = string.length();
        int l2 = string2.length();
        while(i < Math.min(l1, l2) && string.charAt(i) == string2.charAt(i)) {
            i++;
        }
        if(i < Math.min(l1, l2)) {
            return o[string.charAt(i) - 'a'] < o[string2.charAt(i) - 'a'];
        } else {
            return string.length() <= string2.length();
        }
    }
}
