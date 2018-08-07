import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 12/10/2017.
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // a stack of array to index
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            // pop out all previous temperatures which are smaller then current T
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekFirst()]) {
                int idx = stack.pollFirst();
                res[idx] = i-idx; // current T is the next higher T of the one we just popped out from stack
            }
            stack.addFirst(i); //add current temp and idx to stack
        }
        return res;
    }
}
