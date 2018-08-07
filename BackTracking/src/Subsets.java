import java.util.*;
/**
 * Created by yeqing on 5/28/2017.
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 */
public class Subsets {
    // v1, backtracking
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        dfs(res, tempList, nums, 0);

        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList(tempList)); // create a new list and add it to res

        for(int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            dfs(res, tempList, nums, i+1);
            if(tempList.size() != 0) tempList.remove(tempList.size()-1);
        }
    }
    /**
     *  Number of subsets for {1 , 2 , 3 } = 2^3 .
     why ?
     case    possible outcomes for the set of subsets
     1   ->          Take or dont take = 2
     2   ->          Take or dont take = 2
     3   ->          Take or dont take = 2
     therefore , total = 2*2*2 = 2^3 = { { } , {1} , {2} , {3} , {1,2} , {1,3} , {2,3} , {1,2,3} }
     Lets assign bits to each outcome  -> First bit to 1 , Second bit to 2 and third bit to 3
     Take = 1
     Dont take = 0

     0) 0 0 0  -> Dont take 3 , Dont take 2 , Dont take 1 = { }
     1) 0 0 1  -> Dont take 3 , Dont take 2 ,   take 1       =  {1 }
     2) 0 1 0  -> Dont take 3 ,    take 2       , Dont take 1 = { 2 }
     3) 0 1 1  -> Dont take 3 ,    take 2       ,      take 1    = { 1 , 2 }
     4) 1 0 0  ->    take 3      , Dont take 2  , Dont take 1 = { 3 }
     5) 1 0 1  ->    take 3      , Dont take 2  ,     take 1     = { 1 , 3 }
     6) 1 1 0  ->    take 3      ,    take 2       , Dont take 1 = { 2 , 3 }
     7) 1 1 1  ->    take 3     ,      take 2     ,      take 1     = { 1 , 2 , 3 }
     */
    public List<List<Integer>> subsets2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(1<<i, i);
        }
        int sizeOfSubset = (int) Math.pow(2, nums.length);
        List<List<Integer>> res = new ArrayList<>(sizeOfSubset);
        for (int i = 0; i < sizeOfSubset; i++) {
            List<Integer> subset = new ArrayList<>();
            int idx = i;
            // simple arroach is to loop through all digits no matter 0 or 1, and pick those ones
            // here, use idx & -idex to extract the least significant one bit every time.
            while (idx > 0) {
                int LSB = idx & -idx;
                subset.add(nums[map.get(LSB)]);
                idx = idx - LSB;
            }
            res.add(subset);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Subsets sub = new Subsets();
        for (List<Integer> list : sub.subsets2(nums)) {
            for ( int n : list)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
