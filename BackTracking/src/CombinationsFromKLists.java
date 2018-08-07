import java.util.*;

public class CombinationsFromKLists {
    // v1, backtracking, recursive solution
    List<List<Integer>> combinationsFromKLists(List<List<Integer>> lists) {
        List<List<Integer>> res = new ArrayList<>();
        if(lists == null || lists.isEmpty()) return res;
        dfs(res, lists, new ArrayList<>(), 0);
        return res;
    }

    void dfs(List<List<Integer>> res, List<List<Integer>> lists, List<Integer> temp, int pos) {
        if(pos == lists.size()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int n : lists.get(pos)) {
            temp.add(n);
            dfs(res, lists, temp, pos+1);
            if(temp.size() > 0) temp.remove(temp.size()-1);
        }
    }

    // v2, BFS
    List<List<Integer>> combinationsFromKLists2(List<List<Integer>> lists) {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<>());
        for(int i = 0; i < lists.size(); i++) {
            // level order BFS traversal
            for(int j = 0, size = queue.size(); j < size; j++) {
                List cur = queue.poll();
                for(int n : lists.get(i)) {
                    List temp = new ArrayList(cur);
                    temp.add(n);
                    queue.add(temp);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>(queue);
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(new Integer[]{1,2,3}));
        lists.add(Arrays.asList(new Integer[]{4,5}));
        lists.add(Arrays.asList(new Integer[]{6,7}));

        CombinationsFromKLists sol = new CombinationsFromKLists();
        System.out.println(sol.combinationsFromKLists(lists).toString());
        System.out.println(sol.combinationsFromKLists2(lists).toString());
    }
}
