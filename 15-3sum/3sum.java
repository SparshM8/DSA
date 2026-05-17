class Solution {
    public List<List<Integer>> threeSum(int[] nums) { 
        List<List<Integer>> arr = new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length - 1;
            while (j<k){
                int s = nums[i]+ nums[j]+ nums[k];
                if (s > 0){
                    k -= 1;
                }
                else if (s < 0){
                    j += 1;
                }
                else{
                    arr.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[k]))); 
                    j+=1;
                    while (j<k && nums[j] == nums[j-1]){
                        j+=1;
                    }
                }
            }
        }
        return arr;
    }
}