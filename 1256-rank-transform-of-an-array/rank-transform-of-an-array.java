class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sort_arr = arr.clone();
        Arrays.sort(sort_arr);

        HashMap<Integer, Integer> mp = new HashMap<>();
        int rank =1;
        for(int num: sort_arr){
            if(!mp.containsKey(num))
                mp.put(num, rank++);
            }
        int []res = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            res[i]=mp.get(arr[i]);
        }
        return res;
    }
}