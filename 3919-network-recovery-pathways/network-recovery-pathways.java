import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        for (int[] e : edges) {
            int u = e[0], v = e[1], cost = e[2];
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] next : adj[u]) {
                int v = next[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        int low = 0, high = 1000000000;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canReach(topo, adj, online, mid, k, n)) {
                ans = mid;       
                low = mid + 1;   
            } else {
                high = mid - 1;  
            }
        }
        
        return ans;
    }
    
    private boolean canReach(List<Integer> topo, List<int[]>[] adj, boolean[] online, int mid, long k, int n) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        
        for (int u : topo) {
            if (dp[u] == Long.MAX_VALUE) continue;
            
            if (u != 0 && u != n - 1 && !online[u]) continue;
            
            for (int[] next : adj[u]) {
                int v = next[0];
                int cost = next[1];
                
                if (cost >= mid) {
                    if (v == n - 1 || online[v]) {
                        if (dp[u] + cost < dp[v]) {
                            dp[v] = dp[u] + cost;
                        }
                    }
                }
            }
        }
        
        return dp[n - 1] <= k;
    }
}