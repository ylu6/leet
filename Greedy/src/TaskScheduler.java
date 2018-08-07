
import java.util.*;

/**
 * Created by yeqing on 8/25/2017.
 */
/*
    Greedy algorithm, first count frequency of each task, e,g AAAABBCCDD, A:4, B:2, C:2, D:2
    when arrange k-distance apart, first round pick top-k frequent tasks, if k=3
    round 1, ABC, left: 3A, 1B, 1C, 2D
    round 2, ABCADB, left 2A, 0B, 1C, 1D
    round 3, ABCADBACD, left 1A, 0B, 0C, 0D
    round 4, ABCADBACDA
    done
    if in certain round, type of tasks (t) left less than k, then need to idle k-t+1 intervals
    e.g AA, k=2
    A_idle_idle (2-1+1 idle intervals)
*/
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> maxPQ = new PriorityQueue<>(
                (a,b) -> a.getValue() == b.getValue() ?
                        b.getKey()-a.getKey() : b.getValue() - a.getValue());

        // count task frequency
        for (char c : tasks)    { map.put(c, 1 + map.getOrDefault(c, 0)); }
        // add all entries into the maxPQ
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            maxPQ.add(entry);
        }

        while (!maxPQ.isEmpty()) {
            int k = n+1;
            Queue<Map.Entry<Character, Integer>> tmpQueue = new LinkedList<>();
            while (k > 0 && !maxPQ.isEmpty()) {
                Map.Entry<Character, Integer> entry = maxPQ.poll(); // poll the most frequent task
                k--;
                entry.setValue(entry.getValue() - 1); // add it to task queue, therefore decrease count by 1
                if (entry.getValue() != 0)
                    tmpQueue.add(entry); // this task is not used up, later add back to maxPQ
            }
            maxPQ.addAll(tmpQueue);
            res += maxPQ.isEmpty() ? n+1-k : n+1; //
        }
        return res;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        TaskScheduler sol = new TaskScheduler();
        System.out.println(sol.leastInterval(tasks, 2));
    }
}
