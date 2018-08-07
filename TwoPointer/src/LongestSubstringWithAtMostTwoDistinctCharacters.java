import java.util.HashMap;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
//    Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//    For example, Given s = “eceba” and k = 2,
//    T is "ece" which its length is 3.
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() <= 2) return s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0, k = 2;

        for(int lo = 0, hi = 0; hi < s.length(); hi++) {
            char c = s.charAt(hi);
            if(map.containsKey(c)) map.put(c, 1+map.get(c)); // map already contains key
            else { // a new char, check current number of chars in substring, if it is k, move left boundary
                while(map.size() >= k) {
                    char charToDelete = s.charAt(lo);
                    int count = map.get(charToDelete);
                    if(count > 1) map.put(charToDelete, count-1);
                    else map.remove(charToDelete);
                    lo++;
                }
                map.put(c, 1);
            }
            maxLen = Math.max(maxLen, hi-lo+1);
        }
        return maxLen;
    }

    //
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        if(s.length() <= 2) return s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0, k = 2;
        // ecebb, when lo at 0, hi at 3, the map contains {e:2, c: 1}, and we want to add b into map
        // when move lo to right, 'e' 'c' will be removed, and lo ends at index=2
        for(int lo = 0, hi = 0; hi < s.length(); hi++) {
            char c = s.charAt(hi);
            map.put(c, hi);
            while(map.size() > k) {
                if(map.get(s.charAt(lo)) == lo) map.remove(s.charAt(lo));
                lo++;
            }
            maxLen = Math.max(maxLen, hi-lo+1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "ecebabbaccccccb";
        LongestSubstringWithAtMostTwoDistinctCharacters sol = new LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println(sol.lengthOfLongestSubstringTwoDistinct2(s));
    }
}
