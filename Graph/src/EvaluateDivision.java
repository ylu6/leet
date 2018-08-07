import java.util.*;

/**
 * Created by yeqing on 11/4/2017.
 */
public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) { // build the whole map from equations and values
            String str1 = equations[i][0], str2 = equations[i][1];
            map.putIfAbsent(str1, new HashMap<String, Double>());
            map.putIfAbsent(str2, new HashMap<String, Double>());
            map.get(str1).put(str2, values[i]);
            map.get(str2).put(str1, 1.0/values[i]);
        }
        for (String s : map.keySet()) {
            System.out.println(s + ": ");
            for (Map.Entry<String, Double> entry : map.get(s).entrySet())
                System.out.println(entry.getKey() + ": " + entry.getValue());
            System.out.println();
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < res.length; i++) {
            String[] query = queries[i];
            System.out.println("start: " + query[0] + " end: " + query[1]);
            if (!map.containsKey(query[0]) || !map.containsKey(query[1])) {
                res[i] = -1.0;
                continue;
            }
            if (query[0].equals(query[1])) {
                res[i] = 1.0;
                continue;
            }
            Set<String> seen = new HashSet<>();
            res[i] = dfsSearch(query[0], query[1], map, seen, 1.0);
            if (res[i] == 0.0) res[i] = -1.0; // str1 and str2 not connected,
            //else  map.get(query[0]).put(query[1], res[i]);// add the value into map, may speed up later query
        }

        return res;
    }
    double dfsSearch(String start, String end, Map<String, Map<String, Double>> map, Set<String> seen, double value) {
        System.out.println("search start: " + start);
        if (seen.contains(start)) return 0.0; // this string was seen before, not possible
        seen.add(start);
        if (start.equals(end)) return value*1.0;

        double ret = 0.0;
        for (Map.Entry<String, Double> entry : map.get(start).entrySet()) {
            ret = dfsSearch(entry.getKey(), end, map, seen, entry.getValue()*value);
            if (ret != 0.0) return ret; // find result, early break
        }
        return ret; // if not found, return 0.0
    }

    public static void main(String[] args) {
        EvaluateDivision sol = new EvaluateDivision();
        String[][] equations = {{"a","b"},{"b","c"}};
        double[] values = {2.0,3.0};
        String[][] queries = { {"a","c"},{"b","c"}};
        for (double res : sol.calcEquation(equations, values, queries))
            System.out.println(res);
    }
}
