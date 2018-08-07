import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        // same idea as III, there are k*2 states
        // sold[i]: max money one can have with up to i times sell transaction
        // bought[i]: max money one can have with up to i times buy transaction
        int[] sold = new int[k+1]; // k+1, because we need to use sold[kk-1]
        int[] bought = new int[k+1];
        Arrays.fill(bought, Integer.MIN_VALUE);

        if(k >= prices.length/2) {
            return buyAndSellUnlimitedTimes(prices);
        }

        for(int p : prices) {
            for(int kk = k; kk >= 1; kk--) {
                sold[kk] = Math.max(sold[kk], bought[kk] + p);
                bought[kk] = Math.max(bought[kk], sold[kk-1] - p);
            }
        }
        return sold[k];
    }

    int buyAndSellUnlimitedTimes(int[] prices) {
        int maxP = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) maxP += prices[i] - prices[i-1];
        }
        return maxP;
    }

    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3};
        BestTimeToBuyAndSellStockIV sol = new BestTimeToBuyAndSellStockIV();
        System.out.println(sol.maxProfit(2, prices));
    }
}
