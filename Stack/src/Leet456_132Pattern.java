import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 12/11/2017.
 */
public class Leet456_132Pattern {
    // 132 pattern definition: index i < j < k and a[i] < a[k] < a[j].
    // if scan j from left to right, we keep the minimum value for elements left to j
    // then scan from j+1 to end, to check whether 132 pattern exist, this is O(N^2)
    // for left to right scan, we search for largest value in the middle, keep minimum value
    // at left side, but for right side, we need to check third < max && third > min
    // this is hard to track
    // If SCAN from RIGHT TO LEFT, O(N):
    // we search for 32 pairs, once a new 32 pair was found, update the third value, we always store the largest third value
    // then in the left side we search for min, if min < third, 132 found
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        // minArr[i]: minimum number in array nums in index rage [0, i]
        int[] minArr = new int[nums.length];
        minArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            minArr[i] = Math.min(nums[i], minArr[i-1]);

        Deque<Integer> stack = new ArrayDeque<>();
        int third = Integer.MIN_VALUE;
        stack.addFirst(nums[nums.length-1]);
        for (int i = nums.length-2; i >= 0; i--) {
            if (minArr[i] < third) return true; // found a 132 pattern
            if (nums[i] > stack.peekFirst()) { // found 32 paatern
                int temp = stack.pollFirst();
                while (!stack.isEmpty() && nums[i] > stack.peekFirst())
                    temp = stack.pollFirst();
                third = Math.max(third, temp); // update third, keep track of largest third value in all valid 32 pairs
            }
            stack.addFirst(nums[i]); // add current number into stack
        }
        return false;
    }
    public static void main(String[] args) {
        Leet456_132Pattern sol = new Leet456_132Pattern();
        int[] nums = {-2, 1, 2, -2, 1, 2};
        System.out.println(sol.find132pattern(nums));
    }
}
