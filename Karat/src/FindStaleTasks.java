import java.awt.datatransfer.StringSelection;
import java.util.*;

public class FindStaleTasks {
    List<String> findStaleTasks(String[][] precursorSteps, String[][] lastExecTimes) {
        Map<String, List<String>> adj = new HashMap<>(); // adjacent list to store the graph
        Map<String, String> execTime = new HashMap<>(); // last execution time of each task

        for(String[] edge : precursorSteps) { // build the graph
            adj.putIfAbsent(edge[0], new ArrayList<>()); // !!! ADD two node of the edge to adj first
            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);
        }
        for(String[] arr : lastExecTimes){ // create map :{task: lastExecTime}
            execTime.put(arr[0], arr[1]);
        }

        Set<String> visited = new HashSet<>(); // keep track of visited nodes
        List<String> res = new ArrayList<>(); // final result, which stores those staled tasks
        dfs(adj, execTime, visited, res, precursorSteps[0][0], false);

        return res;
    }

    void dfs(Map<String, List<String>> adj, Map<String, String> execTime, Set<String> visited, List<String> res, String task, boolean staled) {
        if(visited.contains(task)) return;
        if(staled) res.add(task);
        visited.add(task);
        for(String next : adj.get(task)) {
            if(visited.contains(next)) continue;
            String curTime = execTime.get(task);
            String nxtTime = execTime.get(next);
            if(staled || curTime.compareTo(nxtTime) > 0) { // if current task is staled, all following task is staled; if curTime > nxtTime, next task is staled
                dfs(adj, execTime, visited, res, next, true);
            } else {
                dfs(adj, execTime, visited, res, next, false); // otherwise next task is not staled
            }
        }
    }

    public static void main(String[] args) {
        String[][] precursor_steps = {
                {"clean", "mapper"},
                {"metadata", "statistics"},
                {"mapper", "update"},
                {"update", "statistics"},
                {"clean", "metadata"},
                {"mapper", "reducer"},
                {"metadata", "timestamp"}
        };

        String[][] last_execution_times = {
                {"clean", "20170302-1129"},
                {"mapper", "20170302-1155"},
                {"update", "20170302-1150"},
                {"statistics", "20170302-1153"},
                {"metadata", "20170302-1130"},
                {"reducer", "20170302-1540"},
                {"timestamp", "20170302-1030"}
        };
        FindStaleTasks sol = new FindStaleTasks();
        System.out.println(sol.findStaleTasks(precursor_steps, last_execution_times));
    }
}


/*
# Now let's switch over to the back-end of our social network. We have some automated batch jobs that we use to handle
expensive tasks that run periodically throughout the day, like updating statistics for the most popular posts.
We've been given some input that shows the dependencies between each of these batch jobs.

# For example, in this input, "clean" must be executed before "mapper" can execute.
# Given the last execution time for each step of the workflow, we want to find the set of all steps that are "stale"
-- steps that have not executed since the last time one of their precursor steps executed. For example, in this case,
"update" is in the output because "mapper" must occur before "update", but "update" has not been executed since the last time "mapper" was executed.
If a task is stale, all tasks after it are stale too -- so "statistics" is stale because of "mapper".
#                          /--> reducer
#          /---> mapper --|
# clean --|                \--> update --\
#          \                              --> statistics
#           \---> metadata --------------/
#                         \
#                          \--> timestamp

# Sample input:
# precursor_steps = [
#   ["clean", "mapper"],
#   ["metadata", "statistics"],
#   ["mapper", "update"],
#   ["update", "statistics"],
#   ["clean", "metadata"],
#   ["mapper", "reducer"],
#   ["metadata", "timestamp"],

# last_execution_times = [
#   ["clean", "20170302-1129"],
#   ["mapper", "20170302-1155"],
#   ["update", "20170302-1150"],
#   ["statistics", "20170302-1153"],
#   ["metadata", "20170302-1130"],
#   ["reducer", "20170302-1540"],

# Sample output (in any order):

# find_stale_steps(precursor_steps, last_execution_times) =
#   ["update", "statistics", "timestamp"]
 */