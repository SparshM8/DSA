class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        int offset = n + 1;
        int maxVal = 2 * n + 2; 
        int[] bit = new int[maxVal];
        
        long result = 0;
        int currentPref = 0;
        
        add(bit, 0 + offset, 1, maxVal);
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                currentPref += 1;
            } else {
                currentPref -= 1;
            }
            
            result += query(bit, currentPref - 1 + offset);
            
            add(bit, currentPref + offset, 1, maxVal);
        }
        
        return result;
    }
    
    private void add(int[] bit, int index, int val, int maxVal) {
        for (; index < maxVal; index += index & -index) {
            bit[index] += val;
        }
    }
    
    private long query(int[] bit, int index) {
        long sum = 0;
        for (; index > 0; index -= index & -index) {
            sum += bit[index];
        }
        return sum;
    }
}