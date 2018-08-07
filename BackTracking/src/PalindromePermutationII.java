import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePermutationII {
    /**
     * @param s: the given string
     * @return: all the palindromic permutations (without duplicates) of it
     */
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        List<String> res = new ArrayList<>();
        String mid = "";
        int N = 0;
        for(char c : map.keySet()) {
            if(map.get(c)%2 != 0) {
                if(mid == "") mid = ""+c;
                else return res; // no palindromic permutation could be form
            }
            int count = map.get(c)/2;
            N += count;
            map.put(c, count); // use half of the characters to form left part of palindrome
        }

        List<String> left = permute(map, N);
        for(String str : left) {
            String r = new StringBuilder(str).reverse().toString();
            res.add(str + mid + r);
        }
        return res;
    }

    List<String> permute(Map<Character, Integer> map, int N) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, map, N, sb);
        return res;
    }

    void dfs(List<String> res, Map<Character, Integer> map, int N, StringBuilder sb) {
        if(sb.length() == N) {
            res.add(sb.toString());
            return;
        }
        for(char c : map.keySet()) {
            int count = map.get(c);
            if(count > 0) {
                map.put(c, count-1);
                sb.append(c);
                dfs(res, map, N, sb);
                if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
                map.put(c, count);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePermutationII sol = new PalindromePermutationII();
        System.out.println(sol.generatePalindromes("aabbba").toString());

    }
}
