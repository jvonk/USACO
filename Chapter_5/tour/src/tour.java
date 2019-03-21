/*
ID: 100021881
LANG: JAVA
PROG: tour
*/

import java.util.*;
import java.io.*;

public class tour {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("tour.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int V = Integer.parseInt(line[1]);
        List<String> cities = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            cities.add(br.readLine());
        }
        boolean[][] connections = new boolean[N][N];
        for (int i = 0; i < V; i++) {
            line = br.readLine().split(" ");
            connections[cities.indexOf(line[0])][cities.indexOf(line[1])] = true;
        }
        br.close();
        out.close();
    }
}