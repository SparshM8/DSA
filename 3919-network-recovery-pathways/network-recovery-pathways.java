class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int maxWeight = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] e : edges) {
            int fromNode = e[0];
            int toNode = e[1];
            int weight = e[2];

            graph.computeIfAbsent(fromNode, x -> new ArrayList<>()).add(new int[]{toNode, weight});
            maxWeight = Math.max(maxWeight, weight);
        }

        int left = -1;
        int right = maxWeight + 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid, graph, online, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right >= 0 ? right : -1;
    }

    private boolean check(int minWeight, Map<Integer, List<int[]>> graph, boolean[] online, long k) {
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        heap.offer(new long[]{0, 0});

        Map<Integer, Long> bestCost = new HashMap<>();

        while (!heap.isEmpty()) {
            long[] cur = heap.poll();
            long currentCost = cur[0];
            int currentNode = (int) cur[1];

            if (currentNode == online.length - 1) {
                return true;
            }

            if (bestCost.containsKey(currentNode) && bestCost.get(currentNode) <= currentCost) {
                continue;
            }

            bestCost.put(currentNode, currentCost);

            for (int[] edge : graph.getOrDefault(currentNode, new ArrayList<>())) {
                int nextNode = edge[0];
                int edgeWeight = edge[1];

                if (currentCost + edgeWeight > k) {
                    continue;
                }

                if (!online[nextNode]) {
                    continue;
                }

                if (edgeWeight < minWeight) {
                    continue;
                }

                heap.offer(new long[]{currentCost + edgeWeight, nextNode});
            }
        }

        return false;
    }
}