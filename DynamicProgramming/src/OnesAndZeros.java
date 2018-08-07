/**
 * Created by yeqing on 6/6/2017.
 */
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1]; // m zeros and n ones
        for (int i = 1; i <= strs.length; i++) {
            int zeros = 0, ones = 0; // counts zeros and ones in string strs[i-1]
            for (int idx = 0; idx < strs[i-1].length(); idx++) {
                if (strs[i-1].charAt(idx) == '0') zeros++;
                else ones++;
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeros && k >= ones)
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-zeros][k-ones]+1);
                    else
                        dp[i][j][k] = dp[i-1][j][k];
                }
            }
        }
        return dp[strs.length][m][n];
    }

    // version two, use 2D dp array, and update a smaller part of the 2D matrix every loop
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1]; // m zeros and n ones
        for (String str : strs) {
            int zeros = 0, ones = 0; // counts zeros and ones in string strs[i-1]
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == '0') zeros++;
                else ones++;
            }
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeros][k-ones]+1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
//        String[] strs = {"10", "0001", "111001", "1", "0"};
        String[] strs = {"10", "0", "1"};
        OnesAndZeros sol = new OnesAndZeros();
        System.out.println(sol.findMaxForm2(strs, 1, 1));
    }
}
