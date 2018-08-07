import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 8/11/2017.
 */
public class CoinChange {
    // v1: top down memoization method
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        Map<Integer, Integer> cache = new HashMap<>();
        return fewestCoin(coins, amount, cache);
    }
    int fewestCoin(int[] coins, int amount, Map<Integer, Integer> cache) {
        if (cache.containsKey(amount)) return cache.get(amount);
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int nxt = fewestCoin(coins, amount-coin, cache);
            if (nxt >= 0 && 1+nxt < res)
                res = 1+nxt;
        }
        res = (res == Integer.MAX_VALUE) ? -1 : res;
        cache.put(amount, res);
        return res;
    }

    // v2: bottom up, dp method
    // draw the 2D table to visulize the process:
    // row: coins, col: number_of_coins (0, 1, 2.... amout)
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, 1+amount);
        dp[0] = 0; // amount 0 needs zero coin

        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for(int j = 0; j+coin <= amount; j++) {
                dp[j+coin] = Math.min(dp[j+coin], dp[j]+1);
            }
        }
        return dp[amount] == 1+amount ? -1 : dp[amount]; // unreachable if it is 1+amount, return -1 as requested
    }
}
