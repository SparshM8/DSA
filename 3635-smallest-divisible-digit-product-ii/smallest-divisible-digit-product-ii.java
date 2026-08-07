import java.util.*;

class Solution {
    static final int[][] DIGIT_FACTORS = {
        {0,0,0,0}, {0,0,0,0}, {1,0,0,0}, {0,1,0,0}, {2,0,0,0},
        {0,0,1,0}, {1,1,0,0}, {0,0,0,1}, {3,0,0,0}, {0,2,0,0},
    };

    Map<Long, Integer> minCountMemo = new HashMap<>();
    Map<Long, Integer> hMemo = new HashMap<>();

    private long key(int a, int b, int c, int d) {
        return ((long)a << 24) | ((long)b << 16) | ((long)c << 8) | d;
    }

    private int minCount(int a, int b, int c, int d) {
        if (a==0 && b==0 && c==0 && d==0) return 0;
        long k = key(a,b,c,d);
        Integer cached = minCountMemo.get(k);
        if (cached != null) return cached;
        int best = Integer.MAX_VALUE;
        for (int dig = 2; dig <= 9; dig++) {
            int[] f = DIGIT_FACTORS[dig];
            int na = Math.max(0, a-f[0]), nb = Math.max(0, b-f[1]),
                nc = Math.max(0, c-f[2]), nd = Math.max(0, d-f[3]);
            if (na==a && nb==b && nc==c && nd==d) continue;
            int val = 1 + minCount(na, nb, nc, nd);
            if (val < best) best = val;
        }
        minCountMemo.put(k, best);
        return best;
    }

    private long keyLo(int lo, int a, int b, int c, int d) {
        return ((long)lo << 32) | key(a,b,c,d);
    }

    private int h(int lo, int a, int b, int c, int d) {
        if (a==0 && b==0 && c==0 && d==0) return 0;
        if (lo > 9) return Integer.MAX_VALUE / 2;
        long k = keyLo(lo, a,b,c,d);
        Integer cached = hMemo.get(k);
        if (cached != null) return cached;
        int best = h(lo+1, a,b,c,d);
        int[] f = DIGIT_FACTORS[lo];
        int na = Math.max(0, a-f[0]), nb = Math.max(0, b-f[1]),
            nc = Math.max(0, c-f[2]), nd = Math.max(0, d-f[3]);
        if (!(na==a && nb==b && nc==c && nd==d)) {
            int val = 1 + h(lo, na, nb, nc, nd);
            if (val < best) best = val;
        }
        hMemo.put(k, best);
        return best;
    }

    private String buildMinSuffix(int a, int b, int c, int d, int length) {
        int mc = minCount(a,b,c,d);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - mc; i++) sb.append('1');

        int lo = 2, ca = a, cb = b, cc = c, cd = d, remaining = mc;
        while (remaining > 0) {
            while (true) {
                int[] f = DIGIT_FACTORS[lo];
                int na = Math.max(0, ca-f[0]), nb = Math.max(0, cb-f[1]),
                    nc = Math.max(0, cc-f[2]), nd = Math.max(0, cd-f[3]);
                if (h(lo, na, nb, nc, nd) == remaining - 1) {
                    sb.append((char)('0' + lo));
                    ca = na; cb = nb; cc = nc; cd = nd;
                    remaining--;
                    break;
                }
                lo++;
            }
        }
        return sb.toString();
    }

    public String smallestNumber(String num, long t) {
        long rem = t;
        int e2=0, e3=0, e5=0, e7=0;
        while (rem % 2 == 0) { rem /= 2; e2++; }
        while (rem % 3 == 0) { rem /= 3; e3++; }
        while (rem % 5 == 0) { rem /= 5; e5++; }
        while (rem % 7 == 0) { rem /= 7; e7++; }
        if (rem != 1) return "-1";

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = num.charAt(i) - '0';

        int z = -1;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) { z = i; break; }
        }

        int[][] prefixUsed = new int[n+1][4];
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                prefixUsed[i+1] = prefixUsed[i].clone();
                continue;
            }
            int[] f = DIGIT_FACTORS[digits[i]];
            prefixUsed[i+1] = new int[]{
                prefixUsed[i][0]+f[0], prefixUsed[i][1]+f[1],
                prefixUsed[i][2]+f[2], prefixUsed[i][3]+f[3]
            };
        }

        if (z == -1) {
            int la = Math.max(0, e2-prefixUsed[n][0]);
            int lb = Math.max(0, e3-prefixUsed[n][1]);
            int lc = Math.max(0, e5-prefixUsed[n][2]);
            int ld = Math.max(0, e7-prefixUsed[n][3]);
            if (la==0 && lb==0 && lc==0 && ld==0) return num;
        }

        int limitI = (z == -1) ? (n-1) : z;
        for (int i = limitI; i >= 0; i--) {
            int[] u = prefixUsed[i];
            int la = Math.max(0, e2-u[0]), lb = Math.max(0, e3-u[1]),
                lc = Math.max(0, e5-u[2]), ld = Math.max(0, e7-u[3]);
            int start = (digits[i] != 0) ? digits[i]+1 : 1;
            for (int d = start; d <= 9; d++) {
                int[] f = DIGIT_FACTORS[d];
                int na = Math.max(0, la-f[0]), nb = Math.max(0, lb-f[1]),
                    nc = Math.max(0, lc-f[2]), nd = Math.max(0, ld-f[3]);
                int remainingLen = n-1-i;
                if (minCount(na,nb,nc,nd) <= remainingLen) {
                    String prefix = num.substring(0, i);
                    String suffix = buildMinSuffix(na,nb,nc,nd, remainingLen);
                    return prefix + d + suffix;
                }
            }
        }

        int mcFull = minCount(e2,e3,e5,e7);
        int lp = Math.max(n+1, mcFull);
        return buildMinSuffix(e2,e3,e5,e7, lp);
    }
}