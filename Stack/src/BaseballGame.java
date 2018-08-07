import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 10/20/2017.
 * Integer (one round's score): Directly represents the number of points you get in this round.
 * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : ops) {
            if (s.equals("+")) {
                int temp1 = stack.pollFirst();
                int temp2 = stack.peekFirst() + temp1;
                stack.addFirst(temp1);
                stack.addFirst(temp2);
            }
            else if (s.equals("D")) {
                stack.addFirst(2*stack.peekFirst());
            }
            else if (s.equals("C")) {
                stack.pollFirst();
            }
            else {
                stack.addFirst(Integer.parseInt(s));
            }
        }
        while (!stack.isEmpty())
            res += stack.pollFirst();
        return res;
    }
}
