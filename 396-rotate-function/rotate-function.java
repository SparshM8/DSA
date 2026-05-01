class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long F = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            F += (long) i * nums[i];
        }
        long maxF = F;
       for (int i = 1; i < n; i++) {
            F = F + sum - (long) n * nums[n - i];
            maxF = Math.max(maxF, F);
        }    
        return (int) maxF;
    }
}