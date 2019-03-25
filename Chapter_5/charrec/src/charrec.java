/*
ID: 100021881
LANG: JAVA
PROG: charrec
*/

import java.util.*;
import java.io.*;

public class charrec {
    static String letters = "_abcdefghijklmnopqrstuvwxyz";
    static int N;
    static boolean[][][] font;
    static boolean[][] input;
    static int[][][] diff;
    static int[][] cost, from;
    static int[] best, last;
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("charrec.out")));
        boolean[][] fin = read(new BufferedReader(new FileReader("font.in")));
        input = read(new BufferedReader(new FileReader("charrec.in")));
        font = new boolean[fin.length/20][20][20];
        diff = new int[font.length][20][N];
        cost = new int[N][21];
        from = new int[N][21];
        best = new int[N];
        last = new int[N];
        for (int i = 0; i < font.length; i++) {
            font[i/20][i%20]=fin[i];
        }
        for (int i = 0; i < font.length; i++) {
            for (int j = 0; j < font[i].length; j++) {
                for (int k = 0; k < input.length; k++) {
                    for (int l = 0; l < input[k].length; l++) {
                        if (font[i][j][l]!=input[k][l]) diff[i][j][k]++;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            //difference for each input line
            //do skipping and doubling up too
            if (i+18<N) {
                for (int j = 0; j < font.length; j++) {
                    //difference for each character
                    total=0;
                    for (int k = 1; k < diff[j].length; k++) {
                        //difference for each font line
                        total+=diff[j][k][i+k-1];
                    }
                    if (total<cost[i][19]) {
                        //set minimum j
                        cost[i][19]=total;
                        from[i][19]=j;
                    }
                    for (int k = 1; k < diff[j].length; k++) {
                        total+=diff[j][k-1][i+k-1];
                        total-=diff[j][k][i+k-1];
                        if (total<cost[i][19]) {
                            //set minimum j;
                            cost[i][19]=total;
                            from[i][19]=j;
                        }
                    }
                }
            }
            if (i+19<N) {
                for (int j = 0; j < font.length; j++) {
                    //difference for each character
                    total=0;
                    for (int k = 0; k < diff[j].length; k++) {
                        //difference for each font line
                        total+=diff[j][k][i+k];
                    }
                    if (total<cost[i][20]) {
                        //set minimum j
                        cost[i][20]=total;
                        from[i][20]=j;
                    }
                }
            }
            if (i+20<N) {
                for (int j = 0; j < font.length; j++) {
                    //difference for each character
                    total=0;
                    for (int k = 0; k < diff[j].length; k++) {
                        //difference for each font line
                        total+=diff[j][k][i+k-(k==0?1:0)];
                    }
                    if (total<cost[i][21]) {
                        //set minimum j
                        cost[i][20]=total;
                        from[i][20]=j;
                    }
                    for (int k = 1; k < diff[j].length; k++) {
                        total+=diff[j][k][i+k];
                        total-=diff[j][k][i+k+1];
                        if (total<cost[i][20]) {
                            //set minimum j;
                            cost[i][20]=total;
                            from[i][20]=j;
                        }
                    }
                }
            }
        }
        out.println();
        out.close();
    }
    static boolean[][] read (BufferedReader in) throws Exception {
        N = Integer.parseInt(in.readLine());
        boolean[][] font = new boolean[N][20];
        for (int i = 0; i < N; i++) {
            char[] arr = in.readLine().toCharArray();
            for (int j = 0; j < 20; j++) {
                font[i][j] = arr[j]=='1';
            }
        }
        in.close();
        return font;
    }
}