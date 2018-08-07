/**
 * Created by yeqing on 9/8/2017.
 */
public class BulbSwitcherII {
    /*
    if n and m are bigger enough, then the problem can be breakdown into two possible case
    case 1, m is even number, case 2, m is odd number
    there are four possible operations, if one op is done even times, no effect
    case 1, m is even number, we can choose:
    1. 4 operations with even times and 0 with odd times,  original state, 1 possible state
    2. 2 operations with even times and 2 with odd times,  choose 2 from 4, 6 possible state, no overlap with 1
    3. 0 operations with even times and 4 with odd times,  each op done once, 1 possible state, no overlap with 1 and 2
    totally 8 possible states
    case2, m is odd number, we can choose:
    1. 3 operations done even times and 1 done odd times, 4 possible states
    2. 1 operations done even times and 3 done odd times, 4 possible states
    totally 8 possible states

    now consider corner case:
    1. m or n is 0, return 1
    2. both not 0, but n == 1, if m is even, 2 possible states, if m odd, still 2 possible states
    3. if n == 2, if m==1, 3 possible states, m==2, 4 possible states, op1X2, op1+op2, op1+op3, op2+op3
    4. if n > 2, but m==1, 4 possible states, m==2, case 1.1 and case 1.2, 7 possible states in total
    when n > 2, m > 2, always get 8
     */
    public int flipLights(int n, int m) {
        if (m==0 || n==0) return 1;
        if (n==1) return 2;
        if (n==2) {return m > 1 ? 4 : 3;}
        if (n > 2) {
            if (m==1) return 4;
            else if (m==2) return 7;
        }
        return 8;
    }
}
