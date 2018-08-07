import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return backtrack(pattern, str, 0, 0, map, set);
    }

    boolean backtrack(String pattern, String str, int p1, int p2, Map<Character, String> map, Set<String> set) {
        if(p1 == pattern.length() && p2 == str.length()) return true; // depleted at same time, find matching
        if(p1 == pattern.length() || p2 == str.length()) return false; // only one depleted, failed to match

        char c = pattern.charAt(p1);
        int nextP2;
        String substr;
        if(map.containsKey(c)) {
            substr = map.get(c);
            nextP2 = p2 + substr.length();
            if(nextP2 > str.length() || !substr.equals(str.substring(p2, nextP2))) return false;
            return backtrack(pattern, str, p1+1, nextP2, map, set);
        } else {
            for(nextP2 = p2+1; nextP2 <= str.length(); nextP2++) { // check substr [p2, nextP2)
                substr = str.substring(p2, nextP2);
                if(set.contains(substr)) continue; // bijection impossible
                map.put(c, substr);
                set.add(substr);
                if(backtrack(pattern, str, p1+1, nextP2, map, set)) // find match, return true
                    return true;
                map.remove(c); // doesn't match, backtrack
                set.remove(substr);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String pattern = "dd";
        String str = "efea";
        WordPatternII sol = new WordPatternII();
        System.out.println(sol.wordPatternMatch(pattern, str));
    }
}
