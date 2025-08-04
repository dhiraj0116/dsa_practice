package easy;

/**
 Given a binary array nums, return the maximum number of consecutive 1's in the array.
 Example 1:
 Input: nums = [1,1,0,1,1,1]
 Output: 3

 Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

 Example 2:
 Input: nums = [1,0,1,1,0,1]
 Output: 2

 Constraints:
 1 <= nums.length <= 105
 nums[i] is either 0 or 1.
 */
public class Max_Consecutive_Ones {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int currentSum = 0;

        for (int num : nums) {
            if (num == 0) {
                currentSum = 0;
            } else {
                currentSum++;
//                max = Math.max(max, currentSum);
            }

            if (currentSum > max) {
                max = currentSum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Max_Consecutive_Ones maxConsecutiveOnes = new Max_Consecutive_Ones();
        int[] nums = {1,1,1,0,1};
        System.out.println(maxConsecutiveOnes.findMaxConsecutiveOnes(nums));
    }
}
