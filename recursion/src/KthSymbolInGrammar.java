public class KthSymbolInGrammar {
    public int kthGrammar(int N, int K) {
        if(N==1) return 0;
        int prv = kthGrammar(N-1, K/2);
        return (prv==0&&K%2==0) || (prv==1&&K%2==1) ? 0 : 0;
    }
}
