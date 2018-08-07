import java.util.Arrays;

/**
 * Created by yeqing on 9/1/2017.
 */
public class MaximumGap {
    // N numbers in nums, the lower bound for max gap is ceil((max-min)/(N-1))
    // if the interval is integer, it is easy to prove. if it is not integer, (max-min)/(N-1) < interval
    // suppose the max gap can be (max-min)/(N-1), which is interval - 1. In extreme case, suppose all the distance between numbers are max gap
    // min + (max-min)/(N-1) * (N-1) < max, so the max gap cannot be smaller than ceil((max-min)/(N-1))
    // indeed we can pick bucket with size smaller than interval, as long as the max gap cannot be formed by two numbers in same bucket
    public int maximumGap(int[] nums) {
        if(nums.length < 2) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        if(max==min) return 0;

        int bucketSize = (int) Math.ceil(((double)(max-min)/(nums.length-1))); // bucket 0: [min, min+bucketSize)
        int numOfBucket = (int) Math.ceil((double)(max-min)/bucketSize) + 1; // plus is to include max, in case of (max-min)%(N-1)==0
        int[] bucketMin = new int[numOfBucket];
        int[] bucketMax = new int[numOfBucket];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // every bucket only keeps the min and max value of this bucket
        for(int n : nums) {
            int idx = (n-min)/bucketSize;
            bucketMin[idx] = Math.min(n, bucketMin[idx]);
            bucketMax[idx] = Math.max(n, bucketMax[idx]);
        }
//        System.out.println(min + " " + max + " " + bucketSize + " " + numOfBucket);
        System.out.println(bucketMin[3] + " " + bucketMax[3]);
        int maxGap = Integer.MIN_VALUE;
        int prv = bucketMax[0]; // first bucket cannot be empty, min is always in it
        for(int i = 1; i < bucketMin.length; i++) {
            if(bucketMax[i]==Integer.MIN_VALUE && bucketMin[i]==Integer.MAX_VALUE) continue; // empty bucket
            maxGap = Math.max(maxGap, bucketMin[i]-prv);
            prv = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        int[] nums = {100,3,2,1};
        MaximumGap sol = new MaximumGap();
        System.out.println(sol.maximumGap(nums));
    }
}
