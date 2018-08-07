import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 8/12/2017.
 */

public class ArithmeticSlicesIISubsequence {
    // use dp to store number of subsequences ended at index i. But for the transition formula,
    // wee need to know the diff in every arithmetic subsequences, therefore at every index, use a map to store {diff: #of subsequences}
    // for all valid subsequences ended at j with diff d, if A[j]-A[i]==d, then those subsequences all valid at index i
    // However, there may be sub sequences in range [0, j] with length of 2, now they are valid in [0,i]
    // for example, 1,2,2,3,4, at index 3, we have 2 valid, at index 4, besides the 2 from index 3, we have have 2 new valid ss, {2,3,4}{2,3,4}
    // therefore, we also need to know the number of subsequences with diff d and length = 2, for the transition formula
    // SOLUTION: instead of store number of valid subsequence, we store number of subsequence with length >= 2
    // TRANSITION: Count[i,d] = Count[j,d] + 1 if A[i]-A[j]==d, 0<=j<i.
    // All subsequences end at j now ended at i, and one more was added, which {A[j], A[i]}, that's where the 1 comes from
    public int numberOfArithmeticSlices(int[] A) {
        Map<Integer, Integer>[] map = new Map[A.length];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - A[j];
                int d = (int) diff;
                if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) continue;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                res += c2;
                map[i].put((int)d, c2+1 + c1);
            }
        }
        return res;
    }
}
