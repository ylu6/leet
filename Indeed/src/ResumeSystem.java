import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class ResumeSystem {

    Map<String, Integer> verMap;
    Map<String, Map<String, TreeMap<Integer, String>>> map;

    public ResumeSystem() {
        verMap = new HashMap<>();
        map = new HashMap<>();
    }

    // update the profileID with {field, value}, auto increase version by 1
    void update(String profileId, String field, String value){
        int prvVer = verMap.getOrDefault(profileId, 0);
        int curVer = prvVer + 1;
        map.putIfAbsent(profileId, new HashMap<>()); // create a profile if not exists
        map.get(profileId).putIfAbsent(field, new TreeMap<>()); // create field if not exists
        map.get(profileId).get(field).put(curVer, value);
        verMap.put(profileId, curVer); // store the latest version in verMap
    }

    // return map contains {filed:value}
    Map<String, String> get(String profileId, int version) {
        Map<String, String> res = new HashMap<>();
        if(!map.containsKey(profileId) || version > verMap.get(profileId)) return res;
        Map<String, TreeMap<Integer, String>> resume = map.get(profileId);
        if(resume == null) return res;
        for(String field : resume.keySet()) {
            if(resume.get(field) == null) continue;
            Integer recentVer = resume.get(field).floorKey(version);
            if(recentVer != null) {
                res.put(field, resume.get(field).get(recentVer));
            }
        }
        return res;
    }

    // return the value of field associated with the specified version
    String getField(String profileId, int version, String field) {
        if(version > verMap.get(profileId)) return "";
        if(map.containsKey(profileId) && map.get(profileId).containsKey(field)){
            Integer key = map.get(profileId).get(field).floorKey(version);
            if(key == null) return "";
            return map.get(profileId).get(field).get(key);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        ResumeSystem sys = new ResumeSystem();
        Scanner s = new Scanner(System.in);
        while(true) {
            String line = s.nextLine();
            String[] arr = line.split(" ");
            if(arr[0].equals("update")) {
                sys.update(arr[1], arr[2], arr[3]);
            } else if(arr[0].equals("get")) {
                System.out.println(sys.get(arr[1], Integer.parseInt(arr[2])));
            } else if(arr[0].equals("getField")) {
                System.out.println(sys.getField(arr[1], Integer.parseInt(arr[2]), arr[3]));
            } else
                break;
        }
    }
}
