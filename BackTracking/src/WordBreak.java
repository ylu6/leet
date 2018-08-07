import java.util.*;

/**
 * Created by yeqing on 6/30/2017.
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        Set<String> dict = new HashSet<String>();
        for (String word : wordDict) dict.add(word);

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i-1; j >= 0; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<String>(Arrays.asList("leet", "code"));
        WordBreak sol = new WordBreak();
        System.out.println(sol.wordBreak(s, wordDict));
    }
}
