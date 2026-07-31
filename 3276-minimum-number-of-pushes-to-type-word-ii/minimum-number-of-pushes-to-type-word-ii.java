class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }
        
        Arrays.sort(freq);
        // reverse to descending
        for (int i = 0, j = 25; i < j; i++, j--) {
            int temp = freq[i];
            freq[i] = freq[j];
            freq[j] = temp;
        }
        
        int total = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) break;
            total += freq[i] * ((i / 8) + 1);
        }
        
        return total;
    }
}