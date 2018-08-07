/**
 * Created by yeqing on 8/10/2017.
 */
public class WiggleSubsequence {
    // idea is to find all the turning point
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            if (nums[i] > nums[i-1]) {
                while (j+1 < nums.length && nums[j+1] >= nums[j]) { j++; }
                count++; // must use >=, otherwise incorrect
            }
            else if (nums[i] < nums[i-1]) {
                while (j+1 < nums.length && nums[j+1] <= nums[j]) { j++; }
                count++;
            }
            i = j;
        }
        return count;
    }
    public static void main(String[] args) {
        WiggleSubsequence sol =  new WiggleSubsequence();
        int[] nums = {1,3,1,5,4,3,1,2,3,4,4};
        System.out.println(sol.wiggleMaxLength(nums));
    }
}
