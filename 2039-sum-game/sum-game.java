class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int diff = 0;
        int leftQuestions = 0;
        int rightQuestions = 0;

        for (int i = 0; i < half; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQuestions++;
            } else {
                diff += ch - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQuestions++;
            } else {
                diff -= ch - '0';
            }
        }
        if ((leftQuestions + rightQuestions) % 2 == 1) {
            return true;
        }
        return 2 * diff != 9 * (rightQuestions - leftQuestions);
    }
}
