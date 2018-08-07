/**
 * Created by yeqing on 8/7/2017.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i <= s.length(); i++)
            dp[i][0] = 1; // t is empty, s not, delete all char in s
                        // if s is empty, t not, then it is not possible, so 0
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i-1) != t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        String S = "rabbbit", T = "rabbit";
        DistinctSubsequences sol = new DistinctSubsequences();
        System.out.println(sol.numDistinct(S, T));
    }
}
