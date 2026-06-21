package pattern.arrays_strings.manipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/
public class Valid_Anagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray, tArray);
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if(!map.containsKey(c)){
                return false;
            }
            map.put(c, map.get(c) - 1);
            if(map.get(c) < 0){
                return false;
            }
        }

        for(int count : map.values()){
            if(count != 0){
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCount = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++; // Increment for s
            charCount[t.charAt(i) - 'a']--; // Decrement for t
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        Valid_Anagram valid_anagram = new Valid_Anagram();
        System.out.println(valid_anagram.isAnagram("abc", "abc"));
    }
}
