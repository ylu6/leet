import java.util.*;

/**
 * Created by yeqing on 12/9/2017.
 */
public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        // heightSet, store heights of buildings (Li) in a TreeSet
        TreeSet<Integer> heightSet = new TreeSet<>();
        // coordMinPQ, store {Ri, Hi} in a MinPQ, ordered by coordinate Ri
        PriorityQueue<int[]> coordMinPQ = new PriorityQueue<>(
                (a,b)-> a[0] > b[0] ? 1 : (a[0] == b[0] ? 0 : -1));

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            // when a new building added, remove all buildings with Ri <= Li of current building, and update results
            while (!coordMinPQ.isEmpty() && b[0] >= coordMinPQ.peek()[0]) {
                int[] toBeRemoved = coordMinPQ.poll();
                heightSet.remove(toBeRemoved[1]);
                if (b[0] != toBeRemoved[0])
                    addCoord(res, new int[]{toBeRemoved[0], heightSet.isEmpty() ? 0 : heightSet.last()});
            }
            // add current building to heightMaxPQ, update res
            heightSet.add(b[2]);
            if (i == buildings.length-1 || b[0] != buildings[i+1][0])
                addCoord(res, new int[]{b[0], heightSet.last()});
            coordMinPQ.add(new int[]{b[1], b[2]});
        }
        // process remaining coord stored in coordMinPQ, add results into res
        while (!coordMinPQ.isEmpty()) {
            int[] toBeRemoved = coordMinPQ.poll();
            heightSet.remove(toBeRemoved[1]);
            addCoord(res, new int[]{toBeRemoved[0], heightSet.isEmpty() ? 0 : heightSet.last()});
        }
        return res;
    }
    private void addCoord(List<int[]> res, int[] coord) {
        if (res.isEmpty()) res.add(coord);
        else {
            int[] tail = res.get(res.size()-1);
            if (tail[1] == coord[1]) return; // newly inserted coord has same height as previous one, do nothing
            else if (tail[0] == coord[0]) { // newly inserted coord has same x value but different height
                if (coord[1] == 0) tail[1] = 0; // newly inserted has 0 height, e.g. insert [3,5], [3,0]
                                                // in this case, more than one building has same right edge, and it is the end of a building cluster, height should be 0
                else tail[1] = Math.max(tail[1], coord[1]); // pick the larger height and reset the height of current tail
            }
            else res.add(coord);
        }
    }

    public static void main(String[] args) {
        int[][] buildings = {{0,2,3},{2,5,3}};
        TheSkylineProblem sol = new TheSkylineProblem();

        for (int[] c : sol.getSkyline(buildings)){
            System.out.println(c[0] + " " + c[1]);
        }
    }
}
