/*
ID: 100021881
LANG: JAVA
PROG: fence6
*/

import java.io.*;
import java.util.*;

/**
 * find the minimum perimeter given a set of edges
 * @author  Johan Vonk <johan.d.s.vonk@gmail.com>
 */
public class fence6 {
    public static int N, min = Integer.MAX_VALUE;
    public static int[] len = new int[101];
    public static int[][] n = new int[2][101], flag = new int[101][101];
    public static int[][][] l = new int[2][101][101];
    public static boolean[] visit = new boolean[101];
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("fence6.in"));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            len[s] = Integer.parseInt(st.nextToken());
            n[0][s] = Integer.parseInt(st.nextToken());
            n[1][s] = Integer.parseInt(st.nextToken());
            for(int k = 0; k <= 1; k++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n[k][s]; ++j) {
                    l[k][s][j] = Integer.parseInt(st.nextToken());
                    flag[s][l[k][s][j]] = k;
                }
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
        for(int i = 1; i < N; ++i) {
            dfs(i, 0, 0);
        }
        out.println(min);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    /**
     * calculate minimum perimeter
     * @param s id of edge as given in input
     * @param f previous id
     * @param length    total perimeter so far
     */
    public static void dfs(int s, int f, int length) {
        visit[s] = true;
        int dir = 1-flag[s][f];
        for(int i = 0; i < n[dir][s]; ++i) {
            if(!visit[l[dir][s][i]]) {
                dfs(l[dir][s][i], s, length + len[s]);
            } else {
                min = Math.min(min, length + len[s]);
            }
        }
        visit[s] = false;
    }
}
