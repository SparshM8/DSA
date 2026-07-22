import math
from bisect import bisect_right, bisect_left
from typing import List

class SparseTableMax:
    def __init__(self, arr):
        n = len(arr)
        if n == 0: return
        self.k = int(math.log2(n)) + 1
        self.st = [[0] * self.k for _ in range(n)]
        for i in range(n):
            self.st[i][0] = arr[i]
        for j in range(1, self.k):
            for i in range(n - (1 << j) + 1):
                self.st[i][j] = max(self.st[i][j - 1], self.st[i + (1 << (j - 1))][j - 1])
                
    def query(self, L, R):
        if L > R: return 0
        j = int(math.log2(R - L + 1))
        return max(self.st[L][j], self.st[R - (1 << j) + 1][j])

class SparseTableMin:
    def __init__(self, arr):
        n = len(arr)
        if n == 0: return
        self.k = int(math.log2(n)) + 1
        self.st = [[float('inf')] * self.k for _ in range(n)]
        for i in range(n):
            self.st[i][0] = arr[i]
        for j in range(1, self.k):
            for i in range(n - (1 << j) + 1):
                self.st[i][j] = min(self.st[i][j - 1], self.st[i + (1 << (j - 1))][j - 1])
                
    def query(self, L, R):
        if L > R: return float('inf')
        j = int(math.log2(R - L + 1))
        return min(self.st[L][j], self.st[R - (1 << j) + 1][j])

class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        total_ones_in_s = s.count('1')
        
        segments = []
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            segments.append({'type': s[i], 'start': i, 'end': j - 1, 'len': j - i})
            i = j
            
        m = len(segments)
        starts = [seg['start'] for seg in segments]
        ones_indices = [idx for idx, seg in enumerate(segments) if seg['type'] == '1']
        
        arr_0_len = [seg['len'] if seg['type'] == '0' else 0 for seg in segments]
        arr_1_len = [seg['len'] if seg['type'] == '1' else float('inf') for seg in segments]
        arr_V = [0] * m
        
        for idx in ones_indices:
            left_0 = segments[idx - 1]['len'] if idx > 0 else 0
            right_0 = segments[idx + 1]['len'] if idx < m - 1 else 0
            arr_V[idx] = left_0 + right_0
            
        st_max_0 = SparseTableMax(arr_0_len)
        st_max_V = SparseTableMax(arr_V)
        st_min_1 = SparseTableMin(arr_1_len)
        
        ans = []
        for l, r in queries:
            a = bisect_right(starts, l) - 1
            b = bisect_right(starts, r) - 1
            
            M0 = 0
            if a == b:
                if segments[a]['type'] == '0':
                    M0 = r - l + 1
            else:
                if segments[a]['type'] == '0':
                    M0 = max(M0, segments[a]['end'] - l + 1)
                if segments[b]['type'] == '0':
                    M0 = max(M0, r - segments[b]['start'] + 1)
                if a + 1 <= b - 1:
                    M0 = max(M0, st_max_0.query(a + 1, b - 1))
                    
            idx_first = bisect_left(ones_indices, a + 1)
            idx_last = bisect_right(ones_indices, b - 1) - 1
            
            if idx_first > idx_last: 
                ans.append(total_ones_in_s)
                continue
                
            k1 = ones_indices[idx_first]
            kp = ones_indices[idx_last]
            
            left_0_len_k1 = segments[k1 - 1]['end'] - max(segments[k1 - 1]['start'], l) + 1
            right_0_len_k1 = min(segments[k1 + 1]['end'], r) - segments[k1 + 1]['start'] + 1
            gain_1 = max(left_0_len_k1 + right_0_len_k1, M0 - segments[k1]['len'])
            
            total_gain = gain_1
            
            if idx_first < idx_last:
                left_0_len_kp = segments[kp - 1]['end'] - max(segments[kp - 1]['start'], l) + 1
                right_0_len_kp = min(segments[kp + 1]['end'], r) - segments[kp + 1]['start'] + 1
                gain_p = max(left_0_len_kp + right_0_len_kp, M0 - segments[kp]['len'])
                total_gain = max(total_gain, gain_p)
                
                if idx_first + 1 <= idx_last - 1:
                    mid_start = ones_indices[idx_first + 1]
                    mid_end = ones_indices[idx_last - 1]
                    
                    max_V_mid = st_max_V.query(mid_start, mid_end)
                    min_len_mid = st_min_1.query(mid_start, mid_end)
                    
                    gain_mid = max(max_V_mid, M0 - min_len_mid)
                    total_gain = max(total_gain, gain_mid)
            
            ans.append(total_ones_in_s + total_gain)
            
        return ans