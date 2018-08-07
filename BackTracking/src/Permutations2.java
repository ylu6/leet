import java.util.*;

/**
 * Created by yeqing on 5/30/2017.
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        //permuteUnique(nums, res, 0);
        permuteUnique(nums, res, used, new ArrayList<Integer>());
        return res;
    }

    private void permuteUnique(int[] nums, List<List<Integer>> res, int startIdx) {
        if (startIdx == nums.length-1) {
            List<Integer> temp = new ArrayList<>();
            for (int n : nums) temp.add(n);
            res.add(temp);
            return;
        }
        // Set must be used here. although array is sorted at beginning
        // exch of numbers during the process will break the original order
        // therefore check nums[i] == nums[i-1] cannot avoid elements with same value
        // be used at same position
        Set<Integer> used = new HashSet<>();
        for (int i = startIdx; i < nums.length; i++) {
            //if (i > startIdx && nums[i]==nums[i-1]) continue; //won't work for 0,0,0,1,9
            if (used.add(nums[i])) {
                exch(nums, startIdx, i);
                permuteUnique(nums, res, startIdx + 1);
                exch(nums, startIdx, i);
            }
        }
    }
    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void permuteUnique(int[] nums, List<List<Integer>> res, boolean[] used, List<Integer> path) {
        System.out.println(path.toString());
        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i]==nums[i-1] && !used[i-1]) continue; // skip if used or duplicate
            used[i] = true;
            path.add(nums[i]);
            permuteUnique(nums, res, used, path);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        Permutations2 p = new Permutations2();
        for (List<Integer> list : p.permuteUnique(nums)) {
            for (int n : list)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
