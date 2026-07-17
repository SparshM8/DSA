class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        
        long[] g = new long[maxVal + 1];
        
        for (int i = maxVal; i >= 1; i--) {
            long c = 0;
            for (int j = i; j <= maxVal; j += i) {
                c += cnt[j];
            }
            
            long exactPairs = c * (c - 1) / 2;
            
            for (int j = 2 * i; j <= maxVal; j += i) {
                exactPairs -= g[j];
            }
            
            g[i] = exactPairs;
        }
        
        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + g[i];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = binarySearch(pref, queries[i]);
        }
        
        return ans;
    }
    
    private int binarySearch(long[] pref, long target) {
        int low = 1;
        int high = pref.length - 1;
        int result = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (pref[mid] > target) {
                result = mid;       
                high = mid - 1;
            } else {
                low = mid + 1;      
            }
        }
        
        return result;
    }
}