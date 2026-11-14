class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = Integer.MAX_VALUE;
        int n = nums.size();
        for (int k = l; k <= r; k++) {
            int sum = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                sum += nums.get(right);
                if (right - left + 1 > k) {
                    sum -= nums.get(left);
                    left++;
                }
                if (right - left + 1 == k && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}