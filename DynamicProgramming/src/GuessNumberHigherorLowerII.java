/**
 * Created by yeqing on 6/11/2017.
 */
public class GuessNumberHigherorLowerII {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1]; //dp[j][i], solution for sub-problem [j, i], inclusive
        for (int hi = 2; hi <= n; hi++) { // when hi = 1, lo must be 1, dp[1][1] is 0, which is the initial value, so start from 2
            for (int lo = hi-1; lo > 0; lo--) { // when lo == hi, dp[lo][hi] is 0, therefore start from hi-1
                int minCost = Integer.MAX_VALUE;
                for (int k = hi; k >= lo; k--) {
                    int cost;
                    if (k == hi) cost = k + dp[lo][hi-1]; // hi start from 2, so hi-1 is safe
                    else if (k > lo) cost = k + Math.max(dp[lo][k-1], dp[k+1][hi]);
                    else cost = k + dp[lo+1][hi]; // lo start from hi-1, so lo+1 is safe
                    minCost = Math.min(minCost, cost);

                }
                dp[lo][hi] = minCost;
            }
        }
        return dp[1][n];
    }

    private int getMoneyAmount(int lo, int hi, int[][] dp) {
        if (lo >= hi) return 0;
        return dp[lo][hi];
    }

    public static void main(String[] args) {
        GuessNumberHigherorLowerII sol = new GuessNumberHigherorLowerII();
        System.out.println(sol.getMoneyAmount(5));
    }
}
