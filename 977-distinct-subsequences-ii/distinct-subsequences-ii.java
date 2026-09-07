class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        long[] endsWith = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long sum = 0;
            for (int i = 0; i < 26; i++) {
                sum = (sum + endsWith[i]) % MOD;
            }
            
            endsWith[idx] = (sum + 1) % MOD;
        }
        
        long total = 0;
        for (int i = 0; i < 26; i++) {
            total = (total + endsWith[i]) % MOD;
        }
        
        return (int) total;
    }
}