/**
 * Created by yeqing on 8/4/2017.
 */


public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        //dp[i][j]: whether substring s[0:j), p[0,i) matches

        dp[0][0] = true; // match is both s and p are empty
        // if pattern is empty but string is not, must be false

        for(int i = 1; i <= p.length(); i++) {
            char pCh = p.charAt(i-1);
            // pattern is not empty, but string is;
            dp[i][0] =  pCh == '*' ? dp[i-2][0] : false;
            for(int j = 1; j <= s.length(); j++) {
                char sCh = s.charAt(j-1);
                if(sCh == pCh || pCh == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                // for a valid pattern, there must be at least one char in front of '*', therefore we can safely char p.charAt(i-2)
                if(pCh == '*') {
                    if(p.charAt(i-2) == '.' || p.charAt(i-2) == sCh) { // previous char in pattern can match sCh
                        // the effect of a '*'
                        // 0 time: remove the previous char, check dp[i-2][j]
                        // 1 time: equivalent to no star at all, check dp[i-1][j]
                        // > 1 times: match the char in string and pattern keep the same: dp[i][j-1]
                        dp[i][j] = dp[i-2][j] || dp[i-1][j] || dp[i][j-1];
                    } else { // previous char in pattern cannot match sCh, use '*' to remove previous char in pattern
                        dp[i][j] = dp[i-2][j];
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }
    public static void main(String[] args) {
        String s = "aaabbb", p = "a*b.*";
        RegularExpressionMatching sol = new RegularExpressionMatching();
        System.out.println(sol.isMatch(s, p));
    }
}
