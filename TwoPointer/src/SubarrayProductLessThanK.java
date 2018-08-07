/**
 * Created by yeqing on 12/11/2017.
 */
public class SubarrayProductLessThanK {
    // two pointer
    // e.g. 10, 5, 2, 6
    // 10       +1
    // 10, 5    +2
    // 10, 5, 2  invalid
    // 5, 2     +2
    // 5, 2, 6  +3
    // total is 8
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // all nums > 0
        int res = 0, p = 1;
        for (int slow = 0, fast = 0; slow <= fast && fast < nums.length; ) {
            p *= nums[fast]; // multiply current number
            while(p>=k){ // move from left side until product smaller than target
                p /= nums[slow++];
            }
            res += fast-slow+1; // update results
            fast++;
        }
        return res;
    }
}
