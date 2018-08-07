/**
 * Created by yeqing on 12/19/2017.
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        for (int j = s.length()-1; j >= 0; j--) {
            if(isPalindrom(s,j))
                return new StringBuilder(s.substring(j+1)).reverse().toString() + s;
        }
        return "";
    }
    // return true is s.substring(0, j+1) is a palindrome, [0,j]
    boolean isPalindrom(String s, int j) {
        for(int i = 0; i < j; i++) {
            if(s.charAt(i)!=s.charAt(j--)) return false;
        }
        return true;
    }
}
