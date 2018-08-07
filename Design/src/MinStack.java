import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    Deque<Integer> valueStack;
    Deque<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        valueStack = new ArrayDeque<Integer>();
        minStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        valueStack.addFirst(x);
        if(x <= minStack.peekFirst()) minStack.addFirst(x);
    }

    public void pop() {
        int topVal = valueStack.pollFirst();
        if(topVal == minStack.peekFirst()) minStack.pollFirst();
    }

    public int top() {
        return valueStack.peekFirst();
    }

    public int getMin() {
        return minStack.peekFirst();
    }
}
