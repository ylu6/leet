import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 8/14/2017.
 */
public class CreateMaximumNumber {
    // pick i digits from nums1 form largest number, and k-i digits from nums2
    // merge the two picked number to get largest value
    // varying i from max(0, k-nums2.length) to nums1.length, take the largest
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] res = new int[k];
        for (int i = Math.max(0, k-n2); i <= n1 && i <= k; i++) {
            int[] val = merge(maxArray(nums1, i), maxArray(nums2, k-i));
            if(greater(val, 0, res, 0)) res = val;
        }
        return res;
    }
    int[] maxArray(int[] nums, int k) { // pick k digits from array
        int[] res = new int[k];
        if (k == 0) return res;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(nums[0]);
        int count = nums.length - k; // select k is equivalent to remove len-k
        for (int i = 1; i < nums.length; i++) {
            while ( !stack.isEmpty() && nums[i] > stack.peekFirst() && count > 0) {
                count--;
                stack.pollFirst();
            }
            stack.addFirst(nums[i]);
        }
        while (count-- > 0) { stack.pollFirst(); }
        for (int i = k-1; i >= 0; i--) { res[i] = stack.pollFirst(); }
        return res;
    }
    // merge two array to get largest value
    int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length+nums2.length];
        for (int i = 0, j = 0, idx = 0; idx < res.length; idx++) {
            res[idx] = greater(nums1, i,nums2, j) ? nums1[i++] : nums2[j++];
        }
        return res;
    }
    // return true if nums1[i:end] is no less than nums2[j:end]
    boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++; j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        CreateMaximumNumber sol = new CreateMaximumNumber();
        int[] arr1 = sol.maxArray(nums2, 6);
        sol.printArray(sol.merge(nums1, nums2));
    }
}
