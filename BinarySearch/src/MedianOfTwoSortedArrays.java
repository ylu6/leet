/**
 * Created by yeqing on 8/2/2017.
 */
public class MedianOfTwoSortedArrays {
    // 0, 1 ... i-1 | i, i+1 ... m
    // 0, 1 ... j-1 | j, j+1 ... n
    // # of elements in left part = i+j
    // # of elements in right part = m-i + n-j = m+n - (i+j)
    // we break all elements into left and right part, left part has (m+n+1)/2, right part has (m+n-1)/2
    // in the end, if m+n is even, # in left part equals # in right part, pick max from left and min from right
    // if m+n is odd, pick max element from left part
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) // make sure length of 1st array is shorter than or equals to 2nd
            return findMedianSortedArrays(nums2, nums1);
        // m <= n
        if (m == 0 && n == 1) return nums2[0];
        int lo = 0, hi = m, halfLen = (m+n+1)/2;
        while (lo <= hi) {
            int i = lo + (hi-lo)/2;
            int j = halfLen - i; // when i=0, j is halfLen, it is valid. when i=m, j=(m+n+1)/2-m, which is >=0, valid
            // if i == m or i == 0, the right position was found
            if (i < m && nums1[i] < nums2[j-1]) lo = i+1; // when i<m, j must >= 1
            else if (i > 0 && nums2[j] < nums1[i-1]) hi = i-1;
            else { // right position was found
                int maxOfLeft, minOfRight;
                if (i == 0) maxOfLeft = nums2[j-1];
                else if (j == 0) maxOfLeft = nums1[i-1];
                else maxOfLeft = Math.max(nums1[i-1], nums2[j-1]);

                if (i == m) minOfRight = nums2[j];
                else if (j == n) minOfRight = nums1[i];
                else minOfRight = Math.min(nums1[i], nums2[j]);

                if ((m+n)%2 == 1)
                    return maxOfLeft;
                else
                    return (maxOfLeft+minOfRight)/2.0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {1,2,3,4,5};
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
        System.out.println(sol.findMedianSortedArrays(nums1, nums2));
    }
}
