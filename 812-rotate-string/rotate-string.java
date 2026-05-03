class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        String word = s + s;

        for (int i = 0; i < s.length(); i++) {
            if (word.substring(i, i + s.length()).equals(goal)) {
                return true;
            }
        }
        return false;
    }
}