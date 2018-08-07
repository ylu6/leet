import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ZigzagIteratorII {
    /*
     * @param vecs: a list of 1d vectors
     */
    List<List<Integer>> vecs;
    Queue<Iterator<Integer>> queue;
    public ZigzagIteratorII(List<List<Integer>> vecs) {
        this.vecs = vecs;
        queue = new ArrayDeque<>();
        Iterator<List<Integer>> it = vecs.iterator();
        List<Integer> list;
        while(it.hasNext()) {
            list = it.next();
            if(!list.isEmpty()) queue.add(list.iterator()); // add iterator of non-empty list into queue
        }
    }

    /*
     * @return: An integer
     */
    public int next() {
        Iterator<Integer> it = queue.poll();
        int res = it.next();
        if(it.hasNext()) queue.add(it);
        return res;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
