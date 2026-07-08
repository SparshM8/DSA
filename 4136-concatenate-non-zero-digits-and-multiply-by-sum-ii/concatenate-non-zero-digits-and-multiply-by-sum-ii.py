class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        MOD = 1000000007
        m = len(s)
        
        comp = []
        prev_nonzero = [-1] * m
        last_nz = -1
        
        for i, ch in enumerate(s):
            if ch != '0':
                comp.append(int(ch))
                last_nz = len(comp) - 1
            prev_nonzero[i] = last_nz
            
        sz = len(comp)
        next_nonzero = [sz] * m
        first_nz = sz
        comp_idx = sz - 1
        
        for i in range(m - 1, -1, -1):
            if s[i] != '0':
                first_nz = comp_idx
                comp_idx -= 1
            next_nonzero[i] = first_nz
            
        pref_sum = [0] * (sz + 1)
        pref_val = [0] * (sz + 1)
        pow10 = [1] * (sz + 1)
        
        for i in range(sz):
            pref_sum[i + 1] = pref_sum[i] + comp[i]
            pref_val[i + 1] = (pref_val[i] * 10 + comp[i]) % MOD
            pow10[i + 1] = (pow10[i] * 10) % MOD
            
        ans = []
        for l, r in queries:
            L = next_nonzero[l]
            R = prev_nonzero[r]
            
            if L > R:
                ans.append(0)
            else:
                length = R - L + 1
                digit_sum = pref_sum[R + 1] - pref_sum[L]
                
                val = (pref_val[R + 1] - (pref_val[L] * pow10[length]) % MOD) % MOD
                ans.append((val * digit_sum) % MOD)
                
        return ans