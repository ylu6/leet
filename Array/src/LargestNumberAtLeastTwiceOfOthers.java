/**
 * Created by yeqing on 12/28/2017.
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        if(nums.length ==1) return 0;
        int first = 0, second = 1;
        if(nums[1] > nums[0]) {
            first = 1;
            second = 0;
        }

        for (int i = 2; i < nums.length; i++) {
            if(nums[i] > nums[first]) {
                second = first;
                first = i;
            }
            else if (nums[i] > nums[second])
                second = i;
        }
        return nums[second]*2 <= nums[first] ? first : -1;
    }
}
