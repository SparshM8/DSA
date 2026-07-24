class Solution {
    public int uniqueXorTriplets(int[] nums) {
        
        boolean[] present = new boolean[2048];
        List<Integer> uniqueNums = new ArrayList<>();
        for (int num : nums) {
            if (!present[num]) {
                present[num] = true;
                uniqueNums.add(num);
            }
        }
        
        boolean[] pairXor = new boolean[2048];
        pairXor[0] = true; 
        int uSize = uniqueNums.size();
        for (int i = 0; i < uSize; i++) {
            int u1 = uniqueNums.get(i);
            for (int j = i + 1; j < uSize; j++) {
                int u2 = uniqueNums.get(j);
                pairXor[u1 ^ u2] = true;
            }
        }
        
        boolean[] tripletXor = new boolean[2048];
        int uniqueCount = 0;
        for (int px = 0; px < 2048; px++) {
            if (!pairXor[px]) continue;
            for (int u : uniqueNums) {
                int tx = px ^ u;
                if (!tripletXor[tx]) {
                    tripletXor[tx] = true;
                    uniqueCount++;
                }
            }
        } 
        return uniqueCount;
    }
}