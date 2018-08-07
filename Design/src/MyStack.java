import java.util.ArrayDeque;
import java.util.Queue;

public class MyStack {
    // Implement Stack using Queues

    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayDeque<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        int count = queue.size()-1;
        while(count-- > 0) {
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}