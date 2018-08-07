/**
 * Created by yeqing on 8/3/2017.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length()==0) return 0;
        int[] longest = new int[s.length()];
        int maxLen = 0;
        for (int i = 1; i < s.length(); i++) { // longest[0] is always 0, start from 1
            char c = s.charAt(i);
            if (c == '(') longest[i] = 0;
            if (c == ')') {
                if (s.charAt(i-1) == '(')
                    longest[i] = 2 + (i > 1 ? longest[i-2] : 0);
                else {
                    int prvIdx = i - 1 - longest[i-1];
                    if (prvIdx >= 0 && s.charAt(prvIdx) == '(')
                        longest[i] = 2 + (prvIdx > 0 ? longest[prvIdx-1] : 0) + longest[i-1];
                }
            }
            maxLen = Math.max(maxLen, longest[i]);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        LongestValidParentheses sol = new LongestValidParentheses();
        String s = "()(())";
        System.out.println(sol.longestValidParentheses(s));
    }
}
