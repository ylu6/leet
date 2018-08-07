import java.util.*;

public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        // first calculate the rank of each n in nums
        // e.g. 5, 2, 6, 1
        // rank 2, 1, 3, 0
        Map<Integer, Integer> ranks = new HashMap<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        int rank = 0;
        for(int n : map.keySet()) {
            ranks.put(n, rank);
            rank++;
        }

        // thinking process: loop from tail to head, maintain a freq array: store freq of each rank
        // for nums[i], its rank is ranks[i], we need to sum(0, ranks[i]-1), THIS IS A PREFIX SUM PROBLEM!
        // we can use a binary indexed tree, which supports update and getSum in both O(logN) time
        int[] tree = new int[nums.length+1];
        LinkedList<Integer> res = new LinkedList<>();
        for(int i = nums.length-1; i >= 0; i--) {
            rank = ranks.get(nums[i]);
            res.addFirst(getSum(tree, rank-1)); // get prefix sum for ranks from 0 to ranks[i]-1
            update(tree, rank, 1); // for ranks[i], increase its frequency by 1
        }
        return res;
    }

    int getSum(int[] tree, int i) {
        int sum = 0;
        for(int idx = i+1; idx > 0; idx -= idx&(-idx)) {
            sum += tree[idx];
        }
        return sum;
    }
    void update(int[] tree, int i, int delta) {
        for(int idx = i+1; idx < tree.length; idx += idx&(-idx)) {
            tree[idx] += delta;
        }
    }
    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf sol = new CountOfSmallerNumbersAfterSelf();
        int[] nums = {5,2,6,1};
        System.out.println(sol.countSmaller(nums));
    }
}
