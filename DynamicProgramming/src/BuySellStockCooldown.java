/**
 * Created by yeqing on 6/19/2017.
 */
public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // three states, s0: no stock, can buy, s1, have stock, s2, cooldown (no stock)
        // initial condition after first day
        int s0 = 0, s1 = -prices[0], s2 = Integer.MIN_VALUE;
        int s0_prv, s1_prv, s2_prv;
        for (int i = 1; i < prices.length; i++) {
            s0_prv = s0;
            s1_prv = s1;
            s2_prv = s2;
            s0 = Math.max(s0_prv, s2_prv);
            s1 = Math.max(s1_prv, s0_prv - prices[i]);
            s2 = s1_prv + prices[i];
        }
        return Math.max(s0, s2);
    }
}
