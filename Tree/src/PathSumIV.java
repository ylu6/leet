import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PathSumIV {
    /**
     * @param nums: a list of integers
     * @return: return an integer
     */
    // num: abc, level a (1-5), pos b (1,2,3...), value c
    // use HashMap to store {ab: c}
    public int pathSum(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums)   map.put(n/10, n%10);

        return preorder(11, 0, map);
    }

    // preorder traversal of the tree
    int preorder(int coord, int pathSum, Map<Integer, Integer> map) {
        int level = coord/10, pos = coord%10;
        int left = (level+1)*10 + 2*pos - 1, right  = left + 1;
        pathSum += map.get(coord);
        if(!map.containsKey(left) && !map.containsKey(right)) {
            return pathSum;
        }
        int sum = 0;
        if(map.containsKey(left))   sum += preorder(left, pathSum, map);
        if(map.containsKey(right))  sum += preorder(right, pathSum, map);
        return sum;
    }
}
