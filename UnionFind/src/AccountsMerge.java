/**
 * Created by yeqing on 11/9/2017.
 */
import java.util.*;

public class AccountsMerge {
    // use union-find. for each List in accounts, first email as the parent of this email group
    // loop through all the accounts and do union, now all the emails belong to same user have same parent
    //
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> output = new ArrayList<>();
        Map<String, String> parents = new HashMap<>(); // {email : parentEmail}, for union-find
        Map<String, String> owner = new HashMap<>(); // {email : userName}, use email to find userName
        Map<String, Set<String>> treeSetResults = new HashMap<>();

        // initialization
        for (List<String> acc : accounts) {
            for (int i = 1; i < acc.size(); i++) {
                parents.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), acc.get(0));
            }
        }
        // loop through all edges to do the union
        // for each accounts, (email[0], email[i]) forms an edge which connects two emails
        for (List<String> acc : accounts) {
            String p = find(parents, acc.get(1)); // parents of first email
            for (int i = 2; i < acc.size(); i++) { // loop through all other emails
                String q = find(parents, acc.get(i));
                if (p != q) // the two emails belong to same account, but have different parents, do union
                    parents.put(q, p); // cannot put(q, p). In this loop, parent of p must keep unchanged
            }
        }

        // loop through all emails, add emails belong to same group to TreeSet
        for (String email : parents.keySet()) {
            String p = find(parents, email);
            if (!treeSetResults.containsKey(p)) treeSetResults.put(p, new TreeSet<String>());
            treeSetResults.get(p).add(email);
        }
        // convert the treeSetResults to List<List<String>> for output
        for (String key : treeSetResults.keySet()) {
            List<String> emails = new LinkedList<>(treeSetResults.get(key));
            emails.add(0, owner.get(key));
            output.add(0, emails);
        }

//        for (String key : parents.keySet())
//            System.out.println(key + ": " + find(parents, key));

        return output;
    }

    private String find(Map<String, String> parents, String s) {
        while (s != parents.get(s))
            s = parents.get(s);
        return s;
    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        input.add(Arrays.asList("John", "johnnybravo@mail.com"));
        input.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        input.add(Arrays.asList("Mary", "mary@mail.com"));

        AccountsMerge sol = new AccountsMerge();
        for (List<String> acc : sol.accountsMerge(input)) {
            System.out.println(acc.toString());
        }
    }
}
