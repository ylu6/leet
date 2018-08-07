import java.util.*;

/**
 * Created by yeqing on 5/28/2017.
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        permuteDFS(nums, res, new ArrayList<>());
        permute(nums, res, 0);
        return res;
    }

    private void permuteDFS(int[] nums, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int n : nums) {
            if (path.contains(n)) continue;
            path.add(n);
            permuteDFS(nums, res, path);
            path.remove(path.size()-1);
        }
    }

    private void permute(int[] nums, List<List<Integer>> res, int startIdx) {
        if (startIdx == nums.length-1) {
//            List<Integer> temp = Arrays.stream(nums).boxed().collect(Collectors.toList());
            List<Integer> temp = new ArrayList<>(); // stream version is much slower!!!
            for (int n : nums) temp.add(n);
            res.add(temp);
            return;
        }
        for (int i = startIdx; i < nums.length; i++) {
            exch(nums, startIdx, i);
            permute(nums, res, startIdx+1);
            exch(nums, startIdx, i);
        }
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permutations p = new Permutations();
        for (List<Integer> list : p.permute(nums)) {
            for (int n : list)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
