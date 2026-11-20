class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;
        for (int x : nums) {
            dp.put(x + 1, dp.getOrDefault(x, 0) + 1);   // use x as x+1 (first)
            dp.put(x, dp.getOrDefault(x - 1, 0) + 1);   // keep x as x
            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }
        return ans;
    }
}