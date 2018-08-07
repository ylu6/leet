public class ShortestWordDistanceIII {
    int shortestDistance(String[] words, String word1, String word2) {
        int idx = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(idx != -1) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistanceIII sol = new ShortestWordDistanceIII();
        System.out.println(sol.shortestDistance(words, "makes", "coding"));
        System.out.println(sol.shortestDistance(words, "makes", "makes"));
    }
}

/*
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
 */