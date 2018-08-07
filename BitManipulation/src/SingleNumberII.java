/**
 * Created by yeqing on 7/10/2017.
 * Given an array of integers, every element appears three times except for one,
 * which appears exactly once. Find that single one
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int x0 = 0, x1 = 0; // x0, x1 is the bit counter, x0=1 means 1, x1=1 means 2, both are 1 means 3 times
        int mask = 0; // bit mask, if both x0 and x1 is 1, set both to 0.
        for (int n : nums) {
            x1 = x1^(x0&n); // flip x1, if both x0 and the new number is 1
            x0 = x0^n; // flip x0, if the new number is 1
            mask = ~(x0&x1); // mask is 0 only when both x0 and x1 are 1.
            x1 &= mask; // set to 0 if mask is 0, when mask is 1, keep the original number
            x0 &= mask;
        }
        return x0;
    }
}
