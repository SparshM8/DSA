class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        int Lowlen = String.valueOf(low).length();
        int Highlen = String. valueOf(high).length();

        List <Integer> ans = new ArrayList<>();
        for( int i = Lowlen; i<=Highlen;i++){
            for (int j= 0;j+i<=9;j++){
                String str = s.substring(j,j+i);
                int num = Integer.parseInt(str);
                if(num>=low && num<=high)
                ans.add(num);
            }
        }
        return ans;
    }
}