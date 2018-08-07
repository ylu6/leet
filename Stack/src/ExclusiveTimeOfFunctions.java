import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by yeqing on 11/2/2017.
 */
public class ExclusiveTimeOfFunctions {
    // log format:  function_id:start_or_end:timestamp
    // break the whole time into small intervals according to the log timeStamp
    // use a stack to store which id each interval belongs to
    // if start: peek previous ID, add curTime-preTime to res[preID],push curID to stack, preTime=logTimeStamp
    // if end: pop out previous ID, add curTime-preTime+1 to res[preID], preTime=logTimeStamp+1
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        int prvTime = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (String str : logs) {
            String[] log = str.split(":");
            int timeStamp = Integer.parseInt(log[2]);

            if (log[1].equals("start")) { // start
                if (!stack.isEmpty()) {
                    res[stack.peekFirst()] += timeStamp - prvTime;
                    prvTime = timeStamp;
                }
                stack.addFirst(Integer.parseInt(log[0]));
            } else { // end
                res[stack.pollFirst()] += timeStamp - prvTime + 1;
                prvTime = timeStamp + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] logs = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        ExclusiveTimeOfFunctions sol = new ExclusiveTimeOfFunctions();
        int[] res = sol.exclusiveTime(2, Arrays.asList(logs));
//        for (int n : res)
//            System.out.println(n);
    }
}
