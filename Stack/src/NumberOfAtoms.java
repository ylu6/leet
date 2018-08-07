import java.util.*;

/**
 * Created by yeqing on 12/18/2017.
 */
public class NumberOfAtoms {
    // (H2O2)  (H2O2)2 Mg2O3
    public String countOfAtoms(String formula) {
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        stack.addFirst(new HashMap<String, Integer>());
        StringBuilder sb = new StringBuilder();
        int count = 0;
        String name;
        Map<String,Integer> top = null;
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if(Character.isDigit(c)) {
                count = count*10 + c-'0';
                System.out.println(count);
                if(i+1==formula.length() || !Character.isDigit(formula.charAt(i+1))) {
                    if(sb.length()==0) combineMap(stack, count); // the number is following a ')
                    else { // the number is following a Atom name, add {AtomName : count} to the Map on top of the stack
                        name = sb.toString();
                        top = stack.peekFirst();
                        top.put(name, top.getOrDefault(name, 0) + count);
                        sb = new StringBuilder();
                    }
                    count = 0;
                }
            }
            else if(c == '(') stack.addFirst(new HashMap<String, Integer>());
            else if(c == ')') {
                if(i+1==formula.length() || !Character.isDigit(formula.charAt(i+1))) {
                    combineMap(stack, 1);
                }
            }
            else { // is letter
                sb.append(c);
                if(i+1==formula.length() || (!Character.isLowerCase(formula.charAt(i+1)) && ! Character.isDigit(formula.charAt(i+1)))) {
                    top = stack.peekFirst();
                    name = sb.toString();
                    top.put(name, top.getOrDefault(name, 0 ) + 1);
                    sb = new StringBuilder();
                }
            }
        }
        StringBuilder res = new StringBuilder();
        List<String> atoms = new ArrayList(stack.peekFirst().keySet());
        Collections.sort(atoms);
        top = stack.pollFirst();
        for(String atom : atoms) {
            res.append(atom);
            if(top.get(atom) != 1) res.append(top.get(atom));
        }
        return res.toString();
    }
    private void combineMap(Deque<Map<String,Integer>> stack, int count) {
        Map<String, Integer> top = stack.pollFirst();
        if(stack.isEmpty()) stack.addFirst(new HashMap<String, Integer>());
        Map<String, Integer> newtop = stack.peekFirst();
        for(String key :top.keySet())
            newtop.put(key, newtop.getOrDefault(key, 0) + count*top.get(key));
    }

    public static void main(String[] args) {
        String formula = "Be32";
        NumberOfAtoms sol = new NumberOfAtoms();
        System.out.println(sol.countOfAtoms(formula));
        System.out.println((int)('2'-'0'));
    }
}
