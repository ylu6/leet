import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 12/10/2017.
 */

public class VerifyPreorderSerializationOfABinaryTree {
    // if s is a string, add to stack
    // if s is '#': if top of stack if not "#", do nothing, otherwise, pop twice,
    // do this in a while loop until top of stack is no longer "#"
    // if "#" is the left node of a inner node, top of stack is not "#", therefore no popping from stack
    // when "#" is the right node, top of stack is "#"
    public boolean isValidSerialization(String preorder) {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : preorder.split(",")) {
            if (!s.equals("#")) stack.addFirst(s);
            else {
                while (!stack.isEmpty() && stack.peekFirst().equals("#")) {
                    stack.pollFirst();
                    if (stack.isEmpty()) return false;
                    stack.pollFirst();
                }
                stack.addFirst(s); // add "#" into the stack
            }
        }
        return stack.size()==1 && stack.peekFirst().equals("#");
    }
}
