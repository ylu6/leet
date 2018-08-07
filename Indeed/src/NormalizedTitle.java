import java.util.HashMap;
import java.util.Map;

/*
Question Description
=============================================================================
Given a rawTitle, and a list(or array) of clean titles. For each clean title,
        the raw title can get a "match point". For example, if raw title is "senior software
        engineer" and clean titles are "software engineer" and "mechanical engineer", the
        "match point" will be 2 and 1. In this case we return "software engineer" because
        it has higher "match point".
 */
public class NormalizedTitle {
    public String getHighestTitle(String rawTitle, String[] cleanTitles){
        int maxScore = 0;
        String res = null;
        for(String title : cleanTitles) {
            int score = getScore2(rawTitle, title);
            if(score > maxScore) {
                maxScore =score;
                res = title;
            }
        }
        return res;
    }
    // order doesn't matter, only count matches
    int getScore(String rawTitle, String cleanTitle) {
        Map<String, Integer> map = new HashMap<>();
        for(String s : rawTitle.split(" ")) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        int score = 0;
        for(String s : cleanTitle.split(" ")) {
            if(map.getOrDefault(s,0) > 0) {
                score++;
                map.put(s, map.get(s)-1);
            }
        }
        return score;
    }

    int getScore2(String rawTitle, String cleanTitle) {
        String[] rawArr = rawTitle.split(" ");
        String[] cleanArr = cleanTitle.split(" ");
        int[][] dp = new int[rawArr.length+1][cleanArr.length+1];
        for(int i = 1; i <= rawArr.length; i++) {
            for(int j = 1; j <= cleanArr.length; j++) {
                if(rawArr[i-1].equals(cleanArr[j-1])) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[rawArr.length][cleanArr.length];
    }

    public static void main(String[] args) {
        String rawTitle = "a b c d e f g";
        String[] cleanTitles = {"a b g f e", "b c a e f"};
        NormalizedTitle sol = new NormalizedTitle();
        System.out.println(sol.getHighestTitle(rawTitle, cleanTitles));
        System.out.println(sol.getScore2(rawTitle, cleanTitles[0]));
        System.out.println(sol.getScore2(rawTitle, cleanTitles[1]));

    }
}
