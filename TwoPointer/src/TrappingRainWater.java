/**
 * Created by yeqing on 8/1/2017.
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int maxIdx = 0;
        for (int i = 1; i < height.length; i++)
            if (height[i] > height[maxIdx]) maxIdx = i;

        int leftHigh = height[0], rightHigh = height[height.length-1], midHigh = height[maxIdx];
        int res = 0;
        for (int i = 0; i < maxIdx; i++) {
            if (height[i] > leftHigh) leftHigh = height[i];
            else res += leftHigh - height[i];
        }
        for (int i = height.length-1; i > maxIdx; i--) {
            if (height[i] > rightHigh) rightHigh = height[i];
            else res += rightHigh - height[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater sol = new TrappingRainWater();
        System.out.println(sol.trap(height));
    }
}
