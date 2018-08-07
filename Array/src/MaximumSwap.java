/**
 * Created by yeqing on 11/22/2017.
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] map = new int[10]; // store last occurrence of digit (0-9) in array digits

        for (int i = 0; i < digits.length; i++) map[digits[i]-'0'] = i;

        // loop from left to right, for current digit, if there is a larger digit on the right, solution found
        // we want the maximum, therefore check from 9 to digits[i]+1
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i]-'0';
            System.out.println(digit);
            for (int k = 9; k > digit; k--) { // check from large to small
                if (map[k] > i) { // solution found, map[k] is the index we want to swap with index i
                    digits[map[k]] = digits[i];
                    digits[i] = (char)(k+'0'); // convert digit k to char
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int num = 2736;
        MaximumSwap sol = new MaximumSwap();
        System.out.println(sol.maximumSwap(num));
    }
}
