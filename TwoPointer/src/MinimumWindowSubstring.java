import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 8/3/2017.
 */
public class MinimumWindowSubstring {
    // HashMap version
    public String minWindow(String s, String t) {
        Map<Character, Integer> mapT = new HashMap<>();
        // initialize the map
        for (int i = 0; i < t.length(); i++)
            mapT.put(t.charAt(i), 1+mapT.getOrDefault(t.charAt(i), 0));

        int winSize = Integer.MAX_VALUE;
        String res = "";
        Map<Character, Integer> mapS = new HashMap<>();
        for (int slow = 0, fast = 0; fast < s.length(); fast++) {
            char c = s.charAt(fast);
            mapS.put(c, 1+mapS.getOrDefault(c, 0));

            while (contains(mapS, mapT)) {
                if (fast-slow+1 < winSize) {
                    winSize = fast-slow+1;
                    res = s.substring(slow, fast+1);
                }
                mapS.put(s.charAt(slow), mapS.get(s.charAt(slow))-1);
                slow++;
            }
        }
        return res;
    }

    // check if map1 contains map2
    boolean contains(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (Character c : map2.keySet()) {
            if (map1.getOrDefault(c, 0) < map2.get(c)) return false;
        }
        return true;
    }

    // int array version
    public String minWindow2(String s, String t) {
        int[] mapT = new int[128];
        // initialize the map
        for (int i = 0; i < t.length(); i++)
            mapT[t.charAt(i)]++;

        int winSize = Integer.MAX_VALUE;
        String res = "";
        int[] mapS = new int[128];
        for (int slow = 0, fast = 0; fast < s.length(); fast++) {
            mapS[s.charAt(fast)]++;

            while (contains(mapS, mapT)) {
                if (fast-slow+1 < winSize) {
                    winSize = fast-slow+1;
                    res = s.substring(slow, fast+1);
                }
                mapS[s.charAt(slow)]--;
                slow++;
            }
        }
        return res;
    }

    // check if map1 contains map2
    boolean contains(int[] map1, int[] map2) {
        for (int i = 0; i < map1.length; i++)
            if (map2[i] > map1[i]) return false;
        return true;
    }
    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minWindow2(S, T));
    }
}
