import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class MovingAverage {
    private long sum = 0;
    private Queue<Event> queue = new ArrayDeque<>();
    private final int ExpireInSeconds = 4;

    //这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
    private int getNow(){
        return (int) Instant.now().getEpochSecond(); //暂时写个0，苟活
    }
    public void record(int val){
        int curTime = getNow();
        removeExpired(curTime);
        queue.add(new Event(curTime, val));
        sum += val;
    }

    public double getAvg(){
        int curTime = getNow();
        removeExpired(curTime);
        printQueue(queue);
        return queue.size() == 0 ? 0.0 : 1.0*sum/queue.size();
    }

    void removeExpired(int curTime){
        while(!queue.isEmpty() && curTime-queue.peek().timestamp >= ExpireInSeconds) {
            sum -= queue.poll().val;
        }
    }

    public double getMedium() {
        int curTime = getNow();
        removeExpired(curTime);
        int N = queue.size();
        List<Event> list = new ArrayList<Event>(queue);
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = list.get(i).val;
        shuffle(arr);
        printQueue(queue);
        if(N%2 == 0) {
            return (findKth(arr,N/2, 0, N-1) + findKth(arr,N/2-1, 0, N-1))/2.0;
        }
        return (double)findKth(arr,N/2, 0, N-1);
    }

    class Event{
        int timestamp;
        int val;
        public Event(int timestamp, int val) {
            this.timestamp = timestamp;
            this.val = val;
        }
    }
    void printQueue(Queue<Event> queue){
        Queue<Event> q = new ArrayDeque<>(queue);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty())
            sb.append(q.poll().val).append(" ");
        System.out.println(sb.toString());
    }

    int findKth(int[] arr, int k, int lo, int hi) {
        while(lo < hi) {
            int idx = partition(arr, lo, hi);
            if (idx == k) return arr[idx];
            if (idx < k) lo = idx + 1;
            else if (idx > k) hi = idx - 1;
        }
        return arr[hi];
    }

    int partition(int[] arr, int lo, int hi){
        int i = lo+1, j = hi;
        while(true) {
            while (i <= hi && arr[i] < arr[lo]) i++;
            while (j > lo && arr[j] > arr[lo]) j--;
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }
    void shuffle(int[] arr) { // randomly shuffle the array
        Random r = new Random();
        for(int i = arr.length-1; i > 0; i--) {
            int idx = r.nextInt(i+1); // generate a random int in range [0, i]
            swap(arr, i, idx); // swap i with random idx
        }
    }
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        MovingAverage sol = new MovingAverage();
        for(int i = 1; i <= 20; i++) {
            sol.record(i);
            System.out.println(sol.getMedium());
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
        int[] arr = {10};
    }
}
