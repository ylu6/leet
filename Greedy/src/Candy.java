import java.util.Arrays;

/**
 * Created by yeqing on 12/9/2017.
 */
public class Candy {
    //Greedy: every local minimum should be 1, and all other's should be as small as possible
    // 1. scan from left to right, if rating[i] > rating[i-1], candies[i] = candies[i-1],
    // 2. scan from right to left, if [rating[i-1] > rating[i], candies[i-1] = max(candies[i-1], 1+candies[i])
    // this process ensures local minimum is 1, and local maximum is optimal (smallest value among all possible solutions)
    // e.g. 5, 6, 2, 2, 4
    // left to right: 1, 2, 1, 1, 2
    // right to left: 1, 2, 1, 1, 2
    public int candy(int[] ratings) {
        if (ratings.length <= 1) return ratings.length;
        int[] candies = new int[ratings.length];
        int sum = 0;
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) { // scan from left to right
            if (ratings[i] > ratings[i-1])  candies[i] = 1+candies[i-1];
        }
        for (int i = ratings.length-1; i > 0; i--) { // scan from right to left
            if (ratings[i-1] > ratings[i]) candies[i-1] = Math.max(candies[i-1], 1+candies[i]);
        }
        for (int n : candies)   sum += n;
        return sum;
    }
}
