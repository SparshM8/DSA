class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int L=0,R=0,B=0;
    for(char ch : moves.toCharArray())
    {
        if (ch=='L')L++;
        else if (ch=='R')R++;
        else B++;
    }
    return Math.abs(L-R)+B;
    }
}