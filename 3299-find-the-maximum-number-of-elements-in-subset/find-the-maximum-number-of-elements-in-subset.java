class Solution {
    public int maximumLength(int[] nums) {
        Arrays.sort(nums);
        
        int n = nums.length;
        int[] unique = new int[n];
        int[] counts = new int[n];
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                counts[idx - 1]++;
            } else {
                unique[idx] = nums[i];
                counts[idx] = 1;
                idx++;
            }
        }
        
        int maxLen = 1;
        
        for (int i = 0; i < idx; i++) {
            long x = unique[i];
            
            if (x == 1) {
                int ones = counts[i];
                if (ones % 2 == 0) ones--; 
                maxLen = Math.max(maxLen, ones);
                continue;
            }
            
            int currentLen = 0;
            
            while (true) {
                int pos = Arrays.binarySearch(unique, 0, idx, (int) x);
                
                if (pos >= 0) {
                    if (counts[pos] >= 2) {
                        currentLen += 2;
                        x = x * x; 
                        
                        if (x > 1_000_000_000) {
                            currentLen -= 1; 
                            break;
                        }
                    } else {
                        currentLen += 1;
                        break;
                    }
                } else {
                    currentLen -= 1;
                    break;
                }
            }
            
            maxLen = Math.max(maxLen, Math.max(1, currentLen));
        }   
        return maxLen;
    }
}