import java.util.*;

public class PyramidTransitionMatrix {
    Map<String, Boolean> memo;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        memo = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String s : allowed) {
            String key = s.substring(0,2);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s.substring(2));
        }

        return dfs(bottom, map);
    }

    boolean dfs(String bottom, Map<String, List<String>> allowed) {
        if(bottom.length()==1) return true;
        if(memo.containsKey(bottom)) return memo.get(bottom);
        StringBuilder sb = new StringBuilder();
        memo.put(bottom, backtrack(bottom, allowed, 0, sb));
        return memo.get(bottom);
    }

    boolean backtrack(String bottom, Map<String, List<String>> allowed, int pos, StringBuilder sb) {
        boolean res = false;
        String key = bottom.substring(pos, pos+2);
        if(!allowed.containsKey(key)) return res;
        for(int i = 0; i < allowed.get(key).size(); i++) {
            sb.append(allowed.get(key).get(i));
            if(pos==bottom.length()-2) res = dfs(sb.toString(), allowed);
            else res = backtrack(bottom, allowed, pos+1, sb);
            if(res) return true;
            if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
        }
        return false;
    }
}
