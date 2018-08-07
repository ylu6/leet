/**
 * Created by yeqing on 8/16/2017.
 * Given a string, your task is to count how many palindromic substrings in this string.
 */
public class PalindromicSubstrings {
    // v1: use dp, dp[i][j] stores whether substring [j,i] is palindromic
    public int countSubstrings(String s) {
        int res = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            res++;
            for (int j = i-1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (i-1 < j+1 || dp[i-1][j+1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
    // v2: extend palindrome from mid, totally 2n-1 possible centers for extending
    public int countSubstrings2(String s) {
        int res = 0, lo = 0, hi = 0;
        for (int i = 0; i < 2*s.length()-1; i++) {
            if (i%2==0) { lo = i/2; hi = i/2; }
            else        { lo = i/2; hi = i/2+1; }

            while (lo >= 0 && hi < s.length() && s.charAt(lo)==s.charAt(hi)) {
                res++;
                lo--; hi++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aba";
        PalindromicSubstrings sol = new PalindromicSubstrings();
        System.out.println(sol.countSubstrings2(s));
    }
}
