import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeqing on 7/14/2017.
 */
public class LongestUncommonSubsequenceII {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b)->b.length()-a.length());
        Set<String> set = new HashSet<>();
        Set<String> duplicates = getDuplicates(strs);

        for (String s : strs) {
            if (duplicates.contains(s)) { // this is a duplicate, therefore invalid
                set.add(s);     // add s to set
                continue;       // continue to process next string
            }
            else {              // no duplicate of s in string array
                boolean isValid = true;
                for (String str : set) { // check whether s is subsequence of any string in the set
                    if (isSubsequence(s, str)) {  // if yes
                        isValid = false;
                        set.add(s);         // s is invalid, add s to set
                        break;              // break current loop, continue to process next string
                    }
                }
                if (isValid) return s.length(); //s is not subsequence of any string
            }
        }
        return -1;
    }
    // v2
    public int findLUSlength2(String[] strs) {
        Arrays.sort(strs, (a, b)->b.length()-a.length());
        Set<String> duplicates = getDuplicates(strs);

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            if (!duplicates.contains(s)) {
                if (i==0) return s.length();
                for (int j = 0; j < i; j++) {
                    if (isSubsequence(s, strs[j])) break;
                    if (j == i-1) return s.length();
                }
            }
        }
        return -1;
    }

    private Set<String> getDuplicates (String[] strs) {
        Set<String> duplicates = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (String s : strs) {
            if (set.contains(s)) duplicates.add(s);
            else set.add(s);
        }
        return duplicates;
    }

    // check if s1 is subsequence of s2
    private boolean isSubsequence(String s1, String s2) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < s1.length() && idx2 < s2.length()) {
            if (s1.charAt(idx1) == s2.charAt(idx2)) idx1++;
            idx2++;
        }
        return idx1 == s1.length();
    }
    public static void main(String[] args) {
        LongestUncommonSubsequenceII sol = new LongestUncommonSubsequenceII();
        String[] strs = {"aabbcc", "aabbcc","cb","abc"};
        System.out.println(sol.findLUSlength2(strs));
//        System.out.println(sol.isSubsequence("abc", "aabbcc"));
    }
}
