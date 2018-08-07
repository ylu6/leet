import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yeqing on 8/16/2017.
 */
public class FrogJump {
    public boolean canCross(int[] stones) {
        // HashMap to store {stone_position : HashSet}
        // HashSet stores all possible step sizes at that stone
        Map<Integer, Set<Integer>> map = new HashMap<>(stones.length);
        for (int stone : stones)
            map.put(stone, new HashSet<>());
        map.get(stones[0]).add(1); // on first stone, can only jump 1 step

        int endPos = stones[stones.length-1];
        for (int stone : stones) {
            Set<Integer> set = map.get(stone);
            for (int step : set) {
                int reach = stone + step;
                if (reach == endPos) return true;
                Set<Integer> nextSet = map.get(reach);
                if (nextSet != null) {
                    nextSet.add(step);
                    nextSet.add(step+1);
                    if (step > 1) nextSet.add(step-1);
                }
            }
        }
        return false;
    }
}
