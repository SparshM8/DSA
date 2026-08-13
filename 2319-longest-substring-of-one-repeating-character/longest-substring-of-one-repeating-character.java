class Solution {
    char[] LC, RC;
    int[] LL, RL, ML, SZ;
    char[] s;
    int n;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {
        n = str.length();
        s = str.toCharArray();
        LC = new char[4*n];
        RC = new char[4*n];
        LL = new int[4*n];
        RL = new int[4*n];
        ML = new int[4*n];
        SZ = new int[4*n];

        build(1, 0, n-1);

        int k = queryIndices.length;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n-1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = ML[1];
        }
        return result;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            LC[node] = RC[node] = s[l];
            LL[node] = RL[node] = ML[node] = 1;
            SZ[node] = 1;
            return;
        }
        int mid = (l+r)/2;
        build(2*node, l, mid);
        build(2*node+1, mid+1, r);
        pull(node);
    }

    private void pull(int node) {
        int left = 2*node, right = 2*node+1;
        SZ[node] = SZ[left] + SZ[right];
        LC[node] = LC[left];
        RC[node] = RC[right];

        int leftLen = LL[left];
        if (LL[left] == SZ[left] && LC[left] == LC[right]) {
            leftLen = SZ[left] + LL[right];
        }
        LL[node] = leftLen;

        int rightLen = RL[right];
        if (RL[right] == SZ[right] && RC[right] == RC[left]) {
            rightLen = SZ[right] + RL[left];
        }
        RL[node] = rightLen;

        int midMerge = 0;
        if (RC[left] == LC[right]) {
            midMerge = RL[left] + LL[right];
        }

        ML[node] = Math.max(Math.max(ML[left], ML[right]), Math.max(midMerge, Math.max(leftLen, rightLen)));
    }

    private void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            LC[node] = RC[node] = ch;
            LL[node] = RL[node] = ML[node] = 1;
            SZ[node] = 1;
            return;
        }
        int mid = (l+r)/2;
        if (idx <= mid) update(2*node, l, mid, idx, ch);
        else update(2*node+1, mid+1, r, idx, ch);
        pull(node);
    }
}