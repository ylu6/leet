import java.util.*;

public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(toString(board));
        int count = 0;
        while(!queue.isEmpty()) {
            for(int sz = queue.size(); sz > 0; sz--) {
                String curr = queue.poll();
                visited.add(curr);
                if(curr.equals("123450")) return count;
                for(String neighbor : getNeighbors(curr)) {
                    if(!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            count++;
        }
        return -1;
    }
    List<String> getNeighbors(String s) {
        int[] offsets = {-3,-1,1,3};
        int idx = s.indexOf('0');
        System.out.println(idx);
        List<String> res = new ArrayList<>();
        for(int offset : offsets) {
            int k = idx + offset;
            if(k < 0 || k > 5 || (idx == 3 && offset == -1) || (idx == 2 && offset == 1)) continue;
            char[] chars = s.toCharArray();
            chars[idx] = chars[k];
            chars[k] = '0';
            res.add(new String(chars));
        }
        return res;
    }

    String toString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int[] row : board) {
            for(int cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] board = {{1,2,3},{5,4,0}};
        SlidingPuzzle sol = new SlidingPuzzle();
        System.out.println(sol.slidingPuzzle(board));
    }
}
