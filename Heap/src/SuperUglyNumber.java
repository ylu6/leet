import java.util.TreeSet;

/**
 * Created by yeqing on 12/10/2017.
 */
public class SuperUglyNumber {
    // v1, O(nklog(k)) too slow
    // think of the construction process as a tree
    // starting from 1, 1st step, multiply 1 by every num in primes array, add them to TreeSet
    // poll the min number from TreeSet, do the same process, multiply by num in the primes array, add to TreeSet
    // keep doing this uniti nth SuperUglyNumber was found
    // Using TreeSet instead of Heap, because we want to avoid adding duplicate super ugly number into the candidates pool
    public int nthSuperUglyNumber(int n, int[] primes) {
        int res = 1;
        TreeSet<Integer> minPQ = new TreeSet<>();
        minPQ.add(1);
        while (n > 0) {
            res = minPQ.pollFirst();
            for (int i : primes) {
                minPQ.add(res*i); // if use heap, check duplicates is O(N)
            }
            n--;
        }
        return res;
    }
    // v2, O(nk), Similar approach as Ugly Number II
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] idx = new int[primes.length];
        int[] nums = new int[n];
        nums[0] = 1; // first Super Ugly Number is 1
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE, minIdx = -1;
            for (int j = 0; j < primes.length; j++) {
                if (nums[idx[j]]*primes[j] < min) {
                    min = nums[idx[j]]*primes[j];
                    minIdx = j;
                }
            }
            idx[minIdx]++;
            if (min != nums[i-1]) nums[i] = min; // not a duplicate
            else i--; // found a duplicate, don't increase the i index
        }
        return nums[n-1];
    }
    public static void main(String[] args) {
        int[] primes = {2,7,13,19};
        SuperUglyNumber sol = new SuperUglyNumber();
        for (int i = 1; i <= 12; i++)
            System.out.print(sol.nthSuperUglyNumber(i, primes) + " ");
        System.out.println();
        for (int i = 1; i <= 12; i++)
            System.out.print(sol.nthSuperUglyNumber2(i, primes) + " ");
    }
}
