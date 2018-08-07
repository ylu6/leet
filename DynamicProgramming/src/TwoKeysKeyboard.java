/**
 * Created by yeqing on 8/24/2017.
 */
public class TwoKeysKeyboard {
    // if n is a prime, then n is the result
    // if n is not a prime, then breakdown n into product of several primes
    // n = a1*a2*...*ak,  any of the ai is prime number
    // the minSteps is sum of ai, which is a1+a2+a3...+ak
    // example, n= 24, can be breakdown as n = 2*2*2*3, minSteps=2+2+2+3 = 9
    public int minSteps(int n) {
        if (n==1) return 0;
        if (n <= 5) return n;
        int res = 0;
        while (n>5) {
            int step = n; // in case n is a prime, this step will be applied to final result
            int upperBound = (int) Math.sqrt(n);
            // search for the smallest prime number i, such that n%i == 0
            // is i is not prime, but n%i == 0, i can be written as i=a1*a2, and n = k*a1*a2
            // therefore n can be divided by a1 or a2, which is smaller than i
            // the loop must been broke earlier, therefore the first number we found must be a prime
            for (int i = 2; i <= upperBound; i++) {
                if (n%i == 0) {
                    step = i;
                    n = n/i;
                    break;
                }
                if (i == upperBound) return res+step; // n is a prime
            }
            res += step;
        }
        return n==1?res:res+n;
    }
    public static void main(String[] args) {
        TwoKeysKeyboard sol = new TwoKeysKeyboard();
        System.out.println(sol.minSteps(24));
    }
}
