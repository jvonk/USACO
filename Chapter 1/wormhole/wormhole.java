
/*
ID: 100021881
LANG: JAVA
PROG: wormhole
*/

import java.io.*;
import java.util.*;

public class wormhole {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        int N = Integer.parseInt(br.readLine());
        long[] X = new long[N+1];
        long[] Y = new long[N+1];
        int[] partner = new int[N+1];
        int[] nextOnRight = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            X[i]=Long.parseLong(line.nextToken());
            Y[i]=Long.parseLong(line.nextToken());
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(X[i]<X[j] && Y[i]==Y[j]) {
                    if(nextOnRight[i]==0 || X[j]-X[i]<X[nextOnRight[i]]-X[i]) {
                        nextOnRight[i]=j;
                    }
                }
            }
        }
        out.println(solve(nextOnRight, partner, N));
        out.close();
        br.close();
    }
    public static boolean cycleExists(int[] nextOnRight, int[] partner, int N) {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int count = 0; count < N; count++) {
                pos=nextOnRight[partner[pos]];
            }
            if(pos!=0) {
                return true;
            }
        }
        return false;
    }
    public static int solve(int[] nextOnRight, int[] partner, int N) {
        int total = 0;
        int i;
        for (i = 1; i <= N; i++) {
            if(partner[i]==0) {
                break;
            }
        }
        if (i > N) {
            if(cycleExists(nextOnRight, partner, N)) {
                return 1;
            } else {
                return 0;
            }
        }
        for (int j=i+1; j<=N; j++) {
            if (partner[j] == 0) {
                partner[i]=j;
                partner[j]=i;
                total+=solve(nextOnRight, partner, N);
                partner[i]=0;
                partner[j]=0;
            }
        }
        return total;
    }
}
