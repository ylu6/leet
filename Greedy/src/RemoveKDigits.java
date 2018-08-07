import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 8/14/2017.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (num==null || num.length()==0 || k==num.length()) return "0";
        Deque<Character> stack = new ArrayDeque<>();
        stack.addFirst(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && !stack.isEmpty() && c < stack.peekFirst()) {
                stack.pollFirst();
                k--; // remove the top element from stack
            }
            stack.addFirst(c);
        }
        // if all elements in original num is in nondecreasing order
        // all digits will be pushed into and remain in the stack
        // pop out element from stack till k = 0
        while (k > 0) { stack.pollFirst(); k--; }

        // remove leading zero
        while (!stack.isEmpty() && stack.peekLast() == '0') { stack.pollLast(); }
        // construct the final results from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) { sb.append(stack.pollFirst()); }
        // if all the element was removed, return "0"
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
