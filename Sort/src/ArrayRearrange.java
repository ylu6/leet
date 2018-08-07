import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ArrayRearrange {
    /*
    Uber 电面
    数组重拍 count多的放在后面，相同count，数字小的放在前面. 牛人云集,一亩三分地
    e.g. [1,2,1,1,2,3,3] -> [2,2,3,3,1,1,1].本文原创
     */
    // v1, O(nlogn)
    int[] rearrange(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) map.put(n, map.getOrDefault(n, 0)+1);

        TreeSet<Map.Entry<Integer, Integer>> set = new TreeSet<>(
                (a,b)->Integer.compare(a.getValue(),b.getValue())==0
                        ? Integer.compare(a.getKey(),b.getKey()) : Integer.compare(a.getValue(),b.getValue())
        );
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
            set.add(entry);

        int[] res = new int[nums.length];
        int pos = 0;
        for(Map.Entry<Integer, Integer> entry : set) {
            add(res, pos, entry.getKey(), entry.getValue());
            pos += entry.getValue();
        }
        return res;
    }

    // v2, bucket sort, still O(nlogn)
    // in case {1,5,7,8,13,2,4}, every number is unique
    // the problem is indeed sort of the array, which is nlogn
    int[] rearrange2(int[] nums) {
        return null;
    }
    void add(int[] arr, int pos, int num, int count) {
        while(count-- > 0) {
            arr[pos++] = num;
        }
    }

    public static void main(String[] args) {
        ArrayRearrange sol = new ArrayRearrange();
        int[] nums = {1,2,1,1,2,3,3};
        for(int n : sol.rearrange(nums))
            System.out.print(n + " ");;
    }
}
