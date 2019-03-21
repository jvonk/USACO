/*
ID: 100021881
LANG: JAVA
PROG: bigbrn
*/

import java.io.*;

public class bigbrn {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("bigbrn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int T = Integer.parseInt(line[1]);
        int[][] values = new int[N][N];
        boolean[][] trees = new boolean[N][N];
        for (int i = 0; i < T; i++) {
            line = br.readLine().split(" ");
            trees[Integer.parseInt(line[0])-1][Integer.parseInt(line[1])-1] = true;
        }
        for (int i = 0; i < N; i++) {
            values[i][0] = (trees[i][0])?0:1;
            values[0][i] = (trees[0][i])?0:1;
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i>0&&j>0) {
                    int m = trees[i][j] ? 0 : Math.min(values[i - 1][j], values[i][j - 1]);
                    values[i][j] = m + (trees[i][j] ? 0 : ((m == 0 || values[i - m][j - m] > 0) ? 1 : 0));
                }
                max = Math.max(max, values[i][j]);
            }
        }
        out.println(max);
        br.close();
        out.close();
    }
}