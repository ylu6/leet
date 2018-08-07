import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;


// This is indeed a tree traversal problem, we want to print all the leaf node inorder
// therefore it is similar to iterative inorder traversal of tree
// use stack<Iterator<NestedInteger>> and cur NestedInteger
// cur always point to the next
public class NestedIterator implements Iterator<Integer> {

    Deque<Iterator<NestedInteger>> stack;
    NestedInteger cur; // cur always point to the next Integer

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<Iterator<NestedInteger>>();
        cur = null;
        Iterator<NestedInteger> it = nestedList.iterator();

        while(!stack.isEmpty() || it.hasNext()) {
            if(it.hasNext()) {
                cur = it.next();
                stack.addFirst(it);
                if(cur.isInteger()) break;
                it = cur.getList().iterator();
            } else {
                it = stack.pollFirst();
            }
        }
    }

    @Override
    public boolean hasNext() {
        // in case of empty list [] exists as the last element in the NestedInteger
        // cur will point to the iterator of the empty list
        // therefore needs to check both null and isInteger()
        return cur != null && cur.isInteger();
    }

    @Override
    public Integer next() {
        Integer res = cur.getInteger();

        Iterator<NestedInteger> it = stack.pollFirst();
        List<NestedInteger> list;
        while(!stack.isEmpty() || it.hasNext()) {
            if(it.hasNext()) {
                cur = it.next();
                stack.addFirst(it);
                if(cur.isInteger()) return res;
                it = cur.getList().iterator();
            } else {
                it = stack.pollFirst();
            }
        }

        cur = null;
        return res;
    }
}

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer,
    // rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds,
    // if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds,
    // if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}