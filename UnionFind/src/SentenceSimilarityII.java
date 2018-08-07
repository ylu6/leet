import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 11/30/2017.
 */
public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, String> parent = new HashMap<>();
        for (String[] pair : pairs)
            union(pair[0], pair[1], parent);

        for (int i = 0; i < words1.length; i++) {
            String p1 = find(words1[i], parent);
            String p2 = find(words2[i], parent);
            if (!p1.equals(p2))  return false;
        }
        return true;
    }
    // return ID (root string) of the group which 'word' belongs to
    String find(String word, Map<String, String> parent) {
        if (!parent.containsKey(word)) return word; // if word not in Map, return itself
        while (!parent.get(word).equals(word)) {
            parent.put(word, parent.get(parent.get(word))); // path compression
            word = parent.get(word);
        }
        return word;
    }
    // add w1 and w2 to the map if not already exist, do union
    void union(String w1, String w2, Map<String, String> parent) {
        parent.putIfAbsent(w1, w1);
        parent.putIfAbsent(w2, w2);
        String p1 = find(w1, parent);
        String p2 = find(w2, parent);
        if (!p1.equals(p2)) {
            parent.put(p1, p2);
        }
    }

    public static void main(String[] args) {
        String[] words1 = {"I","have","enjoyed","happy","thanksgiving","holidays"};
        String[] words2 = {"I","have","enjoyed","happy","thanksgiving","holidays"};
        String[][] pairs = {{"great","good"},{"extraordinary","good"},{"well","good"},
                {"wonderful","good"},{"excellent","good"},{"fine","good"},{"nice","good"},
                {"any","one"},{"some","one"},{"unique","one"},{"the","one"},{"an","one"},
                {"single","one"},{"a","one"},{"truck","car"},{"wagon","car"},{"automobile","car"},
                {"auto","car"},{"vehicle","car"},{"entertain","have"},{"drink","have"},
                {"eat","have"},{"take","have"},{"fruits","meal"},{"brunch","meal"},{"breakfast","meal"},
                {"food","meal"},{"dinner","meal"},{"super","meal"},{"lunch","meal"},{"possess","own"},
                {"keep","own"},{"have","own"},{"extremely","very"},{"actually","very"},{"really","very"},
                {"super","very"}};

        SentenceSimilarityII sol = new SentenceSimilarityII();
        System.out.println(sol.areSentencesSimilarTwo(words1, words2, pairs));
    }
}
