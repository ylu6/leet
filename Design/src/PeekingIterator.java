import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer>{

    Integer nxt;
    Iterator<Integer> it;

    public PeekingIterator(Iterator<Integer> iterator) {
        nxt = iterator.hasNext() ? iterator.next() : null;
        it = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nxt;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ret = nxt;
        nxt = it.hasNext() ? it.next() : null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return nxt != null;
    }
}
