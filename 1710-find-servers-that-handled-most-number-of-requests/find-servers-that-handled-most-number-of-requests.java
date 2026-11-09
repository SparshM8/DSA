class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] cnt = new int[k];
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]); // {end time, server}
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) free.add(busy.poll()[1]);
            if (free.isEmpty()) continue;
            Integer server = free.ceiling(i % k);
            if (server == null) server = free.first();
            free.remove(server);
            busy.add(new int[]{arrival[i] + load[i], server});
            cnt[server]++;
        }
        int max = 0;
        for (int c : cnt) max = Math.max(max, c);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) if (cnt[i] == max) ans.add(i);
        return ans;
    }
}