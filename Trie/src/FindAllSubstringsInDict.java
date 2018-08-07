import java.util.*;

public class FindAllSubstringsInDict {
    public List<String> getStringsInDict(List<String> dict, String s) {
        Set<String> set = new HashSet<>();
        set.addAll(dict);
        List<String> res = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j <= s.length(); j++) {
                String substr = s.substring(i,j);
                if(set.contains(substr)) {
                    res.add(substr);
                }
            }
        }
        return res;
    }

    public List<String> getStrings(List<String> dict, String s) {
        TrieNode root = new TrieNode();
        for(String str : dict) add(root, str);
        List<String> res = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            query(res, s.substring(i), root);
        }
        return res;
    }

    void query(List<String> res, String s, TrieNode root) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(root.next[c-'a'] == null) return;
            sb.append(c);
            root = root.next[c-'a'];
            if(root.isLeaf) res.add(sb.toString());
        }
    }

    class TrieNode {
        TrieNode[] next;
        boolean isLeaf;
        TrieNode(){
            next = new TrieNode[26];
            isLeaf = false;
        }
    }

    // add String s into Trie
    void add(TrieNode root, String s) {
        TrieNode cur = root;
        for(char c : s.toCharArray()) {
            if(cur.next[c-'a'] == null) cur.next[c-'a'] = new TrieNode();
            cur = cur.next[c-'a'];
        }
        cur.isLeaf = true;
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String s = "thisisaword";
        List<String> dict = Arrays.asList("this", "hi", "his", "is", "word");
        FindAllSubstringsInDict sol = new FindAllSubstringsInDict();
        System.out.println(sol.getStringsInDict(dict, s).toString());
        System.out.println(sol.getStrings(dict, s).toString());
    }
}
