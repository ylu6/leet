/**
 * Created by yeqing on 11/28/2017.
 */
public class IntegerReplacement {
    // if n has 2 or more than 2 consecutive ones at the tail, add 1 to n
    // the case n == 3 (11) should be excluded, because in such case -1 is better
    // if n has 2 or more than 2 consecutive ones at the tail, add 1 to n
    // the case n == 3 (11) should be excluded, because in such case -1 is better
    public int integerReplacement(int n) {
        int res = 0;
        while (n != 1) { // cannot use n > 1, because when overflow, n can be negtive
            if (n%2==0) { // n is even
                n = n >>> 1; // must use >>>, because when add 1, the integer can overflow
            } else {
                if ((n&3) == 3 && n != 3) n++; // last two bits are 1 and n is not 3
                else n--;
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        IntegerReplacement sol = new IntegerReplacement();
        System.out.println(sol.integerReplacement(8));
        System.out.println(sol.integerReplacement(7));
    }
}
