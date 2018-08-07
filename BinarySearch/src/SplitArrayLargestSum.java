/**
 * Created by yeqing on 8/11/2017.
 */
public class SplitArrayLargestSum {
    // the possible value must be in the range [max_of_num, sum_of_num]
    // use binary search to search solution in this range
    // for value mid, use greedy algorithm to partition the array
    // if the partition has more than m subarray, means the mid is too small, we need to search [mid+1, hi]
    // if can be partitioned into m subarray, mid is a possible candidate, but we need a optimized solution
    // therefore continue the binary search, set hi=mid because mid is possible solution
    // when lo == hi, we found the optimized solution
    public int splitArray(int[] nums, int m) {
        long sum = 0;
        int max = 0;
        for(int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }

        long lo = (long) max, hi = sum;
        while(lo < hi) {
            long mid = (lo + hi)/2;
            if(isValid(nums, mid, m)) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return (int) lo;
    }

    // whether nums can be partitioned into m sub-array, with sum of every sub-array <= max
    // use greedy
    boolean isValid(int[] nums, long max, int m) {
        int count = 1, sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(sum + nums[i] > max) {
                sum = nums[i];
                count++;
                if(count > m) return false;
            } else
                sum += nums[i];
        }
        return true;
    }
}
