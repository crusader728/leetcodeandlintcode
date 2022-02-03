package withJava.crusader728.leetcode.slidingwindow;

public class FindSubstringWithGivenHashValue2156 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int l = s.length() - 1;
        int r = s.length() - 1;
        long currentHash = 0;
        long pk = 1;
        for(int i = 0; i < k; ++i) {
            pk = pk * power % modulo;
        }
        String result = null;
        while(l >= 0) {
            currentHash = (currentHash * power % modulo + (s.charAt(l) - 'a' + 1))% modulo;
            if(r - l + 1 > k) {
                while(r >= 0 && r - l + 1 > k) {
                    currentHash = (currentHash - (s.charAt(r) - 'a' + 1) * pk % modulo + modulo) % modulo;
                    r--;
                }
            }
            if(r - l + 1 == k && currentHash == hashValue) {
                result = s.substring(l, r + 1);
            }
            l--;
        }
        return result;   
    }
}
