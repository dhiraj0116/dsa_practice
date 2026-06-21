package random;

/*
Problem: Given an array of stock prices, return the maximum profit from one buy and one sell.
Input: 7
7 1 5 3 6 4
Output: 5
 */

public class BuySellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        BuySellStock  bs = new BuySellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7,6,4,3,1};
        System.out.println(bs.maxProfit(prices));
    }
}

