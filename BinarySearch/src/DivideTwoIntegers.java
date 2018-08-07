/**
 * Created by yeqing on 12/14/2017.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 0) return Integer.MAX_VALUE;
        int sign = dividend > 0 ^ divisor < 0 ? 1 : -1;
        if(divisor==-1) return dividend==Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        if(divisor==1) return dividend;

        int res = (int) absDivide(Math.abs((long)dividend), Math.abs((long)divisor));
        return sign > 0 ? res : -res;
    }

    private long absDivide(long dividend, long divisor) {
        if(dividend < divisor) return 0; // base case
        long sum = divisor, m = 1;
        // denote divisor as n: n, 2n, 4n, 8n .... it is a binary search process
        while(sum + sum <= dividend){
            sum = sum + sum;
            m = m + m;
        }
        return m+absDivide(dividend-sum, divisor);
    }

    public static void main(String[] args) {
        DivideTwoIntegers sol = new DivideTwoIntegers();
        System.out.println(sol.divide(2147483647, 2));
    }
}
