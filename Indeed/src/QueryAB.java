import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class QueryAB {
    // {word : {line: count}}
    private Map<String, TreeMap<Integer, Integer>> map;

    public QueryAB(){
        map = new HashMap<>();
        Scanner s = null;
        String filename = "Indeed/src/querytext.txt";
        try{
            s= new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("cannot find file: " + filename);
            return;
        }
        int lineID = 1;
        while(s.hasNext()) {
            String line = s.nextLine();
            Map<String, Integer> lineMap = parseLine(line);
            for(String word : lineMap.keySet()) {
                map.putIfAbsent(word, new TreeMap<>()); // ordered by lineID
                map.get(word).put(lineID, lineMap.get(word));
            }
            lineID++;
        }
    }

    private Map<String, Integer> parseLine(String line) {
        Map<String, Integer> res = new HashMap<>();
        for(String word : line.split(" ")) {
            if(word.isEmpty()) continue;
            res.put(word, res.getOrDefault(word, 0) + 1);
        }
        return res;
    }

    List<Integer> query(String a) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.get(a).entrySet());
        list.sort(new OrderByCountAndLine());
        return list.stream().map(entry->entry.getKey()).collect(Collectors.toList());
    }

    List<Integer> queryAAndB(String a, String b) {
        List<Integer> list = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> listA = new ArrayList<>(map.get(a).entrySet());
        List<Map.Entry<Integer, Integer>> listB = new ArrayList<>(map.get(b).entrySet());

        return list;
    }

    List<Integer> queryAOrB(String a, String b) {
        List<Integer> list = new ArrayList<>();

        return list;
    }

    // {line : freq}
    class OrderByCountAndLine implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o1.getValue()==o2.getValue()
                    ? o1.getKey() - o2.getKey() : o2.getValue() - o1.getValue();
        }
    }

    public static void main(String[] args) {
        QueryAB sol = new QueryAB();
        System.out.println(sol.query("a"));
        System.out.println(sol.query("b"));
    }
}
