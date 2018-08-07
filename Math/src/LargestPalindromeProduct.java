/**
 * Created by yeqing on 9/1/2017.
 */
public class LargestPalindromeProduct {
    // A greedy approach will be adopted to solve this problem
    // for a n digits number, max = 10^n - 1, min = 10^(n-1), e.g. n=2, max = 99, min = 10
    // max product is 99*99 = 9801, left part is 98, can form palindrome 9889
    // which cannot be formed by mutiply two 2-digits number
    // the next largest can be formed by reflect 97, which is 9779, similarly, we try 96, 95...
    public int largestPalindrome(int n) {
        long max = (long)Math.pow(10, n) - 1;
        long min = max/10;
        long mod = (long)Math.pow(10, n);
        long product = max*max;

        for(long h = max; h > min; h--) {
            long left = h, right = 0;
            for(long tmp = left; tmp > 0; right =right*10+tmp%10, tmp/=10, left*=10);
            long palindrome = left + right;
//            System.out.println(palindrome);

            for (long i = max; i > min; i--) {
                long j = palindrome/i; // i, j is the pair and i >= j
                if (i < j || j <= min) break;
                if (palindrome%i==j) return (int)(palindrome%1337);
            }
        }
        return 9;
    }

    // check
    boolean isValid(long num, long min, long max) {
        for (long i = max; i > min; i--) {
            long j = num/i;
            if (j <= min || j > i) break; // check this first!!! otherwise much slower
            if (num%i == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LargestPalindromeProduct sol = new LargestPalindromeProduct();
//        System.out.println(sol.reverse(123000));
        System.out.println(sol.largestPalindrome(6));
    }
}
