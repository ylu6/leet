/**
 * Created by yeqing on 7/28/2017.
 */
public class DeleteOperationforTwoStrings {
    // version 1, find LCS first, return len1 - LCS + len2 - LCS
    public int minDistance(String word1, String word2) {
        int LCS = longestCommonSubsequency(word1, word2);
        return word1.length() - LCS + word2.length() - LCS;
    }
    int longestCommonSubsequency(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i==0 || j==0) dp[i][j] = 0;
                else if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[word1.length()][word2.length()];
    }

    // version 2: direct approach
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // dp[i][j]: min delete cost for word1.substring(0,i) and word2.substring(0,j)
        int[][] dp = new int[len1+1][len2+1];

        for(int j = 1; j <= len2; j++)
            dp[0][j] = dp[0][j-1] + 1;

        for(int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i-1][0] + 1;
            for(int j = 1; j <= len2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i-1][j-1], 1 + Math.min(dp[i-1][j], dp[i][j-1]));
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "sea", word2 = "eat";
        DeleteOperationforTwoStrings sol = new DeleteOperationforTwoStrings();
        System.out.println(sol.minDistance(word1, word2));
        System.out.println(sol.minDistance2(word1, word2));
    }
}
