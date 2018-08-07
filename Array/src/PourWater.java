/**
 * Created by yeqing on 1/1/2018.
 */
public class PourWater {
    public int[] pourWater(int[] heights, int V, int K) {
        while (V > 0) {
            int idx1 = K, idx2 = K;
            for(int i = K-1; i >= 0; i--) { // check left
                if(heights[i] > heights[idx1]) break;
                if(heights[i] < heights[idx1]) idx1 = i;
            }
            if(idx1 != K) { // droplet can fall in the left side, finish the searching
                heights[idx1]++;
                V--;
                continue;
            }
            for(int i = K+1; i < heights.length; i++) { // droplet cannot fall in the left side, check right
                if(heights[i] > heights[idx2]) break;
                if(heights[i] < heights[idx2]) idx2 = i;
            }
            heights[idx2]++;
            V--;
        }
        return heights;
    }
}
