/**
 * Created by yeqing on 8/8/2017.
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        // b1/b2: max money one can have after up to 1/2 times stock buy
        // s1/s2: max money one can have after up to 1/2 times stock sell
        int b1 = Integer.MIN_VALUE, s1 = 0, b2 = Integer.MIN_VALUE, s2 = 0;
        for(int p : prices) {
            s2 = Math.max(s2, b2+p);
            b2 = Math.max(b2, s1-p);
            s1 = Math.max(s1, b1+p);
            b1 = Math.max(b1, -p);
        }
        return s2;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII sol = new BestTimeToBuyAndSellStockIII();
        int[] prices = {5,4,3,2,1};
        System.out.println(sol.maxProfit(prices));
    }
}
