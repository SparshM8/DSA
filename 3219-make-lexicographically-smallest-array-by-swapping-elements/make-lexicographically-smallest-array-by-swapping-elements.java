import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        List<Deque<Integer>> groups = new ArrayList<>();
        Map<Integer, Integer> numToGroup = new HashMap<>();
        
        int groupIdx = 0;
        groups.add(new ArrayDeque<>());
        groups.get(groupIdx).add(sortedNums[0]);
        numToGroup.put(sortedNums[0], groupIdx);
        
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                groupIdx++;
                groups.add(new ArrayDeque<>());
            }
            groups.get(groupIdx).add(sortedNums[i]);
            numToGroup.put(sortedNums[i], groupIdx);
        }
        
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int g = numToGroup.get(nums[i]);
            result[i] = groups.get(g).pollFirst();
        }
        
        return result;
    }
}