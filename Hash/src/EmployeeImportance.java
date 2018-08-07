import java.util.*;

/**
 * Created by yeqing on 10/20/2017.
 */
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};

public class EmployeeImportance {
    public int getImportance(List<Employee> employees, int id) {
        int res = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees)
            map.put(e.id, e);

        Queue<Employee> queue = new ArrayDeque<>();
        if (!map.containsKey(id)) return res;
        queue.add(map.get(id));
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            res += e.importance;
            for(int i : e.subordinates)
                queue.add(map.get(i));
        }

        return res;
    }
}
