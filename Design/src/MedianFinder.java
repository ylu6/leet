import java.util.PriorityQueue;

/**
 * Created by yeqing on 7/7/2017.
 */
public class MedianFinder {
    PriorityQueue<Integer> small; // store the smaller half
    PriorityQueue<Integer> big; // store the bigger half
    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>(
                (a,b)->a<b ? 1 : (a>b ? -1 : 0)
        ); // maxPQ
        big = new PriorityQueue<>(); // minPQ
    }

    public void addNum(int num) {
        if (small.size()==0) {
            small.add(num);
            return;
        }
        if (num <= small.peek()) { // new number belongs to smaller half
            small.add(num);
            if (small.size()-big.size() > 1)
                big.add(small.poll());
        }
        else {                      // new number belongs to bigger half
            big.add(num);
            if (big.size()-small.size()>1)
                small.add(big.poll());
        }
    }

    public double findMedian() {
        if (small.size() < big.size())  return big.peek();
        else if (small.size() > big.size()) return small.peek();
        else return (small.peek() + big.peek())/2.0;
    }

    public static void main(String[] args) {
        MedianFinder sol = new MedianFinder();
        sol.addNum(1);
        sol.addNum(2);
        System.out.println(sol.findMedian());
        sol.addNum(3);
        System.out.println(sol.findMedian());
    }
}