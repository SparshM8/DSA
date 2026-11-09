class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) available.add(i);

        PriorityQueue<int[]> busy =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) continue;

            int pos = i % k;
            Integer server = available.ceiling(pos);
            if (server == null) server = available.first();  

            available.remove(server);
            busy.add(new int[]{start + load[i], server});
            count[server]++;
        }

        int max = 0;
        for (int c : count) max = Math.max(max, c);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) if (count[i] == max) res.add(i);
        return res;
    }
}