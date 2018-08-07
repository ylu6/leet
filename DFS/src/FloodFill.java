/**
 * Created by yeqing on 11/30/2017.
 */
public class FloodFill {
    // here we don't have another matrix to check whether a element was visited or not during the dfs process
    // it is because we always check image[sr][sc] != color. If visited before, image[sr][sc] is newColor
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image; // without this line, when newColor == image[sr][sc], there will be infinite loop
        dfs(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    void dfs(int[][] image, int sr, int sc, int newColor, int color) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color)
            return;
        image[sr][sc] = newColor;
        dfs(image, sr-1, sc, newColor, color); // up
        dfs(image, sr+1, sc, newColor, color); // down
        dfs(image, sr, sc-1, newColor, color); // left
        dfs(image, sr, sc+1, newColor, color); // right
    }

    static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        FloodFill sol = new FloodFill();
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        printMatrix(sol.floodFill(image, 1,1,1));
    }
}
