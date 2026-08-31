/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] res = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int idx = 2;

        ArrayList<Integer> points = new ArrayList<>();

        while (curr.next != null) {

            // Local maxima
            if (curr.val > prev.val && curr.val > curr.next.val) {
                points.add(idx);
            }

            // Local minima
            if (curr.val < prev.val && curr.val < curr.next.val) {
                points.add(idx);
            }

            prev = curr;
            curr = curr.next;
            idx++;
        }

        if (points.size() < 2) {
            return res;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i < points.size(); i++) {
            min = Math.min(min, points.get(i) - points.get(i - 1));
        }

        int max = points.get(points.size() - 1) - points.get(0);

        return new int[]{min, max};
    }
}