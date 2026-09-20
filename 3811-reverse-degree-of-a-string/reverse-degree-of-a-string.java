class Solution {
    public int reverseDegree(String s) {
        return IntStream.range(0,s.length()).map(i->('z'-s.charAt(i)+1)*(i+1)).sum();
    }
}