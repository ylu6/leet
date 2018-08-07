import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 6/1/2017.
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        boolean[] used = new boolean[(int) Math.pow(2,n)];
        List<Integer> res = new ArrayList<>();
        grayCode(used, res, n, 0);
        return res;
    }

    private boolean grayCode(boolean[] used, List<Integer> res, int n, int num) {
        if (res.size() == used.length) return true;
        if (used[num]) {
            return false;
        }

        used[num] = true;
        res.add(num);
        for (int i = 0; i < n; i++) {
            int nextNum = num^(1<<i);
            boolean found = grayCode(used, res, n, nextNum);
            if (found) return true;
        }
        res.remove(res.size()-1);
        used[num] = false;
        return false;
    }

    // version 2: directly generate gray codes
    // e.g. list originaly contains 00, 01 , retrieve data from tail to end, which is (01, 00), apply | 1<<1, leads to (11, 10)
    // add back to list, we have 00, 01, 11, 10, which is the gray codes
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);

        for(int i = 0; i < n; i++) {
            for(int j = res.size()-1; j >= 0; j--) {
                res.add(res.get(j) | 1 << i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        GrayCode g = new GrayCode();
        for (Integer s : g.grayCode(3)) {
            System.out.println(s);
        }
    }
}
