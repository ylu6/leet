import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        for(int lo = 0; lo < S.length(); ) {
            int hi = lo;
            while(hi+1 < S.length() && S.charAt(hi+1)==S.charAt(lo)) hi++;
            if(hi-lo >= 2) res.add(Arrays.asList(new Integer[]{lo, hi}));
            lo = hi + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        String S = "abbxxxxzzy";
        PositionsOfLargeGroups sol = new PositionsOfLargeGroups();
        System.out.println(sol.largeGroupPositions(S).toString());
    }
}
