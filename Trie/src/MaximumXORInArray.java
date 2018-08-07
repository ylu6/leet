/**
 * Created by yeqing on 7/3/2017.
 * 421. Maximum XOR of Two Numbers in an Array
 */

public class MaximumXORInArray {
    class TrieNode {
        TrieNode[] next;
        boolean isEndNode;
        TrieNode(){
            next = new TrieNode[2];
        }
    }
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();         // root is the head of the Trie tree
        int res = 0, tempMax = 0, mask = 1, bit;

        for (int n : nums) {
            TrieNode curr1 = root, curr2 = root;    // curr1 loop through the old Trie, curr2 insert the new element
            tempMax = 0;
//            StringBuilder sb = new StringBuilder();
            for (int i =30; i >= 0; i--) {
                bit = (n>>i)&1;
//                sb.append(bit).append(" ");
                tempMax = (tempMax << 1) + (curr1.next[bit^1]==null ? 0 : 1);
                if (curr2.next[bit] == null)
                    curr2.next[bit] = new TrieNode();
                curr2 = curr2.next[bit];
                curr1 = curr1.next[bit^1]==null ? curr1.next[bit] : curr1.next[bit^1];
            }
//            System.out.println(sb.toString());
            res = Math.max(tempMax, res);
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumXORInArray sol = new MaximumXORInArray();
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(sol.findMaximumXOR(nums));
    }
}
