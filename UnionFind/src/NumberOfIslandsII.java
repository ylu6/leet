/*
Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k).
Originally, the 2D matrix is all 0 which means there is only sea in the matrix.
The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change
the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}


// union find
public class NumberOfIslandsII {
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        int[] arr = new int[m*n];
        Arrays.fill(arr, -1);
        int cnt = 0;

        int[][] dirs = {{-1,0}, {1,0}, {0,-1},{0,1}};

        for(Point op : operators) {
            int id = op.x*m + op.y;
            if(arr[id] != -1) {
                res.add(cnt); // there are duplicate operator exists, this point has been visited before
                continue;
            }
            cnt++;
            arr[id] = id;
            for(int[] dir : dirs) { // check 4 possible neighbors: up,down,left,right
                int i = op.x + dir[0];
                int j = op.y + dir[1];
                int idx = i*m + j;
                if(i < 0 || i >= n || j < 0 || j >= m || arr[idx]==-1) continue; // out of boundary
                int nid = findRoot(arr, idx);
                if(id != nid) { // current point has different root as neighbor, connect them and decr cnt
                    arr[nid] = id;
                    cnt--;
                }
            }
            res.add(cnt);
        }

        return res;
    }

    int findRoot(int[] arr, int q) {
        while(q != arr[q]) {
//            arr[q] = arr[arr[q]]; // path compression
            q = arr[q];
        }
        return q;
    }

    public static void main(String[] args) {
        Point[] operations = new Point[4];
        operations[0] = new Point(0,0);
        operations[1] = new Point(0,1);
        operations[2] = new Point(2,2);
        operations[3] = new Point(2,1);
        NumberOfIslandsII sol = new NumberOfIslandsII();
        System.out.println(sol.numIslands2(3,3,operations).toString());
    }
}
