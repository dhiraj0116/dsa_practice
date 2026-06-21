package random;

import java.util.Scanner;

public class StringPermutations {

    public static void main(String[] args) {
//        try(Scanner sc = new Scanner(System.in)) {
//            System.out.println("Enter String:");
//            String str = sc.nextLine();
        String str = "ABC";
            System.out.println("All Combinations: ");
            generatePermutations(str, "");
//        }
    }

    private static void generatePermutations(String str, String prefix) {
        if(str.isEmpty()){
            System.out.println(prefix);
        } else {
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                String remaining = str.substring(0, i) + str.substring(i+1);
                generatePermutations(remaining, prefix + ch);
            }
        }
    }
}
