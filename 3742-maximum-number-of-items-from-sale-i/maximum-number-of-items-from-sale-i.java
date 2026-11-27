class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    g[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1], val = 1 + g[i];
            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + val);
            }
        }

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            for (int c = price; c <= budget; c++) {
                dp[c] = Math.max(dp[c], dp[c - price] + 1);
            }
        }

        return dp[budget];
    }
}