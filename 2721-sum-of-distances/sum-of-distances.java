class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : map.values()) {
            long totalSum = 0;
            for (int index : indices) {
                totalSum += index;
            }

            long prefixSum = 0;
            int size = indices.size();

            for (int i = 0; i < size; i++) {
                int currentIdx = indices.get(i);
                long suffixSum = totalSum - prefixSum - currentIdx;

                long leftCount = i;
                long leftTotal = (leftCount * currentIdx) - prefixSum;

                long rightCount = size - 1 - i;
                long rightTotal = suffixSum - (rightCount * currentIdx);

                arr[currentIdx] = leftTotal + rightTotal;

                prefixSum += currentIdx;
            }
        }
        return arr;
    }
}