import java.util.*;

/**
 * Created by yeqing on 5/26/2017.
 */
public class BinaryWatch {
    public List<String> readBruteForce(int num) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    String time = h + ":" + (m<10 ? "0" : "") + m;
                    res.add(time);
                }
            }
        }
        return res;
    }
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = {8, 4, 2, 1};
        int[] nums2 = {32, 16, 8, 4, 2, 1};
        for (int i = 0; i <= num; i++) { // number of 1 digits for hours
            if (i > 3 || num - i > 5) continue; // invalid, out of bound
            List<Integer> hours = generateDigits(nums1, i);
            List<Integer> mins  = generateDigits(nums2, num-i);
            for (int h : hours) {
                for (int m : mins) {
                    if (h < 12 && m < 60)
                        res.add(h + ":" + (m>9 ? m : "0"+m));
                }
            }
        }
        return res;
    }

    // nums is array of values for each digit,
    // N is number of one digits
    private List<Integer> generateDigits(int[] nums, int N) {
        List<Integer> res = new ArrayList<>();
        helper(nums, N, 0, 0, res);
        return res;
    }

    private void helper(int[] nums, int N, int pos, int val, List<Integer> res) {
        if (nums.length - pos < N) return; // number digits left in array is less than N, invalid
        if (N == 0) {
            res.add(val); // base case, push value into List
            return;
        }
        helper(nums, N, pos+1, val, res); // current digit is not used
        helper(nums, N-1, pos+1, val+nums[pos], res); // current digit is used
    }
    public static void main(String[] args) {
        BinaryWatch w = new BinaryWatch();
        for (String s : w.readBinaryWatch(2))
            System.out.print(s + " ");
        System.out.println();
        for (String s : w.readBruteForce(2))
            System.out.print(s + " ");
    }
}
