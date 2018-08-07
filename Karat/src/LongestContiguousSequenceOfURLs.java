import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestContiguousSequenceOfURLs {
    // if there is more than one, return the 1st
    List<String> LCS(List<String> list1, List<String> list2) {
        int[][] dp = new int[list1.size()+1][list2.size()+1];
        int maxLen = 0;
        int[] maxCoord = new int[2];

        // find the the first LCS
        // dp[i][j]: length of the LCS ended at i-1 of list1 and j-1 of list2
        for(int i = 1; i <= list1.size(); i++) {
            for(int j = 1; j <= list2.size(); j++) {
                if(list1.get(i-1).equals(list2.get(j-1))) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        maxCoord = new int[]{i-1,j-1}; // Note, the end of the LCS is (i-1,j-1), not (i,j)
                    }
                }
            }
        }
        List<String> res = new LinkedList<>();
        while(maxLen-- > 0) {
            res.add(0, list1.get(maxCoord[0]));
            maxCoord[0]--;
        }
        return res;
    }

    // v2, use 1d dp array
    public List<String> LCS2(List<String> list1, List<String> list2) {
        int[] dp = new int[list2.size()+1];
        int maxLen = 0;
        int[] maxCoord = new int[2];

        for(int i = 1; i <= list1.size(); i++) {
            for(int j = list2.size(); j >= 1; j--) {
                if(list1.get(i-1).equals(list2.get(j-1))) {
                    dp[j] = 1 + dp[j-1];
                    if(dp[j] > maxLen) {
                        maxLen = dp[j];
                        maxCoord = new int[]{i-1, j-1};
                    }
                }
            }
        }
        LinkedList<String> res = new LinkedList<>();
        while(maxLen-- > 0) {
            res.addFirst(list1.get(maxCoord[0]));
            maxCoord[0]--;
        }
        return res;
    }
    public static void main(String[] args) {
        List<String> user0 = Arrays.asList("/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html");
        List<String> user1 = Arrays.asList("/one.html", "/two.html", "/three.html", "/four.html", "/six.html" );
        List<String> user2 = Arrays.asList("/nine.html", "/two.html", "/three.html", "/four.html", "/six.html", "/seven.html");
        List<String> user3 = Arrays.asList("/three.html", "/eight.html");
        LongestContiguousSequenceOfURLs sol = new LongestContiguousSequenceOfURLs();
        System.out.println(sol.LCS(user0, user2));
        System.out.println(sol.LCS(user1, user2));
        System.out.println(sol.LCS(user0, user3));
        System.out.println(sol.LCS(user1, user3));
        System.out.println(sol.LCS2(user0, user2));
        System.out.println(sol.LCS2(user1, user2));
        System.out.println(sol.LCS2(user0, user3));
        System.out.println(sol.LCS2(user1, user3));
    }
}

/*
Q2:
We have some clickstream data that we gathered on our client's website. Using cookies, we collected snippets of users' anonymized URL histories while they browsed the site. The histories are in chronological order and no URL was visited more than once per person.

Write a function that takes two user’s browsing histories as input and returns the longest contiguous sequence of URLs that appears in both.

user0 = [ "/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html" ]
user1 = [ "/one.html", "/two.html", "/three.html", "/four.html", "/six.html" ]
user2 = [ "/nine.html", "/two.html", "/three.html", "/four.html", "/six.html", "/seven.html" ]
user3 = [ "/three.html", "/eight.html" ]

Sample output:

(user0, user2):
   /four.html
   /six.html
   /seven.html

(user1, user2):
   /two.html
   /three.html
   /four.html
   /six.html
(user0, user3):
   None

(user1, user3):
   /three.html
 */
