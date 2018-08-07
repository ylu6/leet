import java.util.Arrays;

// Uber phone interview question
// given a dict which is a list of words
// given a array of char
// what is the longest word in dict which can be formed from those chars
public class LongestWordFromDict {
    String longestWord(String[] dict, char[] chars) {
        Arrays.sort(dict, (a,b)->Integer.compare(b.length(), a.length()));
        int[] map = new int[26];
        for(char c : chars) {
            map[c-'a']++;
        }
        for(String word : dict) {
            if(isValid(map, word))
                return word;
        }
        return "";
    }
    boolean isValid(int[] map, String word) {
        int[] tmap = new int[26];
        for(char c : word.toCharArray()) {
            tmap[c-'a']++;
            if(tmap[c-'a'] > map[c-'a']) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] dict = {"b","abcda","abc","bb"};
        char[] chars = {'a', 'c', 'd', 'b', 'a'};
        LongestWordFromDict sol = new LongestWordFromDict();
        System.out.println(sol.longestWord(dict, chars));
    }
}
