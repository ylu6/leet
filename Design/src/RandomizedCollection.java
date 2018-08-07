import java.util.*;

public class RandomizedCollection {

    Map<Integer, LinkedHashSet<Integer>> map;
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
        list = new ArrayList<Integer>();
        r = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        map.putIfAbsent(val, new LinkedHashSet<Integer>());
        list.add(val);
        map.get(val).add(list.size()-1);
        System.out.println(list.toString());
        return !contains;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        int idx = map.get(val).iterator().next();
        int tail = list.get(list.size()-1); // last number in the list
        map.get(val).remove(idx); // remove index of val from map
        // must remove idx first!! in case of {4,2,4}, we want to remove 4 at idx=0, map contains {4: {0,2}}
        // if update tail idx first, map will contain {4: {0}},
        // then after remove 0 from set, result is wrong
        if(idx != list.size()-1) { // swap the number at idx with last number in the list
            list.set(idx, tail);
            map.get(tail).remove(list.size()-1); // update index of tail in the map
            map.get(tail).add(idx);
        }
        if(map.get(val).isEmpty()) map.remove(val); // remove the LinkedHashSet associated with val if becomes empty
        list.remove(list.size()-1);
        System.out.println(list.toString() + ", " + map.size());
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        rc.insert(4);
        rc.insert(3);
        rc.insert(4);
        rc.insert(2);
        rc.insert(4);
        rc.remove(4);
        rc.remove(4);
        rc.remove(4);
    }
}
