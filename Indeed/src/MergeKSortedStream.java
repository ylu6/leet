import java.util.*;

public class MergeKSortedStream {

    class Num{ // wrapper class for the stream, because it doesn't have peek function
        int num;
        Stream stream;
        public Num(Stream stream, int num) {
            this.stream = stream;
            this.num = num;
        }
    }

    List<Integer> getNumberInAtLeastKStream(List<Stream> streams, int k){
        PriorityQueue<Num> minPQ = new PriorityQueue<Num>(
                (a,b)->Integer.compare(a.num, b.num)
        );
        for(Stream stream : streams) {
            if(stream.move()) {
                int n = stream.getValue();
                minPQ.add(new Num(stream, n));
            }
        }

        List<Integer> res = new ArrayList<>();
        Integer prv = null;
        int count = 0;
        while(!minPQ.isEmpty()) {
            Num curNum = minPQ.poll();
            if(prv != null && prv == curNum.num) { // current number is the same as previous number
                count++;
                if(count == k) res.add(curNum.num); // num will be add once, even count is larger than k
            } else {
                count = 1;
                prv = curNum.num;
            }
            if(count + minPQ.size() < k) return res; // early terminate if not enough candidate
                                                    // check before the curNum was added back to minPQ
            // duplicate numbers in a stream are only counted once
            // therefore move the iterator to next different number
            while(curNum.stream.move()) {
                int nxt = curNum.stream.getValue();
                if(curNum.num != nxt) {
                    curNum.num = nxt; // update curNum
                    minPQ.add(curNum); // add curNum back to minPQ
                    break;
                } else {
                    continue;
                }
            } // if curNum.stream is depleted and nxt not found, curNum will not be added back to minPQ
        }
        return res;
    }
    public static void main(String[] args) {
        MergeKSortedStream test = new MergeKSortedStream();
        Integer[] arr1 = {1,2,3,4};
        Integer[] arr2 = {2,5,6, 7, 7, 7, 7, 7, 7};
        Integer[] arr3 = {2,5,7, 7};

        List<Integer> l1 = Arrays.asList(arr1);
        List<Integer> l2 = Arrays.asList(arr2);
        List<Integer> l3 = Arrays.asList(arr3);

        List<Stream> streams = new ArrayList<>();
        streams.add(new Stream(l1));
        streams.add(new Stream(l2));
        streams.add(new Stream(l3));

        List<Integer> res = test.getNumberInAtLeastKStream(streams, 2);
        System.out.println(res);
    }
}

class Stream{
    Iterator<Integer> iterator;
    public Stream(List<Integer> list){
        this.iterator = list.iterator();
    }
    public boolean move(){
        return iterator.hasNext();
    }
    public int getValue(){
        return iterator.next();
    }
}
