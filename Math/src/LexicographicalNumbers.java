import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 8/31/2017.
 */
public class LexicographicalNumbers {
    /*       1                 2
       10    11 ... 19       20 21 ... 29
    100 101... 109
    This is like preorder traversal of a ten-nary tree, each tree node has ten children, 0, 1, 2 ... 9
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++)    {
            if (i <= n) preorder(res, n, i); // make sure cur is always in range
        }
        return res;
    }

    void preorder(List<Integer> res, int n, int cur) {
        res.add(cur);
        cur *= 10;
        for (int i = 0; i <= 9; i++) {
            if (cur + i > n) return; // early terminate the loop
            preorder(res, n, cur+i);
        }
    }

    public static void main(String[] args) {
        LexicographicalNumbers sol = new LexicographicalNumbers();
        List<Integer> nums = sol.lexicalOrder(111);
        for (int n : nums) System.out.print(n + " ");
    }
}
