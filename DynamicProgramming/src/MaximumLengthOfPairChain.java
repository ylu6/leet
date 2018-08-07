import java.util.Arrays;

/**
 * Created by yeqing on 8/25/2017.
 */
public class MaximumLengthOfPairChain {
    // this is indeed a greedy algorithm, sort the array by second element of the pair
    // for first pair, we want the tail to be as small as possible
    // if second pair has larger tail but overlap with first one,
    // first pair must be the best candidate
    // same for the 2nd, 3rd ....
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a,b)->a[1]-b[1]);
        int res = 0, currEnd = Integer.MIN_VALUE;

        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > currEnd) {
                res++;
                currEnd = pairs[i][1];
            }
        }
        return res;
    }
}
