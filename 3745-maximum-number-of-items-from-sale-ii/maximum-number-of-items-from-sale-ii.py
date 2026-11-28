from collections import defaultdict
from typing import List


class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        max_factor = max(factor for factor, _ in items)
        min_price = min(price for _, price in items)

        freq = [0] * (max_factor + 1)
        for factor, _ in items:
            freq[factor] += 1

        strict_multiples = [0] * (max_factor + 1)
        for d in range(1, max_factor + 1):
            total = 0
            for m in range(2 * d, max_factor + 1, d):
                total += freq[m]
            strict_multiples[d] = total

        boosted_by_price = defaultdict(int)
        threshold = 2 * min_price

        for factor, price in items:
            gain = strict_multiples[factor] + (freq[factor] - 1)
            if gain > 0 and price < threshold:
                boosted_by_price[price] += gain

        rem_budget = budget
        total_items = 0

        for price in sorted(boosted_by_price):
            if rem_budget < price:
                break
            avail = boosted_by_price[price]
            buy = min(avail, rem_budget // price)
            total_items += 2 * buy
            rem_budget -= buy * price

        total_items += rem_budget // min_price
        return total_items