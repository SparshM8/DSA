class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] minDamage = new int[m][n];
        for (int[] row : minDamage) {
            Arrays.fill(row, Integer.MAX_VALUE); 
        }
        
        Deque<int[]> deque = new LinkedList<>();
        
        int startDamage = grid.get(0).get(0);
        minDamage[0][0] = startDamage;
        deque.offerFirst(new int[]{0, 0});
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            if (r == m - 1 && c == n - 1) {
                return (health - minDamage[r][c]) >= 1;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int damage = grid.get(nr).get(nc);
                    
                    if (minDamage[r][c] + damage < minDamage[nr][nc]) {
                        minDamage[nr][nc] = minDamage[r][c] + damage;
                        
                        if (damage == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return false;
    }
}