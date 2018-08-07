import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        for(String s : banned) set.add(s);
        String[] words = paragraph.replaceAll("[!?',;.]", " ").toLowerCase().split("\\s+");
        int maxCount = 0;
        String res = "";

        for(String w : words) {
            if(!set.contains(w)) {
                int count = map.getOrDefault(w, 0) + 1;
                if (count > maxCount) {
                    maxCount = count;
                    res = w;
                }
                map.put(w, count);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "Bob. hIt, baLl";
        MostCommonWord sol = new MostCommonWord();
        sol.mostCommonWord(s, new String[]{"bob", "hit"});
    }
}
