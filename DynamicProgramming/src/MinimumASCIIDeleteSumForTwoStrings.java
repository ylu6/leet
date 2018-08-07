/**
 * Created by yeqing on 11/3/2017.
 */
public class MinimumASCIIDeleteSumForTwoStrings {
    // version 1: find the total ASCII sum of s1 and s2 first, then find the ASCII maxCommonSum, then got the minimum delete sum
    public int minimumDeleteSum(String s1, String s2) {
        int sum = 0;
        for (int i = 0; i < s1.length(); i++)   sum += s1.charAt(i);
        for (int i = 0; i < s2.length(); i++)   sum += s2.charAt(i);
        return sum - maxCommonSum(s1, s2)*2; // multiply by 2, there are two chars for each common occurrence, one from s1, one from s2
    }
    // find the common subsequence of s1 and s2, which has the maximum ASCII sum
    int maxCommonSum(String s1, String s2) {
        // dp[i][j]: the max sum of the common subsequence of s1 and s2, untill index i-1 of s1, j-1 of s2
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        // dp process
        for (int i = 1; i <= s1.length(); i++) { // loop over s1
            char c1 = s1.charAt(i-1);
            for (int j = 1; j <= s2.length(); j++) { // loop over s2
                char c2 = s2.charAt(j-1);
                // transition function
                if (c1==c2) { // if current two char is the same
                    dp[i][j] = Math.max(dp[i-1][j-1] + c1, Math.max(dp[i-1][j], dp[i][j-1]));
                } else { // if current two char is not the same
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    // version 2: direct approach
    public int minimumDeleteSum2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();

        // dp[i][j]: min ASCII delete cost for s1.substring(0, i) and s2.substring(0, j)
        int[][] dp = new int[len1+1][len2+1];

        for(int j = 1; j <= len2; j++){
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }

        for(int i = 1; i <= len1; i++){
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
            for(int j =1; j <= len2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(s1.charAt(i-1) + dp[i-1][j], s2.charAt(j-1) + dp[i][j-1]));
                } else {
                    dp[i][j] = Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
                }
            }
        }

        return dp[len1][len2];
    }
    public static void main(String[] args) {
        String s1 = "delete", s2 = "leet";
        String s3 = "sea", s4 = "eat";
        MinimumASCIIDeleteSumForTwoStrings sol = new MinimumASCIIDeleteSumForTwoStrings();
        System.out.println(sol.minimumDeleteSum(s1, s2));
        System.out.println(sol.minimumDeleteSum(s3, s4));

    }
}
