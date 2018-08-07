/**
 *  Created by yeqing on 7/28/2017.
 *  find the minimum number of steps required to convert word1 to word2
 *  a) Insert a character
 *  b) Delete a character
 *  c) Replace a character
 */
public class EditDistance {
    // v1, not optimized, storage O(m*n)
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        if (l1 == 0 || l2 == 0) return Math.max(l1,l2);

        int[][] dp = new int[l1+1][l2+1];
        for (int i = 0; i <= l1; i++)
            dp[i][0] = i;
        for (int j = 0; j <= l2; j++)
            dp[0][j] = j;

        for (int i = 1; i <= l1; i++) {
            char c1 = word1.charAt(i-1);
            for (int j = 1; j <= l2; j++) {
                char c2 = word2.charAt(j-1);
                if (c1 == c2) dp[i][j] = dp[i-1][j-1];
                else // three cases: replace, delete, insert
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[l1][l2];
    }

    // optimized
    public int minDistance2(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        if (l1 == 0 || l2 == 0) return Math.max(l1,l2);

        int[] dp = new int[l1+1];
        for (int i = 1; i <= l1; i++)
            dp[i] = i;

        for (int j = 1; j <= l2; j++) {
            char c1 = word2.charAt(j-1);
            dp[0] = j;
            int prv = j-1;
            for (int i = 1; i <= l1; i++) {
                int tmp = dp[i];
                char c2 = word1.charAt(i-1);
                if (c1 == c2) dp[i] = prv;
                else // three cases: replace, delete, insert
                    dp[i] = 1 + Math.min(dp[i-1], Math.min(dp[i], prv));
                prv = tmp;
            }
        }
        return dp[l1];
    }
    public static void main(String[] args) {
        String word1 = "a" , word2 = "ab";
        EditDistance sol = new EditDistance();
        System.out.println(sol.minDistance2(word1, word2));
    }
}
