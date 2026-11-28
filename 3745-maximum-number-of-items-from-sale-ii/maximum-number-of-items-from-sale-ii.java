import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // freq[f] = count of items with factor == f
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // strictMultiples[f] = count of items with factor strictly divisible by f (multiple > f)
        long[] strictMultiples = new long[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            long sum = 0;
            for (int m = 2 * d; m <= maxFactor; m += d) {
                sum += freq[m];
            }
            strictMultiples[d] = sum;
        }

        // Collect profitable boosted batches: price < 2 * minPrice gives 2 items per purchase
        // Map price -> total boosted copy slots available at that price
        TreeMap<Long, Long> boostedByPrice = new TreeMap<>();
        long threshold = 2L * minPrice;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            // Other items with same factor also have factor_i dividing factor_j (i != j)
            long gain = strictMultiples[factor] + (freq[factor] - 1);

            if (gain > 0 && price < threshold) {
                boostedByPrice.put(price, boostedByPrice.getOrDefault(price, 0L) + gain);
            }
        }

        long remBudget = budget;
        long totalItems = 0;

        for (Map.Entry<Long, Long> entry : boostedByPrice.entrySet()) {
            long price = entry.getKey();
            long avail = entry.getValue();
            if (remBudget < price) break;

            long buy = Math.min(avail, remBudget / price);
            totalItems += 2L * buy;
            remBudget -= buy * price;
        }

        // Spend remaining budget on the cheapest item (1 item per minPrice)
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}