class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;

        for (int s = 0;s < n; s++) {
            int count = 0;

            for (int m = s; m < n; m++) {
                if (nums[m] == target) {
                    count++;
                }

                int len = m - s + 1;

                if (count > len / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }
}