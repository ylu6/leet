/**
 * Created by yeqing on 12/13/2017.
 */
public class ValidParenthesisString {
    // v1, two scan, left to right, right to left. Hard to prove the correctness !!
    public boolean checkValidString(String s) {
        // (((*))(), valid from left to right, also from right to left
        // **(((), valid from left to right, but failed from right to left
        // scan from left to right, all stars are treated as '(' or empty string
        int left = 0, right = 0, star = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='(') left++;
            else if(c=='*') star++;
            else {
                if(left>0) left--;
                else if (star>0) star--;
                else return false;
            }
        }
        if(left>star) return false; // even all stars are used as ')', the string is still invalid, so return false
        // scan from right to left, all stars are treated as ')' or empty string
        star = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c==')') right++;
            else if(c=='*') star++;
            else {
                if(right>0) right--;
                else if (star>0) star--;
                else return false;
            }
        }
        if(right>star) return false;
        return true;
    }

    // v2, reversive approach, time O(3^N)
    public boolean checkValidString2(String s) {
        return helper(s, 0, 0);
    }

    boolean helper(String s, int idx, int left) {
        if(left < 0) return false;
        if(idx == s.length()) return left == 0;

        char c = s.charAt(idx);
        if(c == '(') return helper(s, idx+1, left+1);
        else if(c == ')') return helper(s, idx+1, left-1);
        else return helper(s, idx+1, left-1) || helper(s, idx+1, left) || helper(s, idx+1, left+1);
    }

    // v3, one scan, O(N) time, O(1) solution, best solution
    public boolean checkValidString3(String s) {
        // the lower bound and higher bound of: # of '('
        // e.g. (*(), 1st char, '(', lo=1, hi=1
        // 2nd char '*', lo=0, hi=2, corresponding to (), (*, and ((
        // 3rd char '(', lo=1, hi=3
        // 4th char ')', lo=0, hi=2, corresponding to ()(), (*(), ((()
        int lo = 0, hi = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                if(lo > 0) lo--;
                hi--;
                if(hi < 0) return false;
            } else { // c is '*', it can be used as '(' or '*' or ')'
                if(lo > 0) lo--;
                hi++;
            }
        }
        return lo == 0;
    }
}
