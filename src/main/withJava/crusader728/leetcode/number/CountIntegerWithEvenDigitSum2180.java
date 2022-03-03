package withJava.crusader728.leetcode.number;

public class CountIntegerWithEvenDigitSum2180 {
    public int countEven(int num) {
        int ans = 0;
        for(int i = 1; i <= num; ++i) {
            if(isDigitSumEven(i)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isDigitSumEven(int i) {
        int n = i;
        int sum = 0;
        while(n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum % 2 == 0;
    }
}
