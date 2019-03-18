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
        long[] e = new long[N];
        List<Set<Integer>> connected = new ArrayList<Set<Integer>>();
        for (int i = 0; i < N; i++) {
            e[i]=Integer.parseInt(line[i]);
            connected.add(new HashSet<Integer>());
        }

        long[] values = new long[N];
        Map<Integer, long[]> old = new HashMap<Integer, long[]>();
        for (int i = 0; i < N-1; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0])-1;
            int b = Integer.parseInt(line[1])-1;
            connected.get(a).add(b);
            connected.get(b).add(a);
        }
        int prevstart = -1;
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
        for (int i = 0; i < Q; i++) {
            line = br.readLine().split(" ");
            if (line[0].equals("1")) {
                e[Integer.parseInt(line[1])-1]=Integer.parseInt(line[2]);
                old.clear();
            } else {
                boolean[] visited = new boolean[N];
                int goal = Integer.parseInt(line[2])-1;
                int start = Integer.parseInt(line[1])-1;
                if (!old.containsKey(start)) {
                    values = e.clone();
                    visited[start] = true;
                    Queue<Integer> queue = new LinkedList<Integer>();
                    queue.add(start);
                    loop:
                    while (!queue.isEmpty()) {
                        int k = queue.remove();
                        for (int j : connected.get(k)) {
                            if (!visited[j]) {
                                visited[j] = true;
                                values[j] = values[k] ^ e[j];
                                if (j == goal) break loop;
                                queue.add(j);
                            }
                        }
                    }
                    old.put(start, values);
                }
                prevstart=start;
                out.println(old.get(start)[goal]);
            }
        }
        br.close();
        out.close();
        System.exit(0);
    }
}
