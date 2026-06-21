package agoda_prep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//unique permutations of a string
//Approach: backtracking with in-place swaps
public class UniquePermutations {

    public static void main(String[] args) {
        System.out.println(UniquePermutations.permutations("AAB"));
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        bruteForce("AAB", "", set);
        System.out.println(set);
    }

    private static List<String> permutations(String input){
        if(input == null) throw new IllegalArgumentException("Invalid Input");
        List<String> result = new ArrayList<>();
        char[] chars = input.toCharArray();
        backtrack(chars, 0, result);
        return  result;
    }

    private static void backtrack(char[] chars, int index, List<String> result){
        if(index == chars.length){
            result.add(new String(chars));
            return;
        }

        Set<Character> set = new HashSet<>();
        for(int i = index; i < chars.length; i++){
            if(!set.add(chars[i])) continue;
            swap(chars, index, i);
            backtrack(chars, i + 1, result);
            swap(chars, index, i);
        }
    }

    private static void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static void bruteForce(String input, String prefix, Set<String> result){
        if(input.isEmpty()){
//            System.out.println(prefix);
            result.add(prefix);
        } else {
            for(int i = 0; i < input.length(); i++){
                char ch = input.charAt(i);
                String remaining = input.substring(0, i) + input.substring(i+1);
                bruteForce(remaining, prefix + ch, result);
            }
        }



    }
}