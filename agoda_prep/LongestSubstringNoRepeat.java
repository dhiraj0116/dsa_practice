package agoda_prep;

import java.util.HashMap;
import java.util.Map;

// longest substring without repeating characters
//Approach: sliding window with a map of last seen index
public class LongestSubstringNoRepeat {

    public static void main(String[] args) {
        System.out.println(LongestSubstringNoRepeat.maxlength("abcabcdbb"));
    }

    private static int maxlength(String input){
        if(input == null) throw new IllegalArgumentException("Input is null");

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int best = 0;
        for(int right = 0; right < input.length(); right++){
            char ch = input.charAt(right);
            if(map.containsKey(ch)){
                left = Math.max(left, map.get(ch) + 1);
            }
            map.put(ch, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
