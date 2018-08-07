import java.util.Arrays;

public class ChampagneTower {
    // simulate the process by adding all Champagne into (0,0) glass
    // then split the surplus part and pour into left and right of next level
    // keep doing this process until none of the glasses in current row has surplus
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] table = new double[101][101]; // query range [0,99], so we need 100 row, but when processing 100th row,
                                            // we may pour champagne into 101th row
        table[0][0] = poured;

        for(int r = 0; r <= 99; r++) {
            for(int c = 0; c <=r; c++) {
                if(table[r][c] > 1) {
                    table[r+1][c] += (table[r][c]-1.0)/2.0;
                    table[r+1][c+1] += (table[r][c]-1.0)/2.0;
                    table[r][c] = 1.0;
                }
            }
        }
//        Arrays.stream(table[2]).forEach(x->System.out.print(x + " "));
        return table[query_row][query_glass];
    }

    public static void main(String[] args) {
        ChampagneTower sol = new ChampagneTower();
        System.out.println(sol.champagneTower(4,2,1));
    }
}
