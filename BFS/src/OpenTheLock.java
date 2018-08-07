import java.util.*;

/**
 * Created by yeqing on 12/28/2017.
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> seen = new HashSet<>();
        Set<String> deadSet = new HashSet<>();
        for(String deadend : deadends) deadSet.add(deadend);
        Queue<String> queue = new ArrayDeque<>();
        queue.add("0000");
        int turns = 0;
        String front;
        while(!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                front = queue.poll();
                if(front.equals(target)) return turns;
                if(seen.contains(front)) continue;
                for(String state : getNeighbours(front)) {
                    if (!seen.contains(state) && !deadSet.contains(state)) queue.add(state);
                }
                seen.add(front);
            }
            turns++;
        }
        return -1;
    }
    private List<String> getNeighbours(String s) {
        List<String> res = new ArrayList<>();
        char[] states = s.toCharArray(), temp;
        for (int i = 0; i < 4; i++) {
            char original = states[i];
            states[i] = original == '0' ? '9' : (char) (original-1); // current code -1
            res.add(new String(states));
            states[i] = original == '9' ? '0' : (char) (original+1); // current code +1
            res.add(new String(states));
            states[i] = original; // reset the current code to original state
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "0269";
        OpenTheLock sol = new OpenTheLock();
        for (String str : sol.getNeighbours(s))
            System.out.println(str);
    }
}
