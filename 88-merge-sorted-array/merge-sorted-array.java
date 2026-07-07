class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;         // Pointer for nums1 valid elements
        int j = n - 1;         // Pointer for nums2 elements
        int k = m + n - 1;     // Pointer for placing elements at the back of nums1
        
        // Loop runs as long as there are elements left to process in nums2
        while (j >= 0) {
            // If nums1 still has elements and its current element is larger
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                // Otherwise, nums2 has the larger element (or nums1 is empty)
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}