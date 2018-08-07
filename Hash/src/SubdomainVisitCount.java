import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for(String cpdomain : cpdomains) {
            String[] pair = cpdomain.split(" ");
            int count = Integer.parseInt(pair[0]);
            String domain = pair[1];
            map.put(domain, map.getOrDefault(domain,0)+count);
            int idx = domain.indexOf(".");
            while(idx >= 0) {
                domain = domain.substring(idx+1);
                map.put(domain, map.getOrDefault(domain, 0) + count);
                idx = domain.indexOf(".");
            }
        }

        List<String> res = new ArrayList<>();
        for(String s : map.keySet()) {
            res.add(map.get(s) + " " + s);
        }

        return res;
    }
}
