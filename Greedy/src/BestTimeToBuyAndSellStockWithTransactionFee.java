/**
 * Created by yeqing on 11/17/2017.
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int low = Integer.MAX_VALUE, high = 0;
        int profit = 0;
        for (int n : prices) {
            if (high - n > fee) {// sell stock
                profit += high-low > fee ? high - low - fee : 0;
                high = n;
                low = n;
            } else {
                high = Math.max(high, n);
                low = Math.min(low, n);
            }
        }
        profit += high-low > fee ? high - low - fee : 0;
        return profit;
    }
}
