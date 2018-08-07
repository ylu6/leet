import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game24 {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        double eps = 0.0001;
        for(int n : nums) list.add((double)n);
        return backtrack(list, eps);
    }

    boolean backtrack(List<Double> list, double eps) {
        if(list.size()==1) return Math.abs(24-list.get(0)) < eps;

        for(int i = 0; i < list.size()-1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                double vi = list.get(i), vj = list.get(j);
                list.remove(j);
                list.remove(i);
                List<Double> next = new ArrayList<>();
                next.addAll(Arrays.asList(vi+vj, vi*vj, vi-vj, vj-vi));
                if(vi>eps) next.add(vj/vi);
                if(vj>eps) next.add(vi/vj);

                for(double val : next) { // try all possible values from vi,vj with remaining numbers in the list
                    list.add(val);
                    if(backtrack(list, eps)) return true; // found a possible solution
                    list.remove(list.size()-1); // not found, backtrack
                }
                // all combination of vi,vj failed to reach 24
                list.add(i, vi);
                list.add(j, vj);
            }
        }
        return false;
    }

    // use array instead of List, recursive
    public boolean judgePoint24_v2(int[] nums) {
        double[] vals = new double[]{nums[0], nums[1], nums[2], nums[3]};
        double eps = 0.0001;
        return helper(vals, eps);
    }

    boolean helper(double[] vals, double eps) {
        if(vals.length==1) return Math.abs(vals[0]-24) < eps;

        for(int i = 0; i < vals.length-1; i++) {
            for(int j = i+1; j < vals.length; j++) {
                double[] next = new double[vals.length-1];
                List<Double> temp = new ArrayList<>();
                temp.addAll(Arrays.asList(vals[i]+vals[j], vals[i]-vals[j], vals[j]-vals[i], vals[i]*vals[j]));
                if(vals[i] > eps) temp.add(vals[j]/vals[i]);
                if(vals[j] > eps) temp.add(vals[i]/vals[j]);

                for(int k = 0, l = 0; k < vals.length; k++) {
                    if(k!=i && k!=j) next[l++] = vals[k];
                }

                for(double d : temp) {
                    next[next.length-1] = d;
                    if(helper(next, eps)) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Game24 sol = new Game24();
        int[] nums = {1,5,9,1};
        System.out.println(sol.judgePoint24_v2(nums));
    }
}
