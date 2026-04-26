class Solution {
     public static int scoreOfString(String s) {
        int sum=0;
        for (int i = 0; i < s.length()-1; i++) {

            int ascii1=s.charAt(i);
            int ascii2=s.charAt(i+1);
            
            int diff=Math.abs(ascii1-ascii2);
            sum=sum+diff;
        }
        return sum;
    }
}