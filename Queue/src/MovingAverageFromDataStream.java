import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MovingAverageFromDataStream {
    Deque<Integer> queue;
    int size, sum;
    public MovingAverageFromDataStream(int size) {
        queue = new ArrayDeque<>(size);
        this.size = size;
        sum = 0;
    }

    public double next(int val) {
        if(queue.size() == size) sum = sum - queue.pollFirst() + val;
        else sum += val;
        queue.addLast(val);
        return 1.0*sum/queue.size();
    }

    public static void main(String[] args) {
        MovingAverageFromDataStream sol = new MovingAverageFromDataStream(3);
        System.out.println(sol.next(1));
        System.out.println(sol.next(10));
        System.out.println(sol.next(3));
        System.out.println(sol.next(5));
    }
}
