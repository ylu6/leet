import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs) {
            String key = getKey(str);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    String getKey(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    String getKey2(String s) {
        int[] map = new int[26];
        for(char c : s.toCharArray())
            map[c-'a']++;

        StringBuilder sb = new StringBuilder();
        for(char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
            sb.append(map[c]);
        }
        return sb.toString();
    }
}
