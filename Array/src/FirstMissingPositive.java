/**
 * Created by yeqing on 8/7/2017.
 */
public class FirstMissingPositive {
    // idea: mapping positive number (1,2,3...) to array index(0,1,2...), by num-1 -> index
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            // nums[i] not in valid range, or already in correct position, do nothing and move to next number
            if (nums[i] <= 0 || nums[i] > nums.length || i==nums[i]-1) {
                i++;
            }
            else if (nums[nums[i]-1] != nums[i]) swap(nums, i, nums[i]-1); // current num and the number to be exchanged doesn't equal
                                                                            // to avoid infinite loop
            else i++;
        }
        for (int i = 0; i< nums.length; i++) {
            if (i != nums[i]-1) return i+1;
        }
        return nums.length+1;
    }
    void swap (int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    void printArray(int[] nums) {
        for (int n : nums)
            System.out.print(n + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        FirstMissingPositive sol = new FirstMissingPositive();
        System.out.println(sol.firstMissingPositive(nums));
    }
}
