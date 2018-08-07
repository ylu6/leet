import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeqing on 7/6/2017.
 */
public class LogSystem {
    List<Integer> logId;
    List<String> logTime;
    Map<String, Integer> map;

    public enum Index {
        Year(4), Month(7), Day(10), Hour(13), Minute(16), Second(19);
        private int index;
        Index(int index) { this.index = index; }
    }
    public LogSystem() {
        logId = new ArrayList<Integer>();
        logTime = new ArrayList<String>();
        map = new HashMap<String, Integer>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        logId.add(id);
        logTime.add(timestamp);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<Integer>();
//        int idx = map.get(gra);
        int idx = Index.valueOf(gra).index;
        for (int i = 0; i < logId.size(); i++) {
            String t = logTime.get(i).substring(0, idx);
            if (t.compareTo(s.substring(0, idx)) >=0  && t.compareTo(e.substring(0, idx)) <= 0 )
                res.add(logId.get(i));
        }
        return res;
    }
}