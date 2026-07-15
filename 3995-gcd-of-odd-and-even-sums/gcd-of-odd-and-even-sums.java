class Solution {
    public int gcdOfOddEvenSums(int n) {
        int SumO = n*n;
        int SumE = n*(n+1);
        return gcd(SumO, SumE);
    }
    private int gcd(int a, int b){
        while(b!=0){
            int temp =b;
            b = a%b;
            a =temp;
        }
        return a;
    }
}