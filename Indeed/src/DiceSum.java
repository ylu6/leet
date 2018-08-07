import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DiceSum {
    double getPossibility(int dice, int target) {
        int total = (int) Math.pow(6, dice);

        int[][] dp = new int[dice+1][target+1];
        dp[0][0] = 1; // dp[i][j]: number of permutations to reach sum j with i dices

        for(int i = 1; i <= dice; i++) { // loop number of dices from 1 to dice
            dp[i][0] = 0; // for i dices, number of permutations to reach 0 is 0
            for(int j = 1; j <= target; j++) { // loop sum from 1 to target value
                for(int val = 1; j-val >= 0 && val <= 6; val++) { // loop dice value from 1 to 6
                    dp[i][j] += dp[i-1][j-val];
                }
            }
        }
        System.out.println(dp[dice][target]);
        return 1.0*dp[dice][target]/total;
    }

    int dfs(int dice, int target) {
        if(dice == 0) {
            return target == 0 ? 1 : 0;
        }
        int count = 0;
        for(int i = 1; i <= 6; i++) {
            count += dfs(dice-1, target - i);
        }
        return count;
    }

    public static void main(String[] args) {
        DiceSum sol = new DiceSum();
        System.out.println(sol.getPossibility(2, 9));
        System.out.println(sol.dfs(2, 9));
    }
}
