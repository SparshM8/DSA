class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k = k % total;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int currentIndex = i * n + j;
                int originalIndex = (currentIndex - k + total) % total;
                int r = originalIndex / n;
                int c = originalIndex % n;
                row.add(grid[r][c]);
            }
            result.add(row);
        }
        return result;
    }
}