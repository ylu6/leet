/**
 * Created by yeqing on 5/19/2017.
 */
import edu.princeton.cs.algs4.*;

public class KdTree {
    TreeNode root;

    class TreeNode {
        Point2D point;
        TreeNode left, right;
        TreeNode(Point2D point) { this.point = point; }
    }

    KdTree(Point2D[] points) {
        root = createKdTree(points, 0, 0, points.length-1);
    }

    TreeNode createKdTree(Point2D[] points, int depth, int lo, int hi) {
        if (lo > hi) return null;
        if (lo == hi) return new TreeNode(points[lo]);

        int midIdx = lo+(hi-lo+1)/2;
        Point2D mid = kthSmallest(points, midIdx, lo, hi); // media point
        TreeNode head = new TreeNode(mid);
        // use mid as pivot point to partition the array
        head.left   = createKdTree(points, depth+1, lo, midIdx-1);
        head.right  = createKdTree(points, depth+1, midIdx+1, hi);

        return head;
    }

    public static void exch(int[] nums, int i, int j) {
        if (i==j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static Point2D kthSmallest(Point2D[] points, int k, int lo, int hi) {
        if (lo == hi) return points[lo];
        int p = partition(points, lo, hi);
        int local_order = p - lo + 1;
        if (k == local_order) return points[p];
        else if (k < local_order) return kthSmallest(points, k, lo, p-1);
        else return kthSmallest(points, k-local_order, p+1, hi);
    }

    private static int partition(Point2D[] points, int lo, int hi) {
        Point2D v = points[lo];
        int i = lo, j = hi+1;
        while (true) {
            while (points[++i] < v) if (i==hi) break;
            while (points[--j] > v) if (j==lo) break;
            if (i >= j) break;
            exch(points, i, j);
        }
        exch(points, lo, j);
        return j;
    }
}
