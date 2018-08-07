import java.util.ArrayList;
import java.util.List;

public class Unrolledlinkedlist {
    Node head = new Node();
    int totalLen = 0;

    // index starting from 0
    char get(int index) {
        if(index < 0 || index >= totalLen || totalLen == 0) {
            return ' '; // should clarify this behavior
        }
        Node cur = head;
        while(index >= cur.len) {
            index -= cur.len;
            cur = cur.next;
        }
        return cur.chars[index];
    }

    // insert, first search the list to find the right node for insertion
    // 1. if the insertion node is null, create a new node
    // 2. insertion node is full, create a new node and move one char there
    // 3. move chars in range [index, len) 1 step towards tail. then insert ch into index
    void insert(char ch, int index) {
        if(index > totalLen) return; // insertion position is in range [0, totalLen]
        Node prv = null, cur = head;
        // there is 5 possible insertion index in a node, 0,1,2,3,4
        // also the insertion index cannot be larger than cur.len
        while(cur != null && (index > 4 || index > cur.len)) {
            index -= cur.len;
            prv = cur;
            cur = cur.next;
        }
        if(cur == null) {
            prv.next = new Node();
            prv.next.len = 1;
            prv.next.chars[0] = ch;
        } else {
            if (cur.len == 5) { // current node is full, create a new node, move one char there
                // create a new node and connect
                Node nxt = cur.next;
                cur.next = new Node();
                cur.next.next = nxt;
                // move last char from cur node to the new node
                cur.next.chars[0] = cur.chars[4];
                cur.next.len = 1; // set len of the new node to 1

                cur.len--; // decrease len of cur node by 1
            } // now the cur node must have at least one empty spot for insertion
            // move chars right by 1 pos in range [index, cur.len)
            for (int i = cur.len; i > index; i--) cur.chars[i] = cur.chars[i - 1];
            cur.chars[index] = ch; // write ch to index
            cur.len++; // increase len of cur node by 1
        }
        totalLen++;
    }


    // follow up
    public void delete(int index) {

    }

    public String toString() {
        StringBuilder sb= new StringBuilder();
        Node cur = head;
        while(cur != null) {
            for(int i = 0; i < cur.len; i++) sb.append(cur.chars[i]).append(' ');
            sb.deleteCharAt(sb.length()-1);
            sb.append(", ");
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Unrolledlinkedlist list = new Unrolledlinkedlist();

        Node n1 = new Node(); //a b
        Node n2 = new Node(); //b
        Node n3 = new Node(); //a b c d e

        n1.chars[0] = 'a';
        n1.chars[1] = 'b';
        n2.chars[0] = 'b';
        n3.chars[0] = 'a';
        n3.chars[1] = 'b';
        n3.chars[2] = 'c';
        n3.chars[3] = 'd';
        n3.chars[4] = 'e';

        n1.next = n2;
        n2.next = n3;
        n1.len = 2;
        n2.len = 1;
        n3.len = 5;
        list.head = n1;
        list.totalLen = 8;
        System.out.println(list.toString());
//        for(int i = 0; i < 9; i++) System.out.println(list.get(i));
        list.insert('z',8);
        System.out.println(list.toString());
        System.out.println(list.totalLen);
        char[] arr = new char[1];
        System.out.println(arr[0]);
    }
}

class Node{
    int len = 0;
    char[] chars = new char[5];
    Node next = null;
}
