import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yeqing on 8/3/2017.
 */
public class SmallestRange {
    // idea is similar as merge k sorted list
    // every time remove one element from heap, then add element from the same list
    // the range is always min and max of the Heap
    // use heap to keep the min, and a variable max to store the maximum
    public int[] smallestRange(List<List<Integer>> nums) {
        // MinPQ, store int array with 3 elements, {val, index, arrayID}
        // MinPQ ordered by val
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a, b) -> a[0] < b[0] ? -1 : (a[0] == b[0] ? 0 : 1)
        );
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            max = Math.max(max, val);
            pq.add(new int[]{val, 0, i});
        }
        int[] smallest = {pq.peek()[0], max};
        while (true) {
            int[] min = pq.poll();
            List<Integer> list = nums.get(min[2]); // current list
            if (min[1] == list.size()-1)
                return smallest;  // current list is depleted, search ended
            int nextVal = list.get(min[1]+1);
            pq.add(new int[]{nextVal, min[1]+1, min[2]});
            if (nextVal > max) max = nextVal;
            int range = max - pq.peek()[0];
            if (range < smallest[1] -smallest[0])
                smallest = new int[]{pq.peek()[0], max};
        }
    }
}
