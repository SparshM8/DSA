class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long ans = 0;
        int evenCount = 1; 
        int oddCount = 0;
        int currentParity = 0; 

        for (int num : arr) {
            currentParity ^= (num & 1);
            if (currentParity == 1) {
                ans += evenCount;
                oddCount++;
            } else {
                ans += oddCount;
                evenCount++;
            }
        }

        return (int) (ans % MOD);
    }
}