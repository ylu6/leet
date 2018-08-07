public class FriendsOfAppropriateAges {
    // A will NOT friend request B if any of the following conditions are true:
    // 1. age[B] <= 0.5 * age[A] + 7
    // 2. age[B] > age[A]
    // 3. age[B] > 100 && age[A] < 100
    // indeed condition 3 is a subset of condition 2, therefore we only need to check 1 and 2
    // From 1 and 2, in order for A to request B as friend, we need to have
    // age[A] >= age[B] && age[B] > 0.5*age[A] + 7
    // from here we must have age[A] > 14 and age[B] > 14
    public int numFriendRequests(int[] ages) {
        int[] ageCount = new int[121];
        for(int age : ages) ageCount[age]++;
        int res = 0;
        for(int A = 15; A < ageCount.length; A++) {
            for(int B = 15; B <= A; B++ ) {
                if(A/2 + 7 < B) {
                    res += A==B ? ageCount[A]*(ageCount[A]-1) : ageCount[A]*ageCount[B];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
