// class Solution {
//     public int numOfStrings(String[] patterns, String word){
//         int ans = 0;
//         for (String str : patterns) {
//             if (word.indexOf(str) != -1) {
//                 ans++;
//             }
//         }
//         return ans;
//     }
// }

// class Solution {
//     public int numOfStrings(String[] patterns, String word) {
//         int ans = 0;
//         for (String str : patterns) {
//             if (word.indexOf(str) != -1) {
//                 ans++;
//             }
//         }
//         return ans;
//     }
// }

// class Solution {
//     public int numOfStrings(String[] patterns, String word) {
//         int ans = 0;
//         for (String str : patterns) {
//             // .* means "any characters before or after"
//             if (word.matches(".*" + Pattern.quote(str) + ".*")) {
//                 ans++;
//             }
//         }
//         return ans;
//     }
// }

// class Solution {
//     public int numOfStrings(String[] patterns, String word) {
//         int count = 0;
        
//         // Loop through each pattern
//         for (String pattern : patterns) {
//             // Read it out loud: "If the word contains the pattern, count it."
//             if (word.contains(pattern)) {
//                 count++;
//             }
//         }
        
//         return count;
//     }
// }


class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            // Check each pattern using our custom advanced KMP search
            if (kmpSearch(pattern, word)) {
                count++;
            }
        }
        return count;
    }
    
    // Advanced KMP String Matching
    private boolean kmpSearch(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        if (m > n) return false;
        
        // 1. Build the LPS (Longest Prefix Suffix) Array
        int[] lps = new int[m];
        int length = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1]; // Fall back without resetting fully
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        // 2. Perform the actual search without ever moving 'j' backward
        int j = 0; // index for text
        int k = 0; // index for pattern
        while (j < n) {
            if (pattern.charAt(k) == text.charAt(j)) {
                j++;
                k++;
            }
            if (k == m) {
                return true; // The whole pattern was matched
            } else if (j < n && pattern.charAt(k) != text.charAt(j)) {
                if (k != 0) {
                    k = lps[k - 1]; // Skip ahead using the LPS map
                } else {
                    j++;
                }
            }
        }
        return false;
    }
}