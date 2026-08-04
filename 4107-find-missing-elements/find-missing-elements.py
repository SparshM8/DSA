class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        present = [False] * 101
        for num in nums:
            present[num] = True
        
        lo, hi = min(nums), max(nums)
        return [i for i in range(lo, hi + 1) if not present[i]]