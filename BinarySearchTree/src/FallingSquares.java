import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class FallingSquares {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(Integer.MIN_VALUE, 0);

        int maxH = 0;
        for(int[] b : positions) { // loop through all boxes
            int start = b[0], end = start + b[1]; // start and end position of this box
            Integer key = map.floorKey(start);
            // search for max height in range [start, end)
            int h = 0;
            while(key != null && key < end) {
                h = Math.max(h, map.get(key)); // update local max height
                key = map.higherKey(key);
            }

            Integer tailKey = map.floorKey(end);
            key = map.ceilingKey(start);
            int tailH = map.get(tailKey); // find height of the tail key in range (start, end]
            while(key != null && key < end) { // remove all keys in range (start, end)
                map.remove(key);
                key = map.higherKey(key); // move to next key
            }
            maxH = Math.max(maxH, h+b[1]);
            res.add(maxH); // add this height to result list
            map.put(start, h+b[1]); // put start height of this box
            map.put(end, tailH); // put end height of this box
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][]{{1,2},{2,3},{6,1}};
        FallingSquares sol = new FallingSquares();
        System.out.println(sol.fallingSquares(positions).toString());
    }
}

