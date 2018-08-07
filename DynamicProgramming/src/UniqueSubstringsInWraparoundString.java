/**
 * Created by yeqing on 6/22/2017.
 */
public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        int len = 1, result = 0;
        int[] counts = new int[26];
        counts[p.charAt(0)-'a'] = 1;
        for (int i = 1; i < p.length(); i++) {
            char curr = p.charAt(i);
            char prv = p.charAt(i-1);
            if (curr - prv == 1 || (curr == 'a' && prv == 'z'))
                len++;
            else
                len = 1;
            counts[curr-'a'] = Math.max(counts[curr-'a'], len);
        }

        for (int n : counts) result += n;
        return result;
    }
}
