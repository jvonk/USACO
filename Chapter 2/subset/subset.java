
/*
ID: 100021881
LANG: JAVA
PROG: subset
*/

import java.io.*;
import java.util.*;

public class subset {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        N = Integer.parseInt(br.readLine());
        max =  N*(N+1)/4;
        int total = 0;
        boolean[] used = new boolean[N];
        for (int i = 0; i < N; i++) {
            used[i]=false;
        }
        total = solve(total, new boolean[N], 0, new int[N]);
        System.out.println(total);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
    static int N;
    static int max;
    public static int solve(int total, boolean[] used, int count, int[] visited) {
        int i = 1;
        for (; i <= N; i++) {
            if(!used[i-1]) {
                System.out.println(i);
                if (count<N-1) {
                    visited[count]=i;
                    int t=total+i;
                    used[i-1]=true;
                    t=solve(t, used, count+1, visited);
                    if (t<total) {
                        total=t;
                    }
                } else {
                    return total+i;                    
                }
            }
        }
        return total;
    }
}
