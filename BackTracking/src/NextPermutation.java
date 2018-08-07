import java.util.Arrays;

/**
 * Created by yeqing on 5/30/2017.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length-1; i > 0; i--) { // scan from backend of array
            if (nums[i-1] < nums[i]) { // find first element which violate increasing order from backend
                int idx = nextLarger(nums, i, nums.length-1, nums[i-1]);
                System.out.println("idx = " + idx + " i = " + i);
                int temp = nums[i-1]; // swap nums[i-1] with nums[idx]
                nums[i-1] = nums[idx];
                nums[idx] = temp;
//                Arrays.sort(nums,i, nums.length);
                // after exch i-1 with idx, the sub-array [i, end] is still in descending order
                reverse(nums, i, nums.length); // reverse [start, end)
                return;
            }
        }
        Arrays.sort(nums);
    }
    // subarray [lo, hi] is sorted in descending order
    // the ceiling is guaranteed to be in the sub-array
    // return the index of the smallest element in sub-array which is > key
    private int nextLarger(int[] nums, int lo, int hi, int key) {
        int mid = lo + (hi-lo)/2;

        if (nums[mid] > key && (mid == hi || nums[mid+1] <= key))
            return mid;
        if (nums[mid] <= key)
            return nextLarger(nums, lo, mid-1, key);
        else
            return nextLarger(nums, mid+1, hi, key);
    }
    // reverse sub-array in the range of [startIdx, endIdx)
    private void reverse(int[] nums, int startIdx, int endIdx) {
        int mid = (endIdx-startIdx)/2;
        for (int i = 0; i < mid; i++) {
            int temp = nums[startIdx+i];
            nums[startIdx+i] = nums[endIdx-1-i];
            nums[endIdx-1-i] = temp;
        }
    }
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = {1, 3, 2};
//        int idx = np.nextLarger(nums, 2, nums.length-1, 4);
//        System.out.println(idx);
        np.nextPermutation(nums);
        for (int n : nums)
            System.out.print(n + " ");
    }
}
