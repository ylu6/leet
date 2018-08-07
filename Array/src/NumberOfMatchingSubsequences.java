import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NumberOfMatchingSubsequences {

    // brute force approach, run time O(wordsLen * (sLen+wordLen))
    public int numMatchingSubseq(String S, String[] words) {
        int count = 0;
        for(String w : words) {
            if(isSubseq(S, w)) count++;
        }
        return count;
    }
    boolean isSubseq(String S, String w) {
        int pos = 0, idx = 0;
        while(pos < S.length()) {
            if(S.charAt(pos) == w.charAt(idx)) {
                idx++;
                if(idx == w.length()) return true;
            }
            pos++;
        }
        return false;
    }

    // preprocess the String S, create a index map for each character in S
    public int numMatchingSubseq2(String S, String[] words) {
        int count = 0;
//        List<Integer>[] indexMap = new List[26];
        TreeSet<Integer>[] indexMap = new TreeSet[26];
        for(int i = 0; i < 26; i++)
            indexMap[i] = new TreeSet<>();

        for(int i = 0; i < S.length(); i++)
            indexMap[S.charAt(i)-'a'].add(i);

        for(String w : words) {
            if(isSubseq2(indexMap, w)) count++;
        }
        return count;
    }

    boolean isSubseq2(TreeSet<Integer>[] indexMap, String word) {
        int prv = 0;
        for(int i = 0; i < word.length(); i++) {
            Integer curr = indexMap[word.charAt(i)-'a'].ceiling(prv);
            if(curr == null) return false;
            prv = curr+1;
        }
        return true;
    }

    public static void main(String[] args) {
        String S = "abcde";
        NumberOfMatchingSubsequences sol = new NumberOfMatchingSubsequences();
        System.out.println(sol.isSubseq(S, "ae"));
        System.out.println(sol.isSubseq(S, "aa"));
        System.out.println(sol.numMatchingSubseq2(S, new String[]{"ae", "aa"}));
    }
}
