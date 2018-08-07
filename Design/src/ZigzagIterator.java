import java.util.List;

public class ZigzagIterator {
    /*
     * @param v1: A 1d vector
     * @param v2: A 1d vector
     */
    int i,j;
    List<Integer> v1, v2;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // do intialization if necessary
        i = 0;
        j = 0;
        this.v1 = v1;
        this.v2 = v2;
    }

    /*
     * @return: An integer
     */
    public int next() {
        if((i <= j && i < v1.size()) || j == v2.size()) {
            return v1.get(i++);
        } else {
            return v2.get(j++);
        }
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return i < v1.size() || j < v2.size();
    }
}
