import java.util.ArrayList;
import java.util.List;

public class FindIslands {
    // Q1: there is only one island, return the top left and bot right coordinates as [x1, y1, x2, y2]
    int[] getOneRectangle(int[][] mat) {
        int[] res = new int[4];
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat.length; j++) {
                if(mat[i][j] == 0) {
                    res = combineCoords(getTopLeft(mat, i, j), getBotRight(mat, i, j));
                    return res;
                }
            }
        }
        return res;
    }

    // Q2: there can be multiple islands, return all
    List<int[]> getAllRectangle(int[][] mat) {
        List<int[]> res = new ArrayList<>();
        // foreach cell, check whether its left and top are 0
        // if both not, this is a new island
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 1 || (i > 0 && mat[i-1][j] == 0) || (j > 0 && mat[i][j-1] == 0)) continue;
                res.add(combineCoords(getTopLeft(mat, i, j), getBotRight(mat, i, j)));
            }
        }
        return res;
    }

    //Q3: islands are not rectangle, return a Set<int[]> or List represent each island
    List<List<int[]>> getAllIslands(int[][] mat){
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        List<List<int[]>> res = new ArrayList<>();

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0 && !visited[i][j]) {
                    List<int[]> island = new ArrayList<>();
                    dfs(mat, visited, island, i, j);
                    res.add(island);
                }
            }
        }
        return res;
    }

    void dfs(int[][] mat, boolean[][] visited, List<int[]> island, int i, int j) {
        if(i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) return;
        if(visited[i][j] || mat[i][j] == 1) return;

        visited[i][j] = true;
        island.add(new int[]{i, j});
        int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        for(int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            dfs(mat, visited, island, r, c);
        }
    }

    // combine two coords int[2] into one arr int[4]
    int[] combineCoords(int[] a, int[] b){
        int[] res = new int[4];
        res[0] = a[0];
        res[1] = a[1];
        res[2] = b[0];
        res[3] = b[1];
        return res;
    }

    // return the top left coordinate of the island represented by zeros surrounded by ones
    int[] getTopLeft(int[][] mat, int i, int j){
        while(i-1 >= 0 && mat[i-1][j] == 0) i--;
        while(j-1 >= 0 && mat[i][j-1] == 0) j--;
        return new int[]{i,j};
    }
    // return the bot right coordinate
    int[] getBotRight(int[][] mat, int i, int j) {
        while(i+1 < mat.length && mat[i+1][j] == 0) i++;
        while(j+1 < mat[0].length && mat[i][j+1] == 0) j++;
        return new int[]{i, j};
    }

    void printArray(int[] arr){
        for(int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    void printIsland(List<int[]> island) {
        for(int[] cell : island) {
            System.out.print(cell[0] + "," + cell[1] +" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        FindIslands sol = new FindIslands();
        int[][] mat1 = new int[][]{{1,1,1,1}, {1,0,0,1}, {1,0,0,1},{1,1,1,1}};
        int[][] mat2 = new int[][]{{1,1,1,1}, {1,0,0,1}, {1,0,0,1},{1,0,0,1}};
        int[][] mat3 = new int[][]{{0,0,0,1}, {0,0,0,1}, {0,0,0,1},{0,0,0,1}};
        int[][] mat4 = new int[][]{{0,0,0,0}, {0,0,0,0}, {0,0,0,0},{0,0,0,0}};
        int[][] mat5 = new int[][]{
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,1,1}
        };

        int[][] mat6 = new int[][] {
                {0,0,1},
                {0,1,1},
                {1,0,0},
        };
//        sol.printArray(sol.getTopLeft(mat1, 1,1));
////        sol.printArray(sol.getBotRight(mat1, 1,1));
////        sol.printArray(sol.getTopLeft(mat2, 1,1));
////        sol.printArray(sol.getBotRight(mat2, 1,1));
////        sol.printArray(sol.getTopLeft(mat3, 1,1));
////        sol.printArray(sol.getBotRight(mat3, 1,1));
//        sol.printArray(sol.getOneRectangle(mat1));
//        sol.printArray(sol.getOneRectangle(mat2));
//        sol.printArray(sol.getOneRectangle(mat3));
//        sol.printArray(sol.getOneRectangle(mat4));
//        sol.printArray(sol.getTopLeft(mat5, 1,0));
//        sol.printArray(sol.getBotRight(mat5, 1,0));

//        for(int[] i : sol.getAllRectangle(mat5)) {
//            sol.printArray(i);
//        }
        for(List<int[]> island : sol.getAllIslands(mat6)) {
            sol.printIsland(island);
        }
    }
}

/*
给一个矩阵，矩阵里的每个元素是1，但是其中分布着一些长方形区域， 这些长方形区域中的元素为0. 要求输出每个长方形的位置（用长方形的左上角元素坐标和右下角元素坐标表示）。-google 1point3acres
example：
input:
[
[1,1,1,1,1,1],
[0,0,1,0,1,1],
[0,0,1,0,1,0],
[1,1,1,0,1,0],
[1,0,0,1,1,1]
]
output:
[
[1,0,2,1],
[1,3,3,3],
[2,5,3,5],
[4,1,4,2]
]
如果 Matrix 中有多个由0组成的长方体，请返回多套值（前提每两个长方体之间是不会连接的，所以放心）. 不改变输入的做法
不过还有第三问，就是connected components
第三问 基本上就是leetcode connected components,只不过是返回一个listoflist，每个list是一个component的所有点坐标
那个图是1,01,0组成的矩阵，0组成的就是各种图形。跟前面关系的确不大
如果矩阵里有多个不规则的形状，返回这些形状。这里需要自己思考并定义何谓“返回这些形状”
 */
