class Solution {
    public int smallestNumber(int n, int t) {
        int candidate = n;
        while (digitProduct(candidate) % t != 0) {
            candidate++;
        }
        return candidate;
    }
    
    private long digitProduct(int num) {
        long product = 1;
        while (num > 0) {
            product *= num % 10;
            num /= 10;
        }
        return product;
    }
}