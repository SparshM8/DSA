class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        
        for (int num : nums) {
            numSet.add(num);
            lo = Math.min(lo, num);
            hi = Math.max(hi, num);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            if (!numSet.contains(i)) {
                result.add(i);
            }
        }
        
        return result;
    }
}