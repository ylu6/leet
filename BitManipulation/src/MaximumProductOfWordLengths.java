/**
 * Created by yeqing on 7/27/2017.
 */
public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int[] values = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++)
                values[i] = values[i] | (1 << (word.charAt(j)-'a'));
        }

        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if ((values[i] & values[j]) == 0)
                    res = Math.max(res, words[i].length()*words[j].length());
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        MaximumProductOfWordLengths sol = new MaximumProductOfWordLengths();
        System.out.println(sol.maxProduct(words));
    }
}
