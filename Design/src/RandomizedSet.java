import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> map; //{value, index}
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int idx = map.get(val);
        int tail = list.get(list.size()-1);

        list.set(idx, tail); // move tail number to idx
        list.remove(list.size()-1); // remove val from list
        map.put(tail, idx); // update index of previous tail number in the map
        map.remove(val); // remove val from map, update in front of remove, avoid trouble when removing tail element
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}
