package pattern.arrays_strings.manipulation;

import java.util.Arrays;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra
space for space complexity analysis.)
*/
public class Product_of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int[] result = new int[n];
        left[0] = 1;
        right[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= right;
            right *= nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        Product_of_Array_Except_Self obj = new Product_of_Array_Except_Self();
        System.out.println(Arrays.toString(obj.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(obj.productExceptSelf2(new int[]{1, 2, 3, 4})));
    }
}
