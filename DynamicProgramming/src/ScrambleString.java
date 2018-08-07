import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 8/7/2017.
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
//memorized recursive version
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        Map<String, Boolean> cache = new HashMap<String, Boolean>();
        return isScramble(cache, s1, s2);
    }

    public boolean isScramble(Map<String, Boolean> cache, String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        if (cache.containsKey(s1+s2)) return cache.get(s1+s2); // retrieve cached value
        int len = s1.length();
        int[] chars = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i)-'a']++;
            chars[s2.charAt(i)-'a']--;
        }
        for (int n : chars) {
            if (n != 0) {
                cache.put(s1+s2, false);
                return false;
            }
        }
        boolean res = false;

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(cache, s1.substring(0,i), s2.substring(0,i)) &&
                    isScramble(cache, s1.substring(i), s2.substring(i)))
            {
                res = true; break;
            }
            if (isScramble(cache, s1.substring(0, i), s2.substring(len-i)) &&
                    isScramble(cache, s1.substring(i), s2.substring(0, len-i)))
            {
                res = true; break;
            }
        }
        cache.put(s1+s2, res);
        return res;
    }

    public static void main(String[] args) {
        String s1 = "abb", s2 = "bba";
        ScrambleString sol = new ScrambleString();
        System.out.println(sol.isScramble(s1, s2));
    }
}
