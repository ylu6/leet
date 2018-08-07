/**
 * Created by yeqing on 9/1/2017.
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 */
public class BitwiseANDOfNumbersRange {
    // 0100 1101 -- 0101 0101: start from n, add 1 every time until reach n,
    // all the digits right of 01 were toggled, which means there must be at least
    // one 0 for each digits for numbers in range [m,n]
    // therefore AND of those digits lead to 0. only 01 were left
    // So we only need to get the common part of left digits of m and n
    public int rangeBitwiseAnd(int m, int n) {
        while(n > m) {
            n = n&(n-1); // remove LSB from n, till n <= m
        }
        return n;
    }
}
