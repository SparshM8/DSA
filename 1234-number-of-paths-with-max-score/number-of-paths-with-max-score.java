class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        int[][] score = new int[n][n];
        int[][] paths = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score[i][j] = -1;
            }
        }
        
        score[n - 1][n - 1] = 0;
        paths[n - 1][n - 1] = 1;
        
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                char ch = board.get(r).charAt(c);
                
                if (ch == 'X' || (r == n - 1 && c == n - 1)) {
                    continue;
                }
                
                int maxPrevScore = -1;
                int pathCount = 0;
                
                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}}; 
                for (int[] dir : dirs) {
                    int prevR = r + dir[0];
                    int prevC = c + dir[1];
                    
                    if (prevR < n && prevC < n && score[prevR][prevC] != -1) {
                        if (score[prevR][prevC] > maxPrevScore) {
                            maxPrevScore = score[prevR][prevC];
                            pathCount = paths[prevR][prevC];
                        } else if (score[prevR][prevC] == maxPrevScore) {
                            pathCount = (pathCount + paths[prevR][prevC]) % MOD;
                        }
                    }
                }
                
                if (maxPrevScore != -1) {
                    int currentVal = (ch == 'E') ? 0 : (ch - '0');
                    score[r][c] = maxPrevScore + currentVal;
                    paths[r][c] = pathCount;
                }
            }
        }
        
        if (score[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{score[0][0], paths[0][0]};
    }
}