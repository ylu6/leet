import java.util.Arrays;

public class ShortEncodingOfWords {
    // String A B, if A ends with B, we can decode B as part of A
    // If look at the string from back to front, this is a prefix problem, can be solved by Trie
    // Insert string into Trie in reverse order (time, insert emit)
    // whenever a new branch is created, add the length of current word and 1 for the "#"
    class TrieNode {
        TrieNode[] next;
        boolean isEndNode;
        public TrieNode(){
            next = new TrieNode[26];
            isEndNode = false;
        }
    }
    public int minimumLengthEncoding(String[] words) {
        // sort by length in descending order, insert me before time will cause problem
        Arrays.sort(words, (s1, s2)->s2.length()-s1.length());
        TrieNode root = new TrieNode();
        int len = 0;

        for(String w : words) {
            TrieNode curr = root;
            for(int i = w.length()-1; i >= 0; i--) {
                int idx = (int) (w.charAt(i) - 'a');
                if(curr.next[idx]==null) {
                    if(i == 0) len += w.length() + 1; // insert new work into reference string, add #
                    curr.next[idx] = new TrieNode();
                }
                curr = curr.next[idx];
            }
            curr.isEndNode = true;
        }

        return len;
    }

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        ShortEncodingOfWords sol = new ShortEncodingOfWords();
        System.out.println(sol.minimumLengthEncoding(words));
    }
}
