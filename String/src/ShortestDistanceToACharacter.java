public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        int[] distToLeft = new int[S.length()];
        int[] distToRight = new int[S.length()];

        // scan from left to right
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == C) distToLeft[i] = 0;
            else {
                distToLeft[i] = (i==0 || distToLeft[i-1] == Integer.MAX_VALUE) ?
                        Integer.MAX_VALUE : distToLeft[i-1] + 1;
            }
        }

        // scan from right to left
        for(int i = S.length()-1; i >= 0; i--) {
            if(S.charAt(i) == C) distToRight[i] = 0;
            else {
                distToRight[i] = (i==S.length()-1 || distToRight[i+1] == Integer.MAX_VALUE) ?
                        Integer.MAX_VALUE : distToRight[i+1] + 1;
            }
        }

        // combine the two and store results in distToLeft
        for(int i = 0; i < S.length(); i++) {
            distToLeft[i] = Math.min(distToLeft[i], distToRight[i]);
        }

        return distToLeft;
    }
}
