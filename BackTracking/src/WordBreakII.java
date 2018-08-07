import java.util.*;

/**
 * Created by yeqing on 6/2/2017.
 */
public class WordBreakII {
    Map<String, List<String>> cache = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for(String str : wordDict) dict.add(str);

        return dfs(s, dict);

    }

    List<String> dfs(String s, Set<String> dict) {
        List<String> res = new ArrayList<>();
        if(cache.containsKey(s)) return cache.get(s);

        if(s.length() == 0) {
            res.add("");
            return res;
        }

        for(int len = 1; len <= s.length(); len++) {
            String substr = s.substring(0, len);
            if(dict.contains(substr)) {
                for(String str : dfs(s.substring(len), dict)) {
                    String tmp = substr + (str.length() == 0 ? "" : " ") + str;
                    res.add(tmp);
                }
            }
        }
        cache.put(s, res);
        return res;
    }
// another version, no need to add "" when s isEmpty()
    List<String> dfs(String s, Set<String> set, Map<String, List<String>> map) {
        List<String> res = new ArrayList<>();
        if(s == null || s.isEmpty()) return res;

        if(map.containsKey(s)) return map.get(s);

        // if size or wordDict is small, may be better to loop through the wordSet instead of length?
        for(int i = 1; i <= s.length(); i++) {
            String substr = s.substring(0, i);
            if(set.contains(substr)) {
                for(String nxt : dfs(s.substring(i), set, map)) {
                    res.add(substr + " " + nxt);
                }
                if(i == s.length()) res.add(s);
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
//        System.out.println(String.join(",", arr));
        List<String> wordDict = Arrays.asList(arr);
        WordBreakII wb = new WordBreakII();
        System.out.println(wb.wordBreak(s, wordDict).toString());

        String s2 = "abs";
        System.out.println(s2.substring(4).length());
    }
}
