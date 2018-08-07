import java.util.*;

public class IncreasingSubsequences {

    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List list1 = Arrays.asList(1,2);
        List list2 = Arrays.asList(963);
        set.add(list1);
        set.add(list2);
        System.out.println(set.size());
        System.out.println(list1.hashCode());
        System.out.println(list2.hashCode());
    }
}
