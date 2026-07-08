MOD = 10**9 + 7
pow10 = [1] * 100005
for i in range(1, 100005):
    pow10[i] = (pow10[i - 1] * 10) % MOD

class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        
        sum_pref = [0] * (n + 1)
        x_pref = [0] * (n + 1)
        count_pref = [0] * (n + 1)
        
        for i, c in enumerate(s):
            d = int(c)
            sum_pref[i + 1] = sum_pref[i] + d
            count_pref[i + 1] = count_pref[i] + (d > 0)
            
            x_pref[i + 1] = (x_pref[i] * 10 + d) % MOD if d > 0 else x_pref[i]

        ans = [0] * len(queries)
        
        for i, (l, r) in enumerate(queries):
            length = count_pref[r + 1] - count_pref[l]
            
            sub_val = (x_pref[r + 1] - x_pref[l] * pow10[length])
            sub_sum = (sum_pref[r + 1] - sum_pref[l])
            
            ans[i] = (sub_val * sub_sum) % MOD

        return ans