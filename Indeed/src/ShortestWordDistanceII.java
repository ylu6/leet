import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {

    Map<String, List<Integer>> map; // store {word : list of indices of this word in original array}

    public ShortestWordDistanceII(String[] words) {
        map = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }

    int shorest(String word1, String word2){
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int i = 0, j = 0, res = Integer.MAX_VALUE;
        while(i <  list1.size() && j < list2.size()) {
            int idx1 = list1.get(i), idx2 = list2.get(j);
            res = Math.min(res, Math.abs(idx2-idx1));
            if(idx1 < idx2) i++;
            else j++;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes", "perfect", "perfect"};
        ShortestWordDistanceII sol = new ShortestWordDistanceII(words);
        System.out.println(sol.shorest("coding", "practice"));
        System.out.println(sol.shorest("makes", "coding"));
        System.out.println(sol.shorest("practice", "perfect"));

    }
}
/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */