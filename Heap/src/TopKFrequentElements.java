import java.util.*;

public class TopKFrequentElements {
    // v1, PriorityQueue<Map.Entry<Integer, Integer>>
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        // <num, freq>
        PriorityQueue<Map.Entry<Integer, Integer>> minPQ = new PriorityQueue<>(
                (a,b)->a.getValue()==b.getValue() ? b.getKey()-a.getKey() : a.getValue() - b.getValue()
        );

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minPQ.add(entry);
            if(minPQ.size() > k) minPQ.poll();
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!minPQ.isEmpty()) {
            res.addFirst(minPQ.poll().getKey());
        }
        return res;
    }

    // v2, bucket sort
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // {num:freq}
        for(int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        List<Integer>[] buckects = new List[nums.length+1]; // max freq is len
        for(int num : map.keySet()) {
            int f = map.get(num); // get freq of num
            if(buckects[f] == null) buckects[f] = new ArrayList<>();
            buckects[f].add(num); // add num into the corresponding freq bucket
        }

        List<Integer> res = new ArrayList<>();
        for(int i = buckects.length-1; i >= 0; i--) {
            if(buckects[i] == null) continue;
            int N = k-res.size();
            if(buckects[i].size() < N)
                res.addAll(buckects[i]);
            else{
                res.addAll(buckects[i].size()-N, buckects[i]);
                return res;
            }
            System.out.println(res.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        TopKFrequentElements sol = new TopKFrequentElements();
        System.out.println(sol.topKFrequent2(nums, 2).toString());
    }
}
