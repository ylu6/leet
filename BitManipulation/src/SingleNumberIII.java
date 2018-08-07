/**
 * Created by yeqing on 7/10/2017.
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int[] res = {0, 0};
        int val = 0;
        for (int n : nums)
            val ^= n;

        // here is the key part of the algorithm
        // after xor all the elements in the array, we indeed get the xor value of the two single number x0^x1
        // if the bit in x0 and x1 is same, after xor, val on that bit is 0
        int LSB = val&(-val); // get the right most bit which is 1

        // now we just partition the whole array into two subsets, one has same bit value as LSB,
        // and the other has different
        for (int n : nums) {
            if ((n&LSB) == 0)
                res[0] ^= n;
            else
                res[1] ^= n;
        }
        return res;
    }
}
