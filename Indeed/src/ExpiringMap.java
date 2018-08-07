import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ExpiringMap<K,V> {
    // stores key, {value, expiring time}
    Map<K, ValueNode> map;

    public ExpiringMap(){
        map = new ConcurrentHashMap<>();
        cleanupThread();
    }

    public void put(K key, V value, long durationMs) {
        Instant now = Instant.now();
//        System.out.println("exipre at: " + now.plusMillis(durationMs));
        map.put(key, new ValueNode(value, now.plusMillis(durationMs)));
    }

    void cleanupThread() {
        Thread thread = new Thread(){
            @Override
            public void run(){
                while(true) {
                    Instant now = Instant.now();
                    for (K key : map.keySet()) {
                        if (map.get(key).expiringTime.isBefore(now)) {
                            map.remove(key);
                            System.out.println("key expired: " + key);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public V get(K key) {
        if(map.containsKey(key))
            return map.get(key).value;
        return null;
    }

    class ValueNode{
        V value;
        Instant expiringTime;
        public ValueNode(V value, Instant expiringTime){
            this.value = value;
            this.expiringTime = expiringTime;
        }
    }

    public static void main(String[] args) {
        ExpiringMap<String, Integer> emap = new ExpiringMap<>();
        emap.put("a", 1, 1000);
        emap.put("b", 10, 5000);
        System.out.println(emap.get("a") + ", " + emap.get("b"));
        try{
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(emap.get("a") + ", " + emap.get("b"));
    }
}
