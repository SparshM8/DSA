class Solution {
    public int totalFruit(int[] arr) {
        
          int n=arr.length; 
         int[] freq=new int[n+1];
         int i=0;
         int count=0;
         int max=0;
         for(int j=0;j<arr.length;j++)
         {
              if(freq[arr[j]]==0)
              {
                count++;
              }
              freq[arr[j]]++;
              while(count>2)
              {
                freq[arr[i]]--;
                if(freq[arr[i]]==0)
                {
                    count--;
                }
                i++;

              }
              max=Math.max(max,j-i+1);

         }
         return max;
    }
}