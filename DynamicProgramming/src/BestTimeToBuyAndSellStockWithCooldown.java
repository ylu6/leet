public class BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        // there states: max money one can have in each states
        // s1: no stock in hand and can buy
        // s2: have stock in hand, and can sell
        // s3: no stock in hand and cannot buy (cooldown)
        int s1, s2, s3 = 0;
        int prv1 = 0, prv2 = Integer.MIN_VALUE, prv3 = 0;

        for(int p : prices) {
            s1 = Math.max(prv1, prv3); // s3 -> s1 after cooldown
            s2 = Math.max(prv2, prv1 - p); // s1 -> s2 if buy stock
            s3 = Math.max(prv3, prv2 + p); // s2 -> s3 if sell stock
            prv1 = s1;
            prv2 = s2;
            prv3 = s3;
        }
        return s3;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        BestTimeToBuyAndSellStockWithCooldown sol = new BestTimeToBuyAndSellStockWithCooldown();
        System.out.println(sol.maxProfit(prices));
    }
}
