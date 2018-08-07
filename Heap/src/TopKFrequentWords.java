import java.util.*;

/**
 * Created by yeqing on 11/2/2017.
 */
public class TopKFrequentWords {

    // version 1: use PriorityQueue, O(nlogk)
    public List<String> topKFrequent(String[] words, int k) {
        Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k+1,
                (a, b)->a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        Map<String, Integer> map = new HashMap<>();

        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<String> res = new LinkedList<>();
        while (!minHeap.isEmpty())
            res.add(0, minHeap.poll().getKey());

        return res;
    }

    // version 2: use BucketSort, O(n)
    public List<String> topKFrequent2(String[] words, int k) {
        List<String> res = new LinkedList<>();
        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words)
            freqMap.put(w, freqMap.getOrDefault(w, 0)+1);

        // each bucket points to a Trie (or null)
        TrieNode[] buckets = new TrieNode[words.length+1]; // word frequency varies from 0 to word.length
        for (String str : freqMap.keySet()) { // add all entries in the freqMap to bucket
            int freq = freqMap.get(str);
            if (buckets[freq] == null) buckets[freq] = new TrieNode();
            addWord(buckets[freq], str);
        }
        // retrieve k sorted results from buckets
        for (int i = words.length; i >= 0 && res.size() < k; i--) {
            if (buckets[i] == null) continue;
            StringBuilder sb = new StringBuilder();
            getWords(res, buckets[i], k, sb);
        }

        return res;
    }

    class TrieNode{
        TrieNode[] next;
        boolean isEndNode;
        TrieNode() {
            next = new TrieNode[26];
            isEndNode = false;
        }
    }

    // add word into Trie
    void addWord(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new TrieNode();
            cur = cur.next[idx];
        }
        cur.isEndNode = true;
    }
    // retrieve word from Trie, preorder traversal
    void getWords(List<String> res, TrieNode root, int k, StringBuilder sb) {
        if (root == null || res.size() == k) return;
        if (root.isEndNode) res.add(sb.toString());

        for (int i = 0; i < 26; i++) {
            if (root.next[i] != null) {
                sb.append((char)('a'+i));
                getWords(res, root.next[i], k, sb);
                if (sb.length()>0) sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}

