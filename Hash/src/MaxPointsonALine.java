import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeqing on 12/29/2017.
 */
public class MaxPointsonALine {
    public int maxPoints(Point[] points) {
        int globalMax = 0, localMax = 0;
        for (int i = 0; i < points.length; i++) {
            Map<List<Integer>, Integer> map = new HashMap<>();
            int duplicates = 0;
            for (int j = i+1; j < points.length; j++) {
                int dx = points[i].x - points[j].x;
                int dy = points[i].y - points[j].y;
                if (dx==0 && dy==0) {
                    duplicates++;
                    continue;
                }
                int GCD = gcd(dx, dy);
                if (GCD!=0) {
                    dx = dx/GCD;
                    dy = dy/GCD;
                }
                List<Integer> temp = new ArrayList<>();
                temp.add(dx);
                temp.add(dy);
                map.put(temp, 1+map.getOrDefault(temp, 0));
                localMax = Math.max(localMax, map.get(temp));
            }
            globalMax = Math.max(globalMax, localMax + 1 + duplicates);
        }
        return globalMax;
    }
    private int gcd(int x, int y) {
        if(y == 0) return x;
        return gcd(y, x%y);
    }

    class Point {
       int x;
       int y;
       Point() { x = 0; y = 0; }
       Point(int a, int b) { x = a; y = b; }
    }
    public static void main(String[] args) {
        MaxPointsonALine sol = new MaxPointsonALine();
        System.out.println(sol.gcd(2,2));
    }
}
