package easy;

/*
Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.
Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:
1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
*/
public class Maximum_Product_Subarray {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int maxProduct = nums[0];
        int minProduct = nums[0];

        int maxProfit = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if(nums[i] >= 0) {
                maxProduct = Math.max(nums[i], maxProduct * nums[i]);
                minProduct = Math.min(nums[i], minProduct * nums[i]);
            } else {
                int temp = maxProduct;
                maxProduct = Math.max(nums[i], minProduct *  nums[i]);
                minProduct = Math.min(nums[i], temp * nums[i]);
            }
            maxProfit = Math.max(maxProfit, maxProduct);
        }
        return maxProfit;
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int l=1,r=1;
        int ans=nums[0];
        for(int i=0;i<n;i++){

            //if any of l or r become 0 then update it to 1
            l = l==0 ? 1 : l;
            r = r==0 ? 1 : r;

            l *= nums[i];   //prefix product
            r *= nums[n-1-i];    //suffix product

            ans = Math.max(ans,Math.max(l,r));
        }
        return ans;
    }

    public static void main(String[] args) {
        Maximum_Product_Subarray maximum_product_subarray = new Maximum_Product_Subarray();
        System.out.println(maximum_product_subarray.maxProduct2(new int[]{2,3,-2,4})); //[2,3,-2,4]
    }

}
