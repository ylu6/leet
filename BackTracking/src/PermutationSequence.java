import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 5/31/2017.
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * return the kth permutation
 * Note: Given n will be between 1 and 9 inclusive
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] values = {0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        boolean[] used = new boolean[10];
        StringBuilder res = new StringBuilder();
        //recursive approach
//        getPermutation(n, k-1, values, used, res);

        //iterative approach
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);

        k = k-1; // convert kth to index
        for (; n > 1; n--) {
            System.out.println("n = " + n + " k = " + k);
            int idx = k/values[n-1];
            System.out.println("idx = " + idx);
            res.append(nums.get(idx));
            nums.remove(idx); //O(N)
            k = k%values[n-1];
        }
        res.append(nums.get(0)); // add the last element remaining in the list
        return res.toString();
    }

    // idx = k-1
    private void getPermutation(int n, int idx, int[] values, boolean[] used, StringBuilder res) {
        if(n==1) { // only one number left
            res.append(getNthNumber(1, used));
            return;
        }
        int digit = getNthNumber(idx/values[n-1]+1, used);
        System.out.println("idx = " + idx + " digit = " + digit);
        res.append(digit);
        used[digit] = true;
        getPermutation(n-1, idx%values[n-1], values, used, res);
    }
    private int getNthNumber(int n, boolean[] used) {
        for (int i = 1; i <= 9; i++) {
            if (used[i]) continue;
            n--;
            if (n == 0) return i;
        }
        return -1;
    }

    public static void main (String[] args) {
        PermutationSequence ps = new PermutationSequence();
//        System.out.println(ps.getNthNumber(2, new boolean[4]));
        System.out.println(ps.getPermutation(3, 2));
    }
}
