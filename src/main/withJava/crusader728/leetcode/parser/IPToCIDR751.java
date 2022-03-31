package withJava.crusader728.leetcode.parser;

import java.util.*;

public class IPToCIDR751 {
    public List<String> ipToCIDR(String ip, int n) {
        if(ip == null || n < 0) {
            throw new IllegalArgumentException();
        }
        int remaining = n;
        long ipValue = ipToValue(ip);
        List<String> ans = new ArrayList<>();
        while(remaining > 0) {
            long a = Long.lowestOneBit(ipValue);
            long b = Long.highestOneBit(remaining);
            long block = a == 0 ? b : Math.min(a, b);   //number of ips of current block
            String cidr = valueToIp(ipValue) + "/" + (32 - Long.numberOfTrailingZeros(block));
            ans.add(cidr);
            ipValue += block;
            remaining -= block;
        }
        return ans;
    }

    private long ipToValue(String ip) {
        String[] temp = ip.split("[.]");
        return Long.parseLong(temp[0]) << 24 | Long.parseLong(temp[1]) << 16 | 
               Long.parseLong(temp[2]) << 8 | Long.parseLong(temp[3]);
    }
    private String valueToIp(long ipValue) {
        return String.format("%d.%d.%d.%d", ipValue >>> 24, ipValue >>> 16 & 0xFF, ipValue >>> 8 & 0xFF, ipValue & 0xFF);
    }

    public static void main(String[] args) {
        IPToCIDR751 ipToCIDR751 = new IPToCIDR751();
        ipToCIDR751.ipToCIDR("0.0.0.0",
        2);
    }
}
