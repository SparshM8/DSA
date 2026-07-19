class Solution {
    public String smallestSubsequence(String s) {
        if(s.isEmpty()) return "";
        int [] count = new int[26];
        for(char c : s.toCharArray()) count[c-'a']++;
        int pos=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<s.charAt(pos)){
                pos=i;
            }
            if(--count[s.charAt(i)-'a']==0)
                break;
        }
        return s.charAt(pos) + smallestSubsequence(
            s.substring(pos + 1).replaceAll(String.valueOf(s.charAt(pos)), "")
        );
    }
}