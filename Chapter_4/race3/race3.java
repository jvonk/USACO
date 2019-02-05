/*
ID: 100021881
LANG: JAVA
PROG: race3
*/

import java.io.*;
import java.util.*;
import java.math.*;

/**
* race3
*/
public class race3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("race3.in"));
        int N = 0;
        boolean[][] connected = new boolean[51][51];
        int[] ans = new int[] {0, 0};
        int[] position = new int[100];
        boolean[] visited = new boolean[100];
        int[] results = new int[100];
        String str;
        while((str = br.readLine()) != null) {
            if(str.equals("-1")) {
                break;
            }
            for(String l : str.split(" ")) {
                int temp = Integer.parseInt(l);
                if(temp == -2) {
                    N++;
                } else {
                    connected[N][temp] = true;
                }
            }
        }
        br.close();
        N--;
        visited[0] = true;
        position[1] = 0;
        int current = 1;
        int next = 1;
        while(current <= next) {
            if(current == next) {
                results[position[current]] = 1;
                ans[0]++;
                if(check(N, visited, connected, position[current])) {
                    results[position[current]] = 2;
                    ans[1]++;
                }
            }
            int location = position[current++];
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && connected[location][i]) {
                    position[++next] = i;
                    visited[i] = true;
                }
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
        out.print(ans[0] - 2);
        for(int i = 1; i < N; i++) {
            if(results[i] >= 1) {
                out.print(" " + i);
            }
        }
        out.println();
        out.print(ans[1] - 2);
        for(int i = 1; i < N; i++) {
            if(results[i] == 2) {
                out.print(" " + i);
            }
        }
        out.println();
        out.close();
        System.exit(0);
    }
    public static boolean check(int N, boolean[] visited, boolean[][] connected, int location) {
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(visited[i] != visited[j] && connected[i][j] && i != location && j != location) {
                    return false;
                }
            }
        }
        for(int i = 0; i <= N; i++) {
            if(visited[i] && connected[location][i] && i != location) {
                return false;
            }
        }
        return true;
    }
}
