import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

// in case the incoming date is huge, aggregate data every few sec and store in a bucket
public class MovingAverage2 {

    private Deque<Bucket> queue = new ArrayDeque<>();
    private int totalCount = 0;
    private long totalSum = 0;
    private final int BucketDuration = 1; // bucket aggregate data within one second interval
    private final int ExpireInSeconds = 5;

    public void record(int val){
        int curTime = getNow();
        removeExpired(curTime);

        int prvTime = queue.isEmpty() ? -1 : queue.peekLast().startTime; // starting time of most recent bucket in the queue

        if(prvTime == -1) { // in case the bucket is EMPTY !!!
            queue.addLast(new Bucket(curTime, BucketDuration, 1, val));
        }
        else if(curTime - prvTime >= BucketDuration) { // if the difference is longer than the bucket duration, add new bucket
            int periods = (curTime - prvTime)/BucketDuration;
            int newBucketTime = prvTime + periods*BucketDuration;
            queue.addLast(new Bucket(newBucketTime, BucketDuration, 1, val));
        }
        else { // otherwise, combine val to the most recent bucket
            Bucket curBucket = queue.peekLast();
            curBucket.count++;
            curBucket.sum += val;
        }

        totalCount++; // increase totcalCount by 1
        totalSum += val; // add val to totalSum
    }

    public double getAvg(){
        int curTime = getNow();
        removeExpired(curTime);
        printQueue(queue);
        return totalCount == 0 ? 0.0 : 1.0*totalSum/totalCount; // avoid division by 0
    }

    void removeExpired(int curTime) { // remove expired buckets from queue, update totalCount and totalSum
        while(!queue.isEmpty() && curTime - queue.peekFirst().startTime > ExpireInSeconds) {
            Bucket toBeremoved = queue.pollFirst(); // remove expired buckets from the queue
            totalCount -= toBeremoved.count; // deduct count of this bucket from totcalCount
            totalSum -= toBeremoved.sum; // deduct sum of this bucket from totalSum
        }
    }

    private int getNow(){
        return (int) Instant.now().getEpochSecond();
    }
    class Bucket {
        int startTime; // in seconds
        int duration; // in seconds
        int count;  // count of values stored in this bucket
        long sum; // sum of values stored in this bucket
        public Bucket(int startTime, int duration, int count, long sum) {
            this.startTime = startTime;
            this.duration = duration;
            this.count = count;
            this.sum = sum;
        }
    }
    void printQueue(Queue<Bucket> queue){
        Queue<Bucket> q = new ArrayDeque<>(queue);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            Bucket cur = q.poll();
            sb.append(cur.sum + "/" +cur.count + " ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        MovingAverage2 sol = new MovingAverage2();
        for(int i = 1; i <= 30; i++) {
            sol.record(i);
            System.out.println(sol.getAvg());
            try{
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
