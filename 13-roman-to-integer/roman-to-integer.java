class Solution {
    public int getVal(char ch1){
        switch(ch1){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default: return 1000;
        }
    }

    public int romanToInt(String s) {
        int sum = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            if ((i + 1) < n && getVal(ch1) < getVal(s.charAt(i + 1))) {
                sum = sum - getVal(ch1);
            } else {
                sum = sum + getVal(ch1);
            }
        }
        return sum;
    }
}
