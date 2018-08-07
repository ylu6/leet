/**
 * Created by yeqing on 8/4/2017.
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true; // empty string matches another empty string
        // when p is empty and s is not empty, no match, dp[i][0] is false for i!=0
        // when s is empty and p is not, dp[0][j] is true only when p.charAt(j-1)==* and dp[0][j-1] is true
        for (int j = 1; j <= p.length(); j++)
            if (p.charAt(j-1)=='*' && dp[0][j-1])   dp[0][j] = true;

        // the algorithm can be optimized to use O(1) memory
        // because dp[i][j] only depends on dp[i-1][j] and dp[i][j-1]
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if (p.charAt(j-1) == '*'){
                    // when thinking about this, dp[i][j] indeed depends on dp[i-1][j], dp[i][j-1] and dp[i-1][j-1]
                    // since p[j-1]=="*", dp[i-1][j-1] is included in dp dp[i-1][j] (star as empty char sequence)
                    if (dp[i-1][j] || dp[i][j-1]) dp[i][j] = true;
                }
                //otherwise dp[i][j] is false
            }
        }
        return dp[s.length()][p.length()];
    }
}
