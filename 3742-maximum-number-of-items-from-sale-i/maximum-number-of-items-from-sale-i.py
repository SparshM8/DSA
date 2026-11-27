from typing import List

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)

        g = [0] * n
        for i in range(n):
            fi = items[i][0]
            for j in range(n):
                if i != j and items[j][0] % fi == 0:
                    g[i] += 1

        dp = [0] * (budget + 1)

        for i in range(n):
            price, val = items[i][1], 1 + g[i]
            for c in range(budget, price - 1, -1):
                dp[c] = max(dp[c], dp[c - price] + val)

        for i in range(n):
            price = items[i][1]
            for c in range(price, budget + 1):
                dp[c] = max(dp[c], dp[c - price] + 1)

        return dp[budget]