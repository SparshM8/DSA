class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int[] altitudes = new int[n + 1];
        altitudes[0] = 0; 
        
        int maxAlt = 0;
        
        for (int i = 0; i < n; i++) {
            altitudes[i + 1] = altitudes[i] + gain[i];
        }
        
        for (int alt : altitudes) {
            maxAlt = Math.max(maxAlt, alt);
        }
        
        return maxAlt;
    }
}


// class Solution {
//     public int largestAltitude(int[] gain) {
//         int currentAltitude = 0;
//         int maxAltitude = 0;
        
//         for (int i = 0; i < gain.length; i++) {
//             // Update the current altitude
//             currentAltitude += gain[i];
            
//             // Keep track of the maximum altitude seen so far
//             maxAltitude = Math.max(maxAltitude, currentAltitude);
//         }
        
//         return maxAltitude;
//     }
// }