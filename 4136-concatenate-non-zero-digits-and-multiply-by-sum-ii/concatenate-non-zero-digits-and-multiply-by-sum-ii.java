class Solution {
    private static final int MOD = 1000000007;
    private static final long[] pow10 = new long[100005];
    
    static {
        pow10[0] = 1;
        for (int i = 1; i < pow10.length; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        
        long[] sumPref = new long[n + 1];
        long[] xPref = new long[n + 1];
        int[] countPref = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            
            sumPref[i + 1] = sumPref[i] + d;
            countPref[i + 1] = countPref[i] + (d > 0 ? 1 : 0);
            
            xPref[i + 1] = (d > 0) ? (xPref[i] * 10 + d) % MOD : xPref[i];
        }

        int[] ans = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int length = countPref[r + 1] - countPref[l];
            long totalSum = (sumPref[r + 1] - sumPref[l]);
            
            long subVal = (xPref[r + 1] - (xPref[l] * pow10[length]) % MOD) % MOD;
            
            if (subVal < 0) {
                subVal += MOD;
            }

            ans[i] = (int) ((subVal * (totalSum % MOD)) % MOD);
        }
        
        return ans;
    }
}