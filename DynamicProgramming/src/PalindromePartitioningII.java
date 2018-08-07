/**
 * Created by yeqing on 8/16/2017.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 */
public class PalindromePartitioningII {
    // v1: extend from current position to both backward and forward to check for palindrome
    public int minCut(String s) {
        // dp[i]: minimum cuts needed for a palindrome partitioning of substring [0,i)
        int[] dp = new int[s.length()+1];
        for (int i = 0; i <= s.length(); i++) { dp[i] = i-1; }
        for (int i = 0; i < s.length(); i++) {
            int min = s.length();
            // palindrome extend from i as: ... i-1, (i), i+1, ...
            for (int j = 0; i-j >= 0 && i+j < s.length() && s.charAt(i-j)==s.charAt(i+j); j++) {
                dp[i+j+1] = Math.min(dp[i+j+1], dp[i-j] + 1);
            }
            // palindrome extend from i, i+1 as: ... i | i+1, ...
            for (int j = 0; i-j>=0 && i+1+j <s.length() && s.charAt(i-j)==s.charAt(i+1+j); j++) {
                dp[i+1+j+1] = Math.min(dp[i+j+2], 1+dp[i-j]);
            }
        }
        printArray(dp);
        return dp[s.length()];
    }
    // v2: extend from current position to backward only to search for palindrome
    public int minCut2(String s) {
        int[] dp = new int[s.length()+1];
        boolean[][] pal = new boolean[s.length()][s.length()]; // pal[i][j]: whether substring [i, j] is a palindrome
        for (int i = 0; i <= s.length(); i++) dp[i] = i-1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i-j)==s.charAt(i) && (i-j+1 > i-1 || pal[i-j+1][i-1])) {
                    dp[i+1] = Math.min(dp[i+1], 1+dp[i-j]);
                    pal[i-j][i] = true;
                }
            }
        }
        return dp[s.length()];
    }
    void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + "  ");
        System.out.println();
    }

    public static void main(String[] args) {
        String s = "aab";
        PalindromePartitioningII sol = new PalindromePartitioningII();
        System.out.println(sol.minCut2(s));
    }
}
