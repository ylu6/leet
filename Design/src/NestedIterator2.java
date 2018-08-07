import java.util.*;

// second approach for the NestedIterator problem
// only use one stack

public class NestedIterator2 implements Iterator<Integer>{

    Deque<NestedInteger> stack;
    public NestedIterator2(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        ListIterator<NestedInteger> it = nestedList.listIterator(nestedList.size());
        while(it.hasPrevious()) {
            stack.addFirst(it.previous());
        }
    }

    @Override
    public boolean hasNext() {
        NestedInteger top;
        while(!stack.isEmpty()) {
            if(stack.peekFirst().isInteger()) return true;
            top = stack.pollFirst();
            List<NestedInteger> list = top.getList();
            ListIterator<NestedInteger> it = list.listIterator(list.size());
            while(it.hasPrevious()) {
                stack.addFirst(it.previous());
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pollFirst().getInteger();
    }
}

