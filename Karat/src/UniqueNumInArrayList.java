import java.util.ArrayList;
import java.util.Arrays;

public class UniqueNumInArrayList {

    int uniqueNum(ArrayList<Integer> list) {
        if(list.size() < 2) return list.get(0);
        int n1 = list.get(0), n2 = 0;
        int cnt1 = 1, cnt2 = 0;

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) == n1) cnt1++;
            else {
                n2 = list.get(i);
                cnt2++;
            }
            if(cnt1 > 1 && cnt2 == 1) return n2;
            if(cnt2 > 1 && cnt1 == 1) return n1;
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] arr = {1, 1, 1, 2, 1, 1};
        for(int n : arr) list.add(n);
        UniqueNumInArrayList sol = new UniqueNumInArrayList();
        System.out.println(sol.uniqueNum(list));
    }
}
