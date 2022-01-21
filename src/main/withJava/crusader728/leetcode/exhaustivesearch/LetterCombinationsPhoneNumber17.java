package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.*;

public class LetterCombinationsPhoneNumber17 {
    static Map<Character, List<Character>> mapping = new HashMap<Character, List<Character>>() {{
       put('2', Arrays.asList('a', 'b', 'c'));
       put('3', Arrays.asList('d', 'e', 'f'));
       put('4', Arrays.asList('g', 'h', 'i'));
       put('5', Arrays.asList('j', 'k', 'l'));
       put('6', Arrays.asList('m', 'n', 'o'));
       put('7', Arrays.asList('p', 'q', 'r', 's'));
       put('8', Arrays.asList('t', 'u', 'v'));
       put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }};
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        helper(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void helper(String digits, int i, StringBuilder builder, List<String> acc) {
        if(i == digits.length()) {
            acc.add(builder.toString());
        } else {
            for(Character ch: mapping.get(digits.charAt(i))) {
                builder.append(ch);
                helper(digits, i + 1, builder, acc);
                builder.deleteCharAt(i);
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber17 letterCombinationsPhoneNumber17 = new LetterCombinationsPhoneNumber17();
        List<String> strings = letterCombinationsPhoneNumber17.letterCombinations("23");
        strings.forEach(System.out::println);
    }
}
