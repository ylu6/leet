public class CoinChange2 {
    public int change(int amount, int[] coins) {
        // dp[i]: number of ways to make up amount i
        int[] dp = new int[amount+1];
        dp[0] = 1; // there is 1 way to make up amount 0, which is no coin

        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j+coins[i] <= amount; j++) {
                dp[j+coins[i]] += dp[j];
            }
        }
        return dp[amount];
    }
}
