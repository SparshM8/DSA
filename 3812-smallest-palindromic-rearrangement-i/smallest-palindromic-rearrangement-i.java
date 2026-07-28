class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder leftHalf = new StringBuilder();
        String middleChar = "";
        
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            int freq = count[i];
            
            for (int j = 0; j < freq / 2; j++) {
                leftHalf.append(c);
            }
            if (freq % 2 != 0) {
                middleChar = String.valueOf(c);
            }
        }
        String rightHalf = new StringBuilder(leftHalf).reverse().toString();
        return leftHalf.toString() + middleChar + rightHalf;
    }
}