import java.util.Arrays;

public class CheapestFlightsWithinKStops {
    //bellman-ford shortest path algorithm
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] priceTo = new int[n];
        Arrays.fill(priceTo, 100000000); // upto 1e5 cities, price upto 100, so 1e8 is a safe number
        priceTo[src] = 0;

        for(int i = 0; i <= K; i++) {
            int[] tmp = Arrays.copyOf(priceTo, n);
            for(int[] flight : flights) {
                tmp[flight[1]] = Math.min(tmp[flight[1]], priceTo[flight[0]] + flight[2]);
            }
            priceTo = tmp;
        }
        return priceTo[dst] == 100000000 ? -1 : priceTo[dst] ;
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        CheapestFlightsWithinKStops sol = new CheapestFlightsWithinKStops();
        System.out.println("price: " + sol.findCheapestPrice(3, flights, 0, 2, 1));
    }
}
