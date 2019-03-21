/*
ID: 100021881
LANG: JAVA
PROG: charrec
*/

import java.util.*;
import java.io.*;

public class charrec {
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("charrec.out")));
        boolean[][] font = read(new BufferedReader(new FileReader("font.in")));
        boolean[][] input = read(new BufferedReader(new FileReader("charrec.in")));
        boolean[][][] characters = new boolean[font.length/20][20][20];
        char[] back = "_abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < font.length; i++) {
            characters[i/20][i%20]=font[i];
        }
        int count = 0;
        int[][] diff = new int[3][characters.length];
        boolean[] flag = new boolean[3];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < characters.length; j++) {
                for (int k = 0; k < 3 && count+k < 20; k++) {
                    diff[k][j] += diff(characters[j][count+k], input[i]);
                }
            }
            int[] minVal = new int[] {-1, -1};
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < 2; j++) {
                for (int k = 0; k < characters.length; k++) {
                    if (diff[j][k] < min){
                        min = diff[j][k];
                        minVal = new int[] {j, k};
                    }
                }
            }
            count+=minVal[0];
            if (minVal[0]==0) flag[0] = true;
            else if (minVal[0]==2) flag[2] = true;
            if (count>=20 || i+1==input.length) {
                flag = new boolean[3];
                out.print(back[minVal[1]]);
                count=0;
            }
        }
        out.println();
        out.close();
    }
    static boolean[][] read (BufferedReader in) throws Exception {
        int N = Integer.parseInt(in.readLine());
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
    static int diff(boolean[] a, boolean[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] ^ b[i]) sum++;
        }
        return sum;
    }
}