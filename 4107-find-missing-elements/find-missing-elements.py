class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        num_set = set(nums)
        lo, hi = min(nums), max(nums)
        
        return [i for i in range(lo, hi + 1) if i not in num_set]