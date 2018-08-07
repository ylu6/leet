/**
 * Created by yeqing on 8/30/2017.
 */
public class EliminationGame {

    public int lastRemaining(int n) {
        int head = 1; // left head of the remaining numbers
        int step = 1; // step size between remaining numbers, after every round of deletion, step size doubles
        int remaining = n; // numbers int the remaining list
        boolean fromLeft = true; // whether deletion starts from left side in this round

        while(remaining > 1) {
            // current head will be deleted, if delete from left, or delete from right but remaining numbers is odd
            if (fromLeft || remaining%2 == 1) head = head + step; // update head, which is the next number
            step *= 2;
            remaining /= 2;
            fromLeft = !fromLeft;
        }

        return head; // in the end, there is 1 number remaining, which is the head
    }
}
