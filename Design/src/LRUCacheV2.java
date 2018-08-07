import java.util.HashMap;
import java.util.Map;

public class LRUCacheV2 {
    int capacity;
    Node head, tail;
    Map<Integer, Node> map;

    public LRUCacheV2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node cur = map.get(key);
            removeNode(cur);
            addNode(cur, head); // cur becomes the first Node after head, which is most recent
            return map.get(key).val;
        }
        else return -1;
    }

    public void put(int key, int value) {
        Node cur;
        if(!map.containsKey(key)) {
            cur = new Node(key, value);
            map.put(key, cur);
            System.out.println(map.size());
        } else {
            cur = map.get(key);
            cur.val = value;
            removeNode(cur);
        }
        addNode(cur, head);
        if(map.size() > capacity) {
            int keyToRemove = tail.pre.key;
            System.out.println();
            map.remove(keyToRemove);
            removeNode(tail.pre);
        }
    }

    class Node{
        Node pre, next;
        int key, val;
        public Node(){
        }
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    void addNode(Node node, Node pre) {
        Node nxt = pre.next;
        pre.next = node;
        node.pre = pre;
        node.next = nxt;
        nxt.pre = node;
    }
    void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        LRUCacheV2 lruCache = new LRUCacheV2(4);
        lruCache.put(1,10);
        lruCache.put(2,20);
        lruCache.put(3,30);
        lruCache.put(4,40);
        lruCache.put(5,50);
        System.out.println(lruCache.get(1));
    }
}
