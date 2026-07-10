import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] pos = new Integer[n];
        for (int i = 0; i < n; i++) pos[i] = i;
        Arrays.sort(pos, (a, b) -> Integer.compare(nums[a], nums[b]));
        
        int[] sortedPos = new int[n];
        for (int i = 0; i < n; i++) {
            sortedPos[pos[i]] = i;
        }
        
        int LOG = 18; 
        int[][] st = new int[n][LOG];
        
        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right < n && nums[pos[right]] - nums[pos[i]] <= maxDiff) {
                right++;
            }
            st[i][0] = right - 1; 
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                st[i][j] = st[st[i][j - 1]][j - 1];
            }
        }
        
        int[] ans = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int u = sortedPos[queries[i][0]];
            int v = sortedPos[queries[i][1]];
            
            if (u > v) {
                int temp = u; u = v; v = temp;
            }
            
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            
            int curr = u;
            int steps = 0;
            
            for (int j = LOG - 1; j >= 0; j--) {
                if (st[curr][j] < v) {
                    curr = st[curr][j];
                    steps += (1 << j);
                }
            }
            
            if (st[curr][0] >= v) {
                ans[i] = steps + 1;
            } else {
                ans[i] = -1; 
            }
        }
        
        return ans;
    }
}