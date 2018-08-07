import java.util.*;

public class DomainClickCount {
    List<String> domainCount(List<String> counts) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // loop through all domain counts
        for(String count : counts) {
            int idx = count.indexOf(','); // find first idx of ',' which split count and domain
            int n = Integer.parseInt(count.substring(0, idx)); // extract the count
            String domain = count.substring(idx+1); // extract the domain
            map.put(domain, map.getOrDefault(domain, 0) + n); // add the full domain
            for(int i = 0; i < domain.length(); i++) { // process sub-domain
                if(domain.charAt(i)=='.') {
                    String substr = domain.substring(i+1);
                    map.put(substr, map.getOrDefault(substr, 0) + n);
                }
            }
        }
        // final results format: "domain=count"
        for(String key : map.keySet()) {
            res.add(key + "=" + map.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        DomainClickCount sol = new DomainClickCount();
        String[] arr = {
            "900,google.com",
            "60,mail.yahoo.com",
            "10,mobile.sports.yahoo.com",
            "40,sports.yahoo.com",
            "300,yahoo.com",
            "10,stackoverflow.com",
            "2,en.wikipedia.org",
            "1,es.wikipedia.org"
        };
        List<String> counts = Arrays.asList(arr);
        System.out.println(sol.domainCount(counts));
    }
}

/*
Q1: domain click count

//       String[] counts = {
//       "900,google.com",
//       "60,mail.yahoo.com",
//       "10,mobile.sports.yahoo.com",
//       "40,sports.yahoo.com",
//       "300,yahoo.com",
//       "10,stackoverflow.com",
//       "2,en.wikipedia.org",
//       "1,es.wikipedia.org" };

Return click counts for each domain
com=1320, stackoverflow.com=10, mail.yahoo.com=60, sports.yahoo.com=50,google.com=900, en.wikipedia.org=2,
mobile.sports.yahoo.com=10, org=3, wikipedia.org=3, yahoo.com=410, es.wikipedia.org=1
 */
