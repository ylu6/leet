import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class TrieNode{
    TrieNode[] next;
    List<String> words;
    public TrieNode(){
        next = new TrieNode[26]; // A-Z, 26 upper case letter
        words = new ArrayList<>(); // list of String with prefix represented by this path
    }
}

public class AutoComplete {
    List<String> autocomplete(String[] className, String str){
        TrieNode root = new TrieNode(), cur = root;
        for(String name : className)
            addWord(root, name);

        List<String> res = new ArrayList<>();
        for(char c : str.toCharArray()) {
            if(Character.isUpperCase(c)) {
                if(cur.next[c-'A'] == null) return new ArrayList<>(); // uppercase letter not match, return empty list
                cur = cur.next[c-'A'];
                res = cur.words; // matched, res points to the new words list
            }
        }
        return res;
    }
    // add word into Trie, constructed with capital letters only
    void addWord(TrieNode root, String word) {
        for(char c : word.toCharArray()) {
            if(Character.isUpperCase(c)) {
                if(root.next[c-'A'] == null) root.next[c-'A'] = new TrieNode();
                root = root.next[c-'A'];
                root.words.add(word); // add the word to words list of current TrieNode
            }
        }
    }

    public static void main(String[] args) {
        String[] className = {
                "GraphView",
                "DataGraphView",
                "DataController",
                "GraphViewController",
                "DataScienceView"
        };
        AutoComplete sol = new AutoComplete();
        System.out.println(sol.autocomplete(className, "Data"));
        System.out.println(sol.autocomplete(className, "GVi"));
        System.out.println(sol.autocomplete(className, "GraphController"));
    }
}





/*
给IDE设计一个autocomplete功能。给出一个如下的String[]。要求输入大写字母和几个小写字母后，实现autocomplete。大写字母必须全部match。感觉是用Trie，但怎么存怎么查没想明白。欢迎大家讨论。

String[]  className {
        "GraphView",
        "DataGraphView",
        "DataController",
        "GraphViewController",
        "DataScienceView"
}

autocomplete(String[] className, "Data");  --> {"DataGraphView", "DataController", "DataScienceView"};
autocomplete(String[] className, "GVi");   -->  {"GraphView",  "GraphViewController"};
autocomplete(String[] className, "GraphController");   -->  {""};
 */