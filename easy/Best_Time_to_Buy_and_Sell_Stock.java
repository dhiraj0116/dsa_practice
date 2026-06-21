package easy;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

Constraints:
1 <= prices.length <= 105
0 <= prices[i] <= 104
*/

public class Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int ans = 0;

        for(int i=1; i<prices.length; i++){
            int profit = prices[i] - min;
            if(profit>ans){
                ans = profit;
            }

            min = Math.min(prices[i], min);
        }
        return ans;
    }
    public int maxProfit2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i:prices){
            min = Math.min(i, min); // i < min ? i:min
            int diff = prices[i] - min;
            if(diff>ans){
                ans = diff;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Best_Time_to_Buy_and_Sell_Stock obj = new Best_Time_to_Buy_and_Sell_Stock();
        System.out.println(obj.maxProfit(new int[]{1,2,3,4,5}));
    }
}
