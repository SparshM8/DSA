class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        List<Integer> resultList = new ArrayList<>();
        for (int id : order) {
            if (Arrays.binarySearch(friends, id) >= 0) {
                resultList.add(id);
            }
        }
        return resultList.stream().mapToInt(i -> i).toArray();
    }
}