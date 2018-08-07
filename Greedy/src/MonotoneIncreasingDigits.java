/**
 * Created by yeqing on 12/16/2017.
 */
public class MonotoneIncreasingDigits {
    // scan digits from left to right, if next > cur, append cur to SB
    // if next < cur, append cur-1 to sb, and append 9 as remaining digits
    // if next== cur, e.g 14443, if following the above rule, we get 14429, WRONG! should be 13999

    // BETTER WAY is to scan from right to left, and find the left most digit where a inversion occurs
    // then reduce that digit by 1 and change all digits at the right side to 9
    public int monotoneIncreasingDigits(int N) {
        char[] num = String.valueOf(N).toCharArray();
        int pos = num.length;
        for (int i = num.length-1; i > 0; i--) {
            if (num[i-1]>num[i]) {
                pos = i-1; // once an inversion pair was found, record pos and reduce num[i-1] by 1
                num[i-1]--; // break tie, e.g 14443, found first 4,3 pair-> 14433 ->14333 -> 13333 therefore pos is at index 1
            }
        }
        for (int i = pos+1; i < num.length; i++) num[i] = '9';

        return Integer.parseInt(new String(num));
    }
    public static void main(String[] args) {
        System.out.println('9'-'0'-1);
    }
}
