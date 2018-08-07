import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordQuery {

    Map<String, Set<String>> userWord; // store user and set of words this user queried till now
    Map<String, Set<String>> wordUser; // store word and set of users queried this word till now
//    Map<String, Integer> wordFreq;

    public WordQuery() {
        userWord = new HashMap<>();
        wordUser = new HashMap<>();
    }

    int readLine(String line) {
        String[] arr = line.split(" ");
        String user = arr[0];
        String word = arr[1];
        Set<String> usersQueriedThisWord = wordUser.get(word); // every word is queried at most once by same user

        int maxFreq = 0;
        Map<String, Integer> wordFreq = new HashMap<>();
        if(usersQueriedThisWord != null) {
            for (String u : usersQueriedThisWord) {
                for (String w : userWord.get(u)) {
                    // excludes count by current query user or query word
                    if(w.equals(word)) continue;
                    if(u.equals(user)) continue;

                    if(wordUser.get(w).contains(u)) wordFreq.put(w, wordFreq.getOrDefault(w, 0)+1);
                    maxFreq = Math.max(maxFreq, wordFreq.get(w));
                }
            }
        }
//        System.out.println(wordUser);
//        System.out.println(userWord);
//        System.out.println(wordFreq);
        // update maps
        userWord.putIfAbsent(user, new HashSet<>());
        userWord.get(user).add(word);
        wordUser.putIfAbsent(word, new HashSet<>());
        wordUser.get(word).add(user);
        return maxFreq;
    }

    public static void main(String[] args) {
//        try {
//            Scanner s = new Scanner(new FileReader("Indeed/src/queryword.txt"));
//        } catch (FileNotFoundException e) {
//            System.out.println("cannot find file");
//        }

        WordQuery wq = new WordQuery();
        Scanner s = new Scanner(System.in);
        while(s.hasNext()) {
            String line = s.nextLine();
            System.out.println(wq.readLine(line));
        }
    }
}

/*
输入是一堆query，每个query包含了user + query word, 输出是每当进来一个query时，根据之前的query，要返回一个最相关的单词，这题能够保证同一个user，只会query某个单词一次。
具体看例子：
Input:
7
A python
B java
A java
B php
C python
C java
D java

Output:
0
0
0
0
1 java(因为目前A: pyhon java, B: java php, search过python的人中还search最多的是java，1次)
1 python php(此时 A: pyhon java, B: java php, C: python)
2 python(此时A: pyhon java, B: java php, C: python java)
这道题我一开始一直在map如何设计上纠结着，naive的方法最后一直有三个case过不了，所以思考的过程是：
map1<String, Set<String>> // user => the words he searched before
大概能过6个case，开始优化
加了个map2<String, List<String>> // word => list of users who searched this word before
这个的话大概又过了4个，还是有三个过不了，此时我心怀侥幸，一直在优化中间的过程，而不是在优化思想了，结果没成功。直到最后还有20分钟时，换掉了思路，用了一个这个map
map3<String, Map<String, Integer>> // word => related word and times
这样的话，每次一个新词进来，直接就能找到相关的词，然后找到出现次数最多的就好。然后再利用map1中这个user之前query的结果，去update map3。这个思路没有debug完，思路讲给了面试官听，他肯定了这个想法。
 */
