import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MILLIS;

public class RateLimiter {
    Map<Integer, TokenBucket> map;

    public RateLimiter() {
        map = new HashMap<>();
    };
    boolean isAllowed(int id) {
        map.putIfAbsent(id, new TokenBucket(3, 1000, 3));
        return map.get(id).allow();
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
    }
}

class TokenBucket {
    private long capacity;
    private long period; // period in unit of nano
    private long numOfTokensPerPeriod; // e.g 100/s rate, add 100 tokens to bucket every 1 s
    private long numOfTokens; // current number of token in bucket
    private Instant lastRefillTime;
    private Instant nextRefillTime;

    public TokenBucket(long capacity, long periodInMillis, long numOfTokensPerPeriod) {
        this.capacity = capacity;
        this.period = periodInMillis;
        this.lastRefillTime = Instant.now();
        this.nextRefillTime = lastRefillTime.plusMillis(periodInMillis);
        this.numOfTokens = capacity; // the bucket is full initially
        this.numOfTokensPerPeriod = numOfTokensPerPeriod;
    }

    boolean allow() {
        numOfTokens = Math.min(capacity, numOfTokens+refill()); // refill bucket
        // take one token from the bucket if available
        if(numOfTokens > 0) {
            numOfTokens--;
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
}