class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] present = new boolean[101];
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        
        for (int num : nums) {
            present[num] = true;
            lo = Math.min(lo, num);
            hi = Math.max(hi, num);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            if (!present[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}