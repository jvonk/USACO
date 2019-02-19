/*
ID: 100021881
LANG: JAVA
PROG: milk6
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/**
 * milk6
 */
public class milk6 {
    public static int N, M;
    public static int[][] connections;
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        connections = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            connections[Integer.parseInt(line[0])][Integer.parseInt(line[1])] +=  Integer.parseInt(line[2]);
        }
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(connections[i]));
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        List<Integer> visited = new ArrayList<Integer>();
        visited.add(N);
        out.println(sum(visited));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static int sum(List<Integer> visited) {
        int i = visited.get(visited.size()-1);
        if (i==1) return 0;
        int sum = 0;
        for (int j = 1; j <= N; j++) {
            if (visited.contains(j) || connections[j][i]==0) continue;
            visited.add(j);
            sum+=Math.min(connections[j][i], sum(visited));
            visited.remove(visited.size()-1);
        }
        return sum;
    }
}
