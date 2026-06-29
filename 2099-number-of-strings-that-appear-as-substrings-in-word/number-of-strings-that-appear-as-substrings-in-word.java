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

class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        
        // Loop through each pattern
        for (String pattern : patterns) {
            // Read it out loud: "If the word contains the pattern, count it."
            if (word.contains(pattern)) {
                count++;
            }
        }
        
        return count;
    }
}