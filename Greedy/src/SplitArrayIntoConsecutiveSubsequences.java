import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 11/17/2017.
 */
public class SplitArrayIntoConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(); // count freq of elements in array
        Map<Integer, Integer> appFreq = new HashMap<>(); // appFreq[i] stores freq of valid sub sequence ended with i-1

        //initialize the freq map
        for (int n : nums) freq.put(n, 1+freq.getOrDefault(n,0));

        // loop invariant: every element before current position are all part of some valid sub-sequence
        // element after current position may be used earlier
        for (int n : nums) {
            if (freq.get(n) == 0) continue; // this number was used, continue to next element

            if (appFreq.getOrDefault(n, 0) > 0) { // number n can be appended to some existing valid sub-seq
                freq.put(n, freq.get(n)-1); // decrease freq of n by q
                appFreq.put(n, appFreq.get(n)-1); // freq of sub-seq ended if n-1 decreased by 1
                appFreq.put(n+1, appFreq.getOrDefault(n+1,0) + 1); // freq of sub-seq ended with n increased by 1
            }
            // cannot be appended to previous sequence, therefore, n+1 and n+2 must exist in array
            // otherwise it is not possible to form a continuous sub sequence with n
            // this is the greedy part, always try to find two more to form valid sub-seq
            else if (freq.getOrDefault(n+1,0) >0 && freq.getOrDefault(n+2,0) > 0) {
                freq.put(n, freq.get(n)-1); // decrease freq of n by q
                freq.put(n+1, freq.get(n+1)-1); // use number n+1
                freq.put(n+2, freq.get(n+2)-1); // use number n+2
                appFreq.put(n+3, 1+appFreq.getOrDefault(n+3, 0)); // forms sub-seq [n, n+1, n+2], update appFreq
            }
            else return false;
            printMap(freq);
        }
        return true;
    }

    <K,V> void printMap(Map<K, V> map) {
        for (Map.Entry<K,V> entry : map.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,5};
        SplitArrayIntoConsecutiveSubsequences sol = new SplitArrayIntoConsecutiveSubsequences();
        System.out.println(sol.isPossible(nums));
    }
}
