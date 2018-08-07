import java.util.*;

/**
 * Created by yeqing on 1/15/2018.
 */
public class MinimumGeneticMutation {
    // one-end BFS search
    public int minMutation(String start, String end, String[] bank) {
        char[] chars = {'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet<>();
        for(String b : bank) set.add(b);
        Queue<String> frontier = new ArrayDeque<>();
        int count = 0;
        frontier.add(start);
        while(!frontier.isEmpty()) {
            int len = frontier.size();
            for(int i = 0; i < len; i++) {
                String cur = frontier.poll();
                if(cur.equals(end))
                    return count;
                for(String neighbour : getNeighbour(cur, set, chars))
                    frontier.add(neighbour);
            }
            count++;
        }
        return -1;
    }
    // two-end BFS search
    public int minMutation2(String start, String end, String[] bank) {
        char[] chars = {'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet<>();
        for(String b : bank) set.add(b);
        if(!set.contains(end)) return -1;
        Queue<String> front = new ArrayDeque<>();
        Queue<String> back = new ArrayDeque<>();
        int count = 0;
        front.add(start);
        back.add(end);
        while(!front.isEmpty() && !back.isEmpty()) {
            if(back.size() < front.size()) {
                Queue<String> temp = front;
                front = back;
                back = temp;
            }
            int len = front.size();
            for(int i = 0; i < len; i++) {
                String cur = front.poll();

                char[] charArray = cur.toCharArray();
                for ( int j = 0; j < 8; j++) {
                    char temp = charArray[j];
                    for (char c : chars) {
                        if(temp == c) continue;
                        charArray[j] = c;
                        String s = new String(charArray);
                        if(back.contains(s))
                            return count+1;
                        else {
                            if(set.contains(s)) {
                                front.add(s);
                                set.remove(s);
                            }
                        }
                    }
                    charArray[j] = temp;
                }
            }
            count++;
        }
        return -1;
    }
    List<String> getNeighbour(String str, Set<String> set, char[] chars) {
        List<String> res = new ArrayList<>();
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            char temp = charArray[i];
            for(char c : chars) {
                if(temp == c) continue;
                charArray[i] = c;
                String s = new String(charArray);
                if(set.contains(s)) {
                    res.add(s);
                    set.remove(s);
                }
            }
            charArray[i] = temp;
        }
        return res;
    }
    public static void main(String[] args) {
        MinimumGeneticMutation sol = new MinimumGeneticMutation();
        char[] chars = {'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet<>();
        set.add("AACCGGTA");
//        System.out.println(sol.getNeighbour("AACCGGTT", set, chars).size());
        for (String s : sol.getNeighbour("AACCGGTT", set, chars)) {
            System.out.println(s);
        }
    }
}
