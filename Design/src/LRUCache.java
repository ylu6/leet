import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yeqing on 7/10/2017.
 */

// extend Java LinkedHashMap, this class is used for LRU cache design
class LRUHashMap<K, V> extends LinkedHashMap<K, V> {
    int capacity;

    public LRUHashMap(int capacity) {
        // LinkedHashMap default accessOrder is false, which means using insertion order
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        if (this.size() > capacity) return true;
        return false;
    }
    // also can be done without override the removeEldestEntry method
    // instead, when cache is full, use the iterator and get the first entry which is the eldest
    // then call the remove method to delete the eldest
}

public class LRUCache {
    Map<Integer, Integer> map;
    int capacity;
    public LRUCache(int capacity) {
//        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true);
//        map = new LRUHashMap<Integer, Integer>(capacity);
        // or override during object creation, without extend the LinkedHashMap class
        map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return this.size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key))
            return map.get(key);
        return -1;
    }

    public void put(int key, int value) {
        map.put(key, value);
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);
        lruCache.put(1,10);
        lruCache.put(2,20);
        lruCache.put(3,30);
        lruCache.put(4,40);
        lruCache.put(5,50);
        System.out.println(lruCache.get(1));
    }
}
