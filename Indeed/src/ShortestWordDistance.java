public class ShortestWordDistance {
    // ver 1: use only one idx variable
    int shortestDistance(String[] words, String word1, String word2) {
        int idx = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(idx != -1 && !words[idx].equals(words[i])) res = Math.min(res, i-idx);
                idx = i;
            }
        }
        return res;
    }

    // ver 2, use two idx variables to keep track of word1 and word2 respectively
    int shortestDistance2(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -2, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(word1.equals(words[i])) idx1 = i;
            if(word2.equals(words[i])) idx2 = i;
            if(idx1 != -1 && idx2 != -2) res = Math.min(res, Math.abs(idx1-idx2));
        }
        return res;
    }
    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes", "perfect", "perfect"};
        ShortestWordDistance sol = new ShortestWordDistance();
        System.out.println(sol.shortestDistance(words, "coding", "practice"));
        System.out.println(sol.shortestDistance(words, "makes", "coding"));
        System.out.println(sol.shortestDistance(words, "practice", "perfect"));
        System.out.println(sol.shortestDistance2(words, "coding", "practice"));
        System.out.println(sol.shortestDistance2(words, "makes", "coding"));
        System.out.println(sol.shortestDistance2(words, "practice", "perfect"));
    }
}

/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
