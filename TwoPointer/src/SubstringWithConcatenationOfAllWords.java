import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeqing on 8/3/2017.
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int len = words[0].length(); // length of word, words are all with same length
        int totalLen = len*words.length;
        for (String word : words) // initialize word map
            map.put(word, 1+map.getOrDefault(word, 0));

        for (int slow = 0; slow <= s.length() - totalLen; slow++) {
            Map<String, Integer> copy = new HashMap<>(map);
            for (int fast = slow; fast < slow+totalLen; fast+=len) {
                String sub = s.substring(fast, fast+len);
                if (copy.getOrDefault(sub, 0) == 0) break;
                copy.put(sub, copy.get(sub)-1);
                if (fast - slow == totalLen-len) res.add(slow);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        SubstringWithConcatenationOfAllWords sol = new SubstringWithConcatenationOfAllWords();
        System.out.println(sol.findSubstring(s, words).toString());
    }
}
