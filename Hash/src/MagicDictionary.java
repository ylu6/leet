import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yeqing on 11/2/2017.
 * when add a word into the dictionary, e.g. apple, add *pple, a*ple, ap*le, app*e and appl*
 * Use two HashMap internally, one store original word,
 * the other store keys with wildcard, and count the number of occurrence
 */
public class MagicDictionary {
    Set<String> original;
    Map<String, Integer> extension;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        original = new HashSet<>();
        extension = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        String tmpStr;
        for (String s : dict) {
            if (original.contains(s)) continue;
            original.add(s);
            for (int i = 0; i < s.length(); i++) {
                tmpStr = s.substring(0, i) + "*" + s.substring(i + 1);
                extension.put(tmpStr, extension.getOrDefault(tmpStr, 0) + 1);
            }
        }
    }
    // use StringBuilder instead of substring
    public void buildDict2(String[] dict) {
        String tmpStr;
        for (String s : dict) {
            if (original.contains(s)) continue;
            StringBuilder sb = new StringBuilder(s);
            original.add(s);
            for (int i = 0; i < s.length(); i++) {
                sb.setCharAt(i,'*');
                tmpStr = sb.toString();
                extension.put(tmpStr, extension.getOrDefault(tmpStr, 0) + 1);
                sb.setCharAt(i, s.charAt(i)); // set it back
            }
        }
    }
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {

        for (int i = 0; i < word.length(); i++) {
            String tmp = word.substring(0,i) + "*" + word.substring(i+1);
            int count = extension.getOrDefault(tmp, 0);
            // e.g. if appl* == 2, which means there are words in the dictionary (like apple, apply)
            // in this case, it is always true
            if (count > 1) return true;
            if (count == 1 && !original.contains(word)) return true;
        }
        return false;
    }
    // use StringBuilder instead of substring
    public boolean search2(String word) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            sb.setCharAt(i, '*');
            String tmp = sb.toString();
            int count = extension.getOrDefault(tmp, 0);
            // e.g. if appl* == 2, which means there are words in the dictionary (like apple, apply)
            // in this case, it is always true
            if (count > 1) return true;
            if (count == 1 && !original.contains(word)) return true;
            sb.setCharAt(i, word.charAt(i));
        }
        return false;
    }
}
