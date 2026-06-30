// class Solution {
//     public int numberOfSubstrings(String s) {
//         int[] count = new int[3]; // Tracks a, b, and c
//         int ans = 0, left = 0;
        
//         for (int right = 0; right < s.length(); right++) {
//             count[s.charAt(right) - 'a']++;
            
//             while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
//                 ans += s.length() - right;
//                 count[s.charAt(left) - 'a']--;
//                 left++;
//             }
//         }
//         return ans;
//     }
// }

// class Solution {
//     public int numberOfSubstrings(String s) {
//         int[] lastSeen = {-1, -1, -1};
//         int ans = 0;
        
//         for (int i = 0; i < s.length(); i++) {
//             lastSeen[s.charAt(i) - 'a'] = i;
            
//             int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            
//             ans += (minIndex + 1);
//         }
//         return ans;
//     }
// }

class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0;
        int[] last = {-1, -1, -1}; 
        
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
            
            int minIndex = Math.min(last[0], Math.min(last[1], last[2]));
            
            if (minIndex != -1) {
                ans += (minIndex + 1);
            }
        }
        return ans;
    }
}