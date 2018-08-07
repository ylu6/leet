import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementInAnArray {
    // v1: heap, O(NlogK), easy solution
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(k+1);
        for(int n : nums) {
            minPQ.add(n);
            if(minPQ.size() == k+1) minPQ.poll(); // poll the minimum number
        }
        // in the end, the k largest numbers left in the minPQ
        return minPQ.peek();
    }

    // v2, partition, O(N) time O(1) space, tricky solution
    public int findKthLargest2(int[] nums, int k) {
        shuffle(nums);
        int lo = 0, hi = nums.length-1;
        k = nums.length - k;
        while(lo < hi) {
            int p = partition(nums, lo, hi);
            if (p == k) return nums[k];
            else if (p < k) lo = p+1;
            else hi = p-1;
        }
        return nums[lo];
    }

    // after partition, nums[lo] will be in the correct position, j
    // all numbers in [lo : j-1] is <= nums[lo]
    // all numbers in [j+1 : hi] is  <= nums[lo]
    int partition(int[] nums, int lo, int hi) {
        int i = lo+1, j = hi;
        while(true) {
            while(i <= hi && nums[i] < nums[lo]) i++; // <= seems also work
            while(j >  lo && nums[j] > nums[lo]) j--; // >= seems also work
            if(i >= j) break;
            exch(nums, i++, j--); // after exch, move i and j to avoid infinite loop
        }
        exch(nums, lo, j);
        return j;
    }

    void shuffle(int[] nums) {
        Random random = new Random();
        for(int i = nums.length-1; i > 0; i--) {
            exch(nums, random.nextInt(i+1), i);
        }
    }

    void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int nums[] = {3,2,3,1,2,4,5,5,6};
        KthLargestElementInAnArray sol = new KthLargestElementInAnArray();
//        sol.partition(nums, 0, nums.length-1);
//        sol.shuffle(nums);
//        for(int n : nums) {
//            System.out.print(n + " ");
//        }
        System.out.println(sol.findKthLargest2(nums, 3));
    }
}
