public class SelfCrossing {
    public boolean isSelfCrossing(int[] x) {
        for(int i = 3; i < x.length; i++) {
            // cross the line 3 steps ahead
            if(x[i-1]<=x[i-3] && x[i]>=x[i-2]) return true;
            // cross the line 4 steps ahead
            if(x[i-1]==x[i-3] && x[i]+x[i-3]>=x[i-2]) return true;
            // cross the line 5 steps ahead
            if(x[i]+x[i-4]>=x[i-2] && x[i-1]+x[i-5]>=x[i-3]) return true;
        }
        return false;
    }
}
