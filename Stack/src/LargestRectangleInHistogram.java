import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 8/7/2017.
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        // values in the stack are monolithic increase
        Deque<Integer> indices = new ArrayDeque<>(); // Deque used as stack
        int maxArea = 0, top = 0, width = 0; // top is the top element in the stack
        for (int i = 0; i < heights.length; ) {
            if (indices.isEmpty() || heights[i] >= heights[indices.peekFirst()]) {
                indices.addFirst(i++);
            }
            else {
                top = indices.pollFirst();
                // width is i - top's previous index in stack
                width = indices.isEmpty() ? i : i-indices.peekFirst()-1;
                maxArea = Math.max(maxArea, heights[top]*width);
            }
        }
        while (!indices.isEmpty()) {
            top = indices.pollFirst();
            width = indices.isEmpty() ? heights.length : heights.length-indices.peekFirst()-1;
            maxArea = Math.max(maxArea, heights[top]*width);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        int[] heights = {2,1,2};
        LargestRectangleInHistogram sol = new LargestRectangleInHistogram();
        System.out.println(sol.largestRectangleArea(heights));
    }
}
