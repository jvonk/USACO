/*
ID: 100021881
LANG: JAVA
PROG: cowland
*/

import java.io.*;
import java.util.*;

public class cowland {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int Q = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        int[] e = new int[N];
        boolean[][] connected = new boolean[N][N];
        int[][] all = new int[N][N];
        for (int i = 0; i < N; i++) {
            e[i] = Integer.parseInt(line[i]);
            all[i][0] = -1;
        }

        int[] values = new int[N];
        for (int i = 0; i < N - 1; i++) {
            line = br.readLine().split(" ");
            connected[Integer.parseInt(line[0]) - 1][Integer.parseInt(line[1]) - 1] = true;
        }
        int prevstart = -1;
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
        for (int i = 0; i < Q; i++) {
            line = br.readLine().split(" ");
            if (line[0].equals("1")) {
                e[Integer.parseInt(line[1]) - 1] = Integer.parseInt(line[2]);
                for (int j = 0; j < N; j++) {
                    all[j][0] = -1;
                }
            } else {
                int goal = Integer.parseInt(line[2]) - 1;
                int start = Integer.parseInt(line[1]) - 1;
                if (all[start][0] == -1) {
                    boolean[] visited = new boolean[N];
                    values = e.clone();
                    visited[start] = true;
                    Queue<Integer> queue = new LinkedList<Integer>();
                    queue.add(start);
                    loop:
                    while (!queue.isEmpty()) {
                        int k = queue.remove();
                        for (int j = 0; j < N; j++) {
                            if ((connected[k][j] || connected[j][k]) && !visited[j]) {
                                visited[j] = true;
                                values[j] = values[k] ^ e[j];
                                if (j == goal) break loop;
                                queue.add(j);
                            }
                        }
                    }
                    all[start] = values.clone();
                }
                //1^4^16
                out.println(all[start][goal]);
            }
        }
        br.close();
        out.close();
        System.exit(0);
    }
}
