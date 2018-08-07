import java.util.Arrays;

/**
 * Created by yeqing on 6/9/2017.
 */
public class RussianDollEnvelopes {
    // sort the envelopes by width, if width is the same, by length in desc, find the LIS from length
    // why desc in length? {1,1}, {5,2}, {4,2},{3,2}, ascending order will give wrong results
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b)-> a[1] != b[1] ? a[1]-b[1] : b[0] - a[0]);
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int len = 0;
        for (int[] env : envelopes) {
            int idx = Arrays.binarySearch(dp, 0, len, env[0]);
            if (idx < 0) {
                idx = -(idx+1);
                dp[idx] = env[0];
                if (idx == len) len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        RussianDollEnvelopes rd = new RussianDollEnvelopes();
        System.out.println(rd.maxEnvelopes(envelopes));
    }
}
