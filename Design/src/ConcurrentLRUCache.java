import java.util.LinkedHashMap;
import java.util.Map;

public class ConcurrentLRUCache {

    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public ConcurrentLRUCache(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(16, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return cache.size() > this.size();
            }
        };
    }

    public int get(int key) {
        synchronized (cache) {
            if(cache.containsKey(key)) {
                return cache.get(key);
            }
            return -1;
        }
    }

    public void put(int key, int value) {
        synchronized (cache) {
            cache.put(key, value);
        }
    }
}
