import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class ConcurrentRateLimiter {
    Map<Integer, ConcurrentTokenBucket> map;
    public ConcurrentRateLimiter() {
        map = new ConcurrentHashMap<>(16, 0.75F, 16);
    }

    boolean isAllowed(int id) {
        map.putIfAbsent(id, new ConcurrentTokenBucket(5, 1000, 5));
        return map.get(id).allow();
    }

    void access(int id, String threadName){
        System.out.println(threadName + " " + isAllowed(id));
    }

    public static void main(String[] args) {
        ConcurrentRateLimiter limiter = new ConcurrentRateLimiter();
        Thread t1 = new Thread("t1"){
            @Override
            public void run(){
                int count = 10;
                while(count-->0) {
                    limiter.access(1, this.getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2 = new Thread("t2") {
            @Override
            public void run(){
                int count = 10;
                while(count-->0) {
                    limiter.access(1, this.getName());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}

class ConcurrentTokenBucket {
    private long capacity;
    private long period; // period in unit of nano
    private long numOfTokensPerPeriod; // e.g 100/s rate, add 100 tokens to bucket every 1 s
    private long numOfTokens; // current number of token in bucket
    private Instant lastRefillTime;
    private Instant nextRefillTime;

    public ConcurrentTokenBucket(long capacity, long periodInMillis, long numOfTokensPerPeriod) {
        this.capacity = capacity;
        this.period = periodInMillis;
        this.lastRefillTime = Instant.now();
        this.nextRefillTime = lastRefillTime.plusMillis(periodInMillis);
        this.numOfTokens = capacity; // the bucket is full initially
        this.numOfTokensPerPeriod = numOfTokensPerPeriod;
    }

    synchronized boolean allow() {
        numOfTokens = Math.min(capacity, numOfTokens+refill()); // refill bucket
        // take one token from the bucket if available
        if(numOfTokens > 0) {
            numOfTokens--;
            System.out.println("number of tokens left: " + numOfTokens);
            return true;
        } else return false;
    }

    long refill() {
        Instant now = Instant.now();
        if(now.isBefore(nextRefillTime)) return 0; // return 0 if current time is before next fill time

        long numPeriods = Duration.between(lastRefillTime, now).toMillis()/period;
        lastRefillTime = lastRefillTime.plusMillis(numPeriods*period);
        nextRefillTime = lastRefillTime.plusMillis(period);
        return numPeriods * numOfTokensPerPeriod;
    }

    long getNumOfTokens(){
        return numOfTokens;
    }
}


