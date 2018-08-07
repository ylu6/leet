import java.util.*;

public class AllOne {

    Bucket head, tail;
    HashMap<String, Bucket> map;

    /** Initialize your data structure here. */
    public AllOne() {
        head = new Bucket(-1);
        tail = new Bucket(-1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<String, Bucket>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)) {
            if(head.next.count == 1) { // key not exists
                head.next.keys.add(key);
            } else {
                Bucket bucket = new Bucket(1, key);
                addBucket(bucket, head);
            }
            map.put(key, head.next);
        } else { // key exists
            Bucket cur = map.get(key);
            cur.keys.remove(key);
            if(cur.count+1 == cur.next.count) { // count+1 exists in the list, move key to next bucket
                cur.next.keys.add(key);
            } else { // count+1 doesn't exist, create new bucket, move key to it
                Bucket bucket = new Bucket(cur.count+1, key);
                addBucket(bucket, cur);
            }
            map.put(key, cur.next);
            if(cur.keys.isEmpty()) removeBucket(cur);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) return;

        Bucket cur = map.get(key);
        cur.keys.remove(key);
        if(cur.count == 1) {
            map.remove(key);
        } else if(cur.count-1 == cur.pre.count) {
            cur.pre.keys.add(key);
        } else {
            Bucket bucket = new Bucket(cur.count-1, key);
            addBucket(bucket, cur.pre);
        }
        if(cur.count != 1) map.put(key, cur.pre);
        if(cur.keys.isEmpty()) removeBucket(cur);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.pre == head ? "" : tail.pre.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    void addBucket(Bucket bucket, Bucket pre) {
        Bucket nxt = pre.next;
        pre.next = bucket;
        bucket.pre = pre;
        bucket.next = nxt;
        nxt.pre = bucket;
    }

    void removeBucket(Bucket bucket) {
        bucket.pre.next = bucket.next;
        bucket.next.pre = bucket.pre;
    }
}

class Bucket {
    Integer count;
    Bucket pre, next;
    Set<String> keys;

    public Bucket(int count, String key) {
        this.count = count;
        keys = new LinkedHashSet<>();
        keys.add(key);
    }
    public Bucket(int count) {
        this.count = count;
        keys = new LinkedHashSet<>();
    }
}

