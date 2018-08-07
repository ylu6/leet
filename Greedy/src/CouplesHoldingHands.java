public class CouplesHoldingHands {
    // this is a greedy algm, first scan from left to right
    // store value->index in a pos[] array, which maps number to its index in row
    // second loop, we try to do match every time n and n+1 is not a couple
    // Loop invariant: all previously visited numbers are paired
    public int minSwapsCouples(int[] row) {
        final int N = row.length;
        int[] pos = new int[N];
        for(int i = 0; i < N; i++) {
            pos[row[i]] = i; // map value to index
        }

        int count = 0, temp = 0;
        for(int i = 0; i < N; i = i+2) {
            int spouse = row[i]%2==0 ? row[i]+1 : row[i]-1; // find spouse of row[i]
            int spouseIdx = pos[spouse]; // find the index of row[i]'s spouse
            if(row[i+1] != spouse) { // row[i+1] and row[i] are not couple
                row[spouseIdx] = row[i+1];
                pos[row[i+1]] = spouseIdx;
                //row[i+1] = spouse; // indeed, no need to set row[i+1], because it won't be used anymore
                //pos[spouse] = i+1; // indeed, no need to set pos[spouse], it won't be used anymore
                count++;
            }
        }
        return count;
    }
}
