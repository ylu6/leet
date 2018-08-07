import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        String[] strs = str.split(" ");

        if(pattern.length() != strs.length) return false;
        int val1, val2;
        for(int i = 0; i < strs.length; i++) {
            val1 = map1.getOrDefault(pattern.charAt(i), -1);
            val2 = map2.getOrDefault(strs[i], -1);
            if(val1 != val2) {
                return false;
            }
            if(!map2.containsKey(strs[i])) {
                map1.put(pattern.charAt(i), i);
                map2.put(strs[i], i);
            }
        }
        return true;
    }
    // v2, use HashMap + HashSet
    public boolean wordPattern2(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] arr = str.split(" ");
        if(pattern.length() != arr.length) return false;

        for(int i = 0; i < arr.length; i++) {
            char c = pattern.charAt(i);
            String s = arr[i];

            if(map.containsKey(c)) {
                if(!s.equals(map.get(c))) return false;
            } else {
                if(set.contains(s)) return false;
            }
            map.put(c, s);
            set.add(s);
        }

        return true;
    }

    public static void main(String[] args) {
        String pattern = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccdd";
        String str = "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";

        WordPattern sol = new WordPattern();
        System.out.println(sol.wordPattern(pattern, str));
    }
}
