import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yeqing on 12/1/2017.
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        int idx = Arrays.binarySearch(arr, x);
        int left, right;
        if (idx >= 0) { // x is in the array, add this element to res first, then scan from two sides of this idx
            res.add(arr[idx]);
            left = idx-1;
            right = idx+1;
        } else { // x is not the in array, find the insertion position
            idx = -1*(idx+1); // arr[left] < x, arr[right] > x
            left = idx-1;
            right = idx;
        }
        while(res.size() < k) {
            if (left < 0) res.add(arr[right++]); // elements at left side used up
            else if (right >= arr.length) res.add(0, arr[left--]); // elements at the right side used up
            else if (x-arr[left] <= arr[right]-x) res.add(0, arr[left--]); // left distance is <= right distance
            else res.add(arr[right++]);
        }
        return res;
    }

    // v2, in case of asking for start/end index, this approach is O(logN), v1 is O(logN + k)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int lo = 0, hi = arr.length - k;
        while(lo < hi) {
            int m = lo + (hi-lo)/2;
            if(x - arr[m] > arr[m+k] - x) lo = m + 1;
            else hi = m;
        }
        List<Integer> res = new ArrayList<>();
        while(k-- > 0) res.add(arr[lo++]);
        return res;
    }
}
