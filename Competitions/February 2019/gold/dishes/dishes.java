/*
ID: 100021881
LANG: JAVA
PROG: dishes
*/

import java.io.*;
import java.util.*;

public class dishes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dishes.in"));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> dishes = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            dishes.add(Integer.parseInt(br.readLine()));
        }
        br.close();

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
        out.close();
        System.exit(0);
    }
    public static class Edge {
        int u, v, w;
    }
}
