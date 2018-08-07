import java.util.HashMap;
import java.util.Map;

/*
Design a logger system that receive stream of messages along with its timestamps,
each message should be printed if and only if it is not printed in the last 10 seconds.
Given a message and a timestamp (in seconds granularity),
return true if the message should be printed in the given timestamp, otherwise returns false.
It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter {

    Map<String, Integer> map;

    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message) || timestamp-map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        LoggerRateLimiter logger = new LoggerRateLimiter();
        System.out.println(logger.shouldPrintMessage(1, "foo"));
        System.out.println(logger.shouldPrintMessage(2, "bar"));
        System.out.println(logger.shouldPrintMessage(3, "foo"));
        System.out.println(logger.shouldPrintMessage(8, "bar"));
        System.out.println(logger.shouldPrintMessage(10, "foo"));
        System.out.println(logger.shouldPrintMessage(11, "foo"));
    }
}
