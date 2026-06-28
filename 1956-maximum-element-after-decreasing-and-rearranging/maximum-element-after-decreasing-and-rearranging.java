// class Solution {
//     public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
//         int n = arr.length;
//         Arrays.sort(arr);
//         if (arr[0] != 1) {
//             arr[0] = 1;
//         }
//         for (int i = 1; i < n; i++) {
//             if (arr[i] - arr[i - 1] > 1) {
//                 arr[i] = arr[i - 1] + 1;
//             }
//         }
//         return arr[n - 1];
//     }
// }

// class Solution {
//     public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
//         Arrays.sort(arr);
//         int maxElement = 0;
//         for (int num : arr) {
//             maxElement = Math.min(maxElement + 1, num);
//         }
//         return maxElement;
//     }
// }

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];
        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }
        int maxElement = 0;
        
        for (int i = 1; i <= n; i++) {
            maxElement = Math.min(maxElement + counts[i], i);
        }
        return maxElement;
    }
}