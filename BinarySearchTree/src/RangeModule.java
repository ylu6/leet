import java.util.TreeMap;

public class RangeModule {
    // store all non-overlapping ranges [left, right), left as key, right as value
    TreeMap<Integer, Integer> segments;

    public RangeModule() {
        segments = new TreeMap<>();
    }
    // addRange(int left, int right) Adds the half-open interval [left, right)
    public void addRange(int left, int right) {
        Integer start = segments.floorKey(left), end = segments.floorKey(right);
        int l, r = right;
        if(start == null || segments.get(start) < left) start = left; // find start value
        end = end != null && segments.get(end) > right ? segments.get(end): right; // find end value

        Integer key = segments.higherKey(start); // remove all keys in range (start, end]
        while(key != null && key <= right) {
            segments.remove(key);
            key = segments.higherKey(key);
        }
        segments.put(start, end); // add the new interval
    }

    // Returns true if and only if every real number in the interval [left, right) is currently being tracked.
    public boolean queryRange(int left, int right) {
        Integer key = segments.floorKey(left);
        return key != null && segments.get(key) >= right;
    }

    // Stops tracking every real number currently being tracked in the interval [left, right)
    public void removeRange(int left, int right) {
        Integer key = segments.lowerKey(left);
        // update segments if overlap with left side of interval
        if(key!=null) {
            if (segments.get(key) > right) segments.put(right, segments.get(key));
            if (segments.get(key) > left) segments.put(key, left);
        }
        // update segments if overlap from right side
        Integer tailKey = segments.lowerKey(right);
        if(tailKey!=null && segments.get(tailKey) > right) {
            segments.put(right, segments.get(tailKey));
            segments.remove(tailKey);
        }

        key = segments.ceilingKey(left);
        while(key!=null && key < right) {
            if(segments.get(key) <= right) segments.remove(key);
            key = segments.higherKey(key);
        }
    }

    private void printRange() {
        for(Integer key : segments.keySet())
            System.out.println(key + ":" + segments.get(key));
    }
    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(5,8);
        rangeModule.queryRange(3,4);
        rangeModule.removeRange(5,6);
        rangeModule.removeRange(3,6);
        rangeModule.printRange();

        rangeModule.addRange(1,3);

        rangeModule.queryRange(2,3);
        rangeModule.printRange();

        rangeModule.addRange(4,8);
        rangeModule.printRange();
        rangeModule.removeRange(14,16);
        rangeModule.printRange();
    }
}
