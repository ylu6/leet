import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by yeqing on 12/8/2017.
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int n : asteroids) {
            if (n > 0) // moving right
                stack.addFirst(n);
            else { // moving left
                if (stack.isEmpty()) stack.addFirst(n);
                else {
                    while (!stack.isEmpty() && stack.peekFirst() > 0 && -1*n > stack.peekFirst())
                        stack.pollFirst();
                    if (!stack.isEmpty() && stack.peekFirst() == -1*n)
                        stack.pollFirst();
                    else if (stack.isEmpty() || stack.peekFirst() < 0)
                        stack.addFirst(n);
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size()-1; i >= 0; i--)
            res[i] = stack.pollFirst();

        return res;
    }

    // v2, use only LinkedList
    public int[] asteroidCollision2(int[] asteroids) {
        LinkedList<Integer> res = new LinkedList<>();
        for(int i = 0; i < asteroids.length; i++) {
            if(asteroids[i] > 0 || res.isEmpty()) {
                res.add(asteroids[i]);
            } else {
                if(res.getLast() + asteroids[i] < 0) {
                    res.pollLast();
                    i--;
                }
            }
        }

        return res.stream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        int[] nums = {-2,-1,1,2};
        AsteroidCollision sol = new AsteroidCollision();
        for (int n : sol.asteroidCollision(nums))
            System.out.print(n + " ");
    }
}
