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
      ListNode prev=null, curr=head;
      ArrayList<Integer> points=new ArrayList<>();
      int[] res={-1,-1};
      int idx=1;

      while(curr.next!=null)
      {
        if(prev==null)
        {
          prev=curr;
          curr=curr.next;
          idx++;
          continue;
        }
        // condition for Local maxima
        if(prev.val<curr.val && curr.val>curr.next.val)
          points.add(idx);
        
        // condition for Local minima
        if(prev.val>curr.val && curr.val<curr.next.val)
          points.add(idx);

         prev=curr;
         curr=curr.next;
         idx++;
      }

       if(points.size()>=2)
       {
         int min=Integer.MAX_VALUE;
         for(int i=1;i<points.size();i++)
         {
            int dist=points.get(i)-points.get(i-1);
            if(dist<min)
              min=dist;
         }
         res[0]=min;
         res[1]=points.get(points.size()-1)-points.get(0);
       }

       return res; 
    }
}