/*
ID: 100021881
LANG: JAVA
PROG: telecow
*/

import java.util.*;
import java.io.*;

public class telecow {
    static int n, m, st, en, tot, head, tail, ans;
    static int[] res = new int[MAXN], qu = new int[MAXN], level = new int[MAXN], cut = new int[MAXN];
    static int[][] g = new int[MAXN][MAXN], backup = new int[MAXN][MAXN];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("telecow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("telecow.out")));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        st = Integer.parseInt(line[2]);
        en = Integer.parseInt(line[3]);
        tot = n*2+1;
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            g[a+n][b] =
        }
        br.close();
        out.close();
    }
    public static class Edge {
        int u, v;
        long w;
        public Edge(int a, int b) {
            u=a;
            v=b;
        }
    }
}