class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        MOD = 10**9 + 7
        ans = 0
        even_count = 1  
        odd_count = 0
        current_parity = 0 

        for num in arr:
            current_parity ^= (num & 1)
            if current_parity == 1:
                ans += even_count
                odd_count += 1
            else:
                ans += odd_count
                even_count += 1

        return ans % MOD