class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[int]:
        pos = sorted(range(n), key=lambda x: nums[x])
        
        sorted_pos = [0] * n
        for idx, orig in enumerate(pos):
            sorted_pos[orig] = idx
            
        LOG = 18
        st = [[0] * LOG for _ in range(n)]
        
        right = 0
        for i in range(n):
            while right < n and nums[pos[right]] - nums[pos[i]] <= maxDiff:
                right += 1
            st[i][0] = right - 1
            
        for j in range(1, LOG):
            for i in range(n):
                st[i][j] = st[st[i][j-1]][j-1]
                
        ans = []
        
        for u_orig, v_orig in queries:
            u = sorted_pos[u_orig]
            v = sorted_pos[v_orig]
            
            if u > v:
                u, v = v, u
                
            if u == v:
                ans.append(0)
                continue
                
            curr = u
            steps = 0
            
            for j in range(LOG - 1, -1, -1):
                if st[curr][j] < v:
                    curr = st[curr][j]
                    steps += (1 << j)
                    
            if st[curr][0] >= v:
                ans.append(steps + 1)
            else:
                ans.append(-1)
                
        return ans