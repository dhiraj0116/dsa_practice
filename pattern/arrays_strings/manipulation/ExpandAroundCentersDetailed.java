package pattern.arrays_strings.manipulation;

import java.util.*;

public class ExpandAroundCentersDetailed {

    /**
     * APPROACH 2: EXPAND AROUND CENTERS - DETAILED EXPLANATION
     * <p>
     * Core Concept: Every palindrome has a "center"
     * - Odd length palindromes: center is a single character (e.g., "aba" -> center at 'b')
     * - Even length palindromes: center is between two characters (e.g., "abba" -> center between 'b' and 'b')
     * <p>
     * Strategy: For each possible center position, expand outward and count palindromes
     */
    public int countSubstringsExpandCenters(String s) {
        if (s == null || s.isEmpty()) return 0;

        int totalCount = 0;

        // Try every possible center position
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length palindromes (center at position i)
            int oddCount = expandAroundCenter(s, i, i);
            System.out.println("Odd center at index " + i + " (" + s.charAt(i) + "): found " + oddCount + " palindromes");
            totalCount += oddCount;

            // Case 2: Even length palindromes (center between i and i+1)
            if (i < s.length() - 1) {
                int evenCount = expandAroundCenter(s, i, i + 1);
                System.out.println("Even center between " + i + "-" + (i+1) + " (" + s.charAt(i) + s.charAt(i+1) + "): found " + evenCount + " palindromes");
                totalCount += evenCount;
            }
        }

