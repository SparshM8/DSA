class Solution {
    public String maxSumOfSquares(int num, int sum) {

        String ans = "9".repeat((int)sum/9);
        
        sum = sum%9;

        if (sum > 9){
            return "";
        }

        else if (sum > 0){
            ans += Integer.toString(sum);
        }

        if (ans.length() > num){
            return "";
        }
        
        int k = num-ans.length();
        return ans + "0".repeat(k);


    }
}