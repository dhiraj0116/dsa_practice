package pattern.arrays_strings.manipulation;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/
public class Group_Anagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String ss = new String(chars);
            map.computeIfAbsent(ss, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String ss = getFrequencyKey(s);
            map.computeIfAbsent(ss, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private String getFrequencyKey(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                sb.append((char)(i + 'a')).append(count[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Group_Anagrams g = new Group_Anagrams();
        System.out.println(g.groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
