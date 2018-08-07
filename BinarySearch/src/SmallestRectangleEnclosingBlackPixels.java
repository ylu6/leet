public class SmallestRectangleEnclosingBlackPixels {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */

    // version 1, use dfs, O(MN), may have stack overflow problem
    public int minArea(char[][] image, int x, int y) {
        int[] edges = new int[]{x, x, y, y};
        dfs(image, x, y, edges);
        return (edges[1]-edges[0]+1)*(edges[3]-edges[2]+1);
    }

    // edges: {xmin, xmax, ymin, ymax}
    void dfs(char[][] image, int x, int y, int[] edges) {
        if(x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != '1' )
            return;

        edges[0] = Math.min(edges[0], x);
        edges[1] = Math.max(edges[1], x);
        edges[2] = Math.min(edges[2], y);
        edges[3] = Math.max(edges[3], y);
        image[x][y] = '2'; // mark as visited
        dfs(image, x-1, y, edges);
        dfs(image, x+1, y, edges);
        dfs(image, x, y-1, edges);
        dfs(image, x, y+1, edges);
    }

    // version 2, binarySearch for xmin, xmax, ymin, ymax
    public int minArea2(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int top = binarySearch(image, 0, x, 0, n, true, -1);
        int bot = binarySearch(image, x+1, m, 0, n, true, 1);
        int left = binarySearch(image, 0, y, top, bot, false, -1);
        int right = binarySearch(image, y+1, n, top, bot, false, 1);
        return (bot-top)*(right-left);
    }

    int binarySearch(char[][] image, int lo, int hi, int tLow, int tHi, boolean xDir, int sign) {
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            int i;
            for(i = tLow; i < tHi; i++) {
                char c = xDir ? image[mid][i] : image[i][mid];
                if(c == '1') {
                    if(sign==-1) hi = mid;
                    else lo = mid+1;
                    break;
                }
            }
            if(i==tHi) {
                if(sign==-1) lo = mid + 1;
                else hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        char[][] image = {"0010".toCharArray(),"0110".toCharArray(),"0100".toCharArray()};
        SmallestRectangleEnclosingBlackPixels sol = new SmallestRectangleEnclosingBlackPixels();
        System.out.println(sol.binarySearch(image, 1, 3, 0 , 3, true, 1));
        System.out.println(sol.minArea2(image, 1, 1));
    }
}
