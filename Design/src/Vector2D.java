import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Vector2D implements Iterator<Integer>{
    Iterator<List<Integer>> outerIt;
    Iterator<Integer> innerIt;

    public Vector2D(List<List<Integer>> vec2d) {
        outerIt = vec2d.iterator();
        innerIt = Collections.emptyIterator(); // if innerIt points to 1st row, 1st row will print twice
    }

    @Override
    public boolean hasNext() {
        if(innerIt.hasNext()) return true;
        while(outerIt.hasNext()) {
            List list = outerIt.next();
            innerIt = list.iterator();
            if(!list.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return innerIt.next();
    }

    @Override
    public void remove() {

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        System.out.println(list.iterator().hasNext());
    }
}
