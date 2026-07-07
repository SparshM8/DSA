class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }
        long x = 0;           
        long sum = 0;      
        long multiplier = 1;  
       
        while (n > 0) {
            int digit = n % 10;  
            n = n / 10;          
            if (digit != 0) {
                x = x + (digit * multiplier);
                sum += digit;
                multiplier *= 10;
            }
        }
        return x * sum;
    }
}