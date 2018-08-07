import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by yeqing on 7/10/2017.
 */
public class LFUCache {
    // several things need to be store in the Cache
    // key: value
    // key: freq
    // freq: group of keys with same freq
    // because both get and put need to be O(1), we can store all those info in HashMap
    // when removing key from LeastFrequent set, we need to remove the least recent one
    // therefore we can use a LinkedHashSet to store group of keys with same freq
    private final int CAPACITY;
    private Map<Integer, Integer> map; // store key:value pair
    private Map<Integer, Integer> counts; // store key: frequency pair
    private Map<Integer, LinkedHashSet<Integer>> freqMap; // store {freq: set_of_keys_with_same_freq}
    int min;

    public LFUCache(int capacity) {
        this.CAPACITY = capacity;
        map = new HashMap<>();
        counts = new HashMap<>();
        freqMap = new HashMap<>();
        freqMap.put(1, new LinkedHashSet<>());
        min = -1;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1; // key not in Cache
        int count = counts.get(key); // read freq of this key
        counts.put(key, count+1); // update counts map
        freqMap.get(count).remove(key); // remove this key from original freqMap
        if(count==min && freqMap.get(count).isEmpty()) min++; // if the LinkedHashSet becomes empty and it is LF, increase min
        freqMap.putIfAbsent(count+1, new LinkedHashSet<>());
        freqMap.get(count+1).add(key); // add this key to freqMap, now the count becomes count+1
        return map.get(key);
    }

    public void put(int key, int value) {
        if (CAPACITY <= 0) return;
        if (map.containsKey(key)) {
            map.put(key, value);
            get(key); // update count
            return;
        }
        if (map.size()>=CAPACITY) { // Cache is full, evict LF element from Cache
            int LFKey = freqMap.get(min).iterator().next();
            freqMap.get(min).remove(LFKey);
            map.remove(LFKey);
            counts.remove(LFKey);
        }
        map.put(key, value);
        min=1;
        counts.put(key, 1);
        freqMap.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache c = new LFUCache(2);
        c.put(1,1);
        c.put(2,2);
    }
}
