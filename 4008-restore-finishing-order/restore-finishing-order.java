import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        Set<Integer> friendSet = Arrays.stream(friends)
                                       .boxed()
                                       .collect(Collectors.toSet());
        
        return Arrays.stream(order)
                     .filter(friendSet::contains)
                     .toArray();
    }
}