        return totalCount;
    }

    /**
     * Expand around center and count palindromes
     * <p>
     * How it works:
     * 1. Start with left and right pointers at the center
     * 2. While characters at left and right match:
     *    - We found a palindrome! Increment count
     *    - Expand outward (left--, right++)
     * 3. Stop when characters don't match or we reach string boundaries
     */
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        List<String> palindromes = new ArrayList<>();

        // Expand as long as characters match and we're within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // Found a palindrome!
            String palindrome = s.substring(left, right + 1);
            palindromes.add(palindrome);
            count++;

            // Expand outward for next iteration
            left--;
            right++;
        }

        System.out.println("  Found palindromes: " + palindromes);
        return count;
    }

    /**
     * APPROACH 5: OPTIMIZED EXPAND - DETAILED EXPLANATION
     *
     * This is essentially the same as Approach 2, but with some optimizations:
     * 1. Early termination when characters don't match
     * 2. Cleaner code structure
     * 3. Better performance in practice due to fewer method calls
     */
    public int countSubstringsOptimized(String s) {
        if (s == null || s.isEmpty()) return 0;

        int totalCount = 0;

        for (int i = 0; i < s.length(); i++) {
            // Count odd length palindromes
            totalCount += countPalindromesOptimized(s, i, i);

            // Count even length palindromes
            totalCount += countPalindromesOptimized(s, i, i + 1);
        }

        return totalCount;
    }

    /**
     * Optimized version with early termination and inline logic
     */
    private int countPalindromesOptimized(String s, int left, int right) {
        int count = 0;

        // Key optimization: immediate termination when characters don't match
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                count++;        // Found palindrome
                left--;         // Expand left
                right++;        // Expand right
            } else {
                break;          // Early termination - no more palindromes possible
            }
        }

        return count;
    }

    /**
     * VISUAL DEMONSTRATION METHOD
     * Shows step-by-step how the algorithm works
     */
    public void demonstrateAlgorithm(String s) {
        System.out.println("=".repeat(80));
        System.out.println("DEMONSTRATING EXPAND AROUND CENTERS FOR: \"" + s + "\"");
        System.out.println("=".repeat(80));

        // Show string with indices
        System.out.print("Indices: ");
        for (int i = 0; i < s.length(); i++) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println();

        System.out.print("String:  ");
        for (char c : s.toCharArray()) {
            System.out.print(" " + c + " ");
        }
        System.out.println("\n");

        int totalCount = 0;

        // Process each center
        for (int i = 0; i < s.length(); i++) {
            System.out.println("Processing center at index " + i + ":");

            // Odd length palindromes
            System.out.println("  Odd length (center at '" + s.charAt(i) + "'):");
            int oddCount = visualExpandAroundCenter(s, i, i, "    ");
            totalCount += oddCount;

            // Even length palindromes
            if (i < s.length() - 1) {
                System.out.println("  Even length (center between '" + s.charAt(i) + "' and '" + s.charAt(i+1) + "'):");
                int evenCount = visualExpandAroundCenter(s, i, i + 1, "    ");
                totalCount += evenCount;
            }

            System.out.println();
        }

        System.out.println("TOTAL PALINDROMIC SUBSTRINGS: " + totalCount);
        System.out.println("=".repeat(80));
    }

    /**
     * Visual version that shows each step of expansion
     */
    private int visualExpandAroundCenter(String s, int left, int right, String indent) {
        int count = 0;
        int originalLeft = left, originalRight = right;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            String palindrome = s.substring(left, right + 1);
            count++;

            // Visual representation
            System.out.print(indent + "Step " + count + ": ");

            // Show the full string with highlighting
            for (int i = 0; i < s.length(); i++) {
                if (i == left || i == right) {
                    System.out.print("[" + s.charAt(i) + "]");
                } else if (i > left && i < right) {
                    System.out.print(" " + s.charAt(i) + " ");
                } else {
                    System.out.print(" " + s.charAt(i) + " ");
                }
            }

            System.out.println(" -> Found: \"" + palindrome + "\"");

            // Expand for next iteration
            left--;
            right++;
        }

        if (count == 0) {
            // Show why no palindrome was found
            if (originalLeft < s.length() && originalRight < s.length()) {
                System.out.println(indent + "No palindrome: '" +
                        (originalLeft >= 0 ? s.charAt(originalLeft) : "?") + "' != '" +
                        (originalRight < s.length() ? s.charAt(originalRight) : "?") + "'");
            }
        }

        return count;
    }

    /**
     * COMPLEXITY ANALYSIS DEMONSTRATION
     */
    public void analyzeComplexity(String s) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS FOR: \"" + s + "\"");
        System.out.println("=".repeat(60));

        int n = s.length();
        int totalCenters = 2 * n - 1; // n odd centers + (n-1) even centers

        System.out.println("String length (n): " + n);
        System.out.println("Total possible centers: " + totalCenters);
        System.out.println("  - Odd centers: " + n + " (at each character)");
        System.out.println("  - Even centers: " + (n-1) + " (between characters)");

        // Analyze worst case
        System.out.println("\nWorst case analysis:");
        System.out.println("  - Each center could potentially expand to entire string");
        System.out.println("  - Maximum expansions per center: " + n);
        System.out.println("  - Total operations: " + totalCenters + " × " + n + " = O(n²)");

        // Analyze actual performance for this string
        int totalExpansions = 0;
        for (int i = 0; i < s.length(); i++) {
            totalExpansions += countExpansions(s, i, i);
            if (i < s.length() - 1) {
                totalExpansions += countExpansions(s, i, i + 1);
            }
        }

        System.out.println("\nActual performance for this string:");
        System.out.println("  - Total character comparisons: " + totalExpansions);
        System.out.println("  - Average expansions per center: " +
                String.format("%.2f", (double)totalExpansions / totalCenters));
    }

    private int countExpansions(String s, int left, int right) {
        int expansions = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            expansions++;
            left--;
            right++;
        }
        return expansions;
    }

    // Test method
    public static void main(String[] args) {
        ExpandAroundCentersDetailed demo = new ExpandAroundCentersDetailed();

        String[] testCases = {"aba", "abba", "aaa", "abc", "racecar"};

        for (String testCase : testCases) {
            demo.demonstrateAlgorithm(testCase);
            demo.analyzeComplexity(testCase);
            System.out.println("\n" + "#".repeat(100) + "\n");
        }

        // Show the difference between approaches
        System.out.println("COMPARISON: Standard vs Optimized");
        System.out.println("=".repeat(40));
        String test = "abcba";
        System.out.println("Test string: \"" + test + "\"");

        int result1 = demo.countSubstringsExpandCenters(test);
        System.out.println("\nStandard approach result: " + result1);

        int result2 = demo.countSubstringsOptimized(test);
        System.out.println("Optimized approach result: " + result2);

        System.out.println("\nKey differences:");
        System.out.println("1. Optimized version has early termination");
        System.out.println("2. Fewer method calls and cleaner code");
        System.out.println("3. Same time complexity but better practical performance");
    }
}
