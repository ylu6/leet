import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString {
    // Store char:frequency in a map
    // Store {char:freq} in a maxHeap
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> maxPQ = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());

        for(char c : S.toCharArray()){ // count frequency of char in String S
            int count = 1+map.getOrDefault(c,0);
            if (count > (S.length() + 1) / 2) return "";
            map.put(c, count);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            maxPQ.add(entry);
        }

        Map.Entry<Character, Integer> prv = null, cur = null;
        StringBuilder sb = new StringBuilder();
        // Greedy algm, everytime pick the number with largest freq
        while(!maxPQ.isEmpty()){
            cur = maxPQ.poll();
            sb.append(cur.getKey());
            if(prv != null && prv.getValue() != 0){
                maxPQ.add(prv); // after processing current entry, add prv back into maxPQ if prv.value > 0
            }
            cur.setValue(cur.getValue()-1); // decrease freq of current char by 1
            prv = cur; // assign current entry to prv
        }
        return prv.getValue()!=0 ? "" : sb.toString();
    }
}
