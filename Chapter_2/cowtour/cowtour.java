/*
ID: 100021881
LANG: JAVA
PROG: cowtour
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class cowtour {
    private static int[][] adjacent;
    private static int[][] location;
    private static int[] visited;
    private static int N;

    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));

        N = Integer.parseInt(br.readLine());


        location = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            location[i][0] = Integer.parseInt(line.nextToken());
            location[i][1] = Integer.parseInt(line.nextToken());
        }
        
        adjacent = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] lineArray = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                adjacent[i][j] = lineArray[j]-'0';
            }
        }

        br.close();

        visited = new int[N];
        Arrays.fill(visited, -1);

        for (int i = 0; i < N; i++) {
            solve(i, i);
        }

        double[][] adjacencyMatrix = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j) {
                    adjacencyMatrix[i][j]=0;
                } else if (adjacent[i][j]==1) {
                    adjacencyMatrix[i][j]=Math.hypot(location[j][0]-location[i][0], location[j][1]-location[i][1]);
                } else {
                    adjacencyMatrix[i][j]=Double.MAX_VALUE;
                }
            }
        }

        adjacencyMatrix=floyd(adjacencyMatrix);

        double min = Double.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (visited[i]==visited[j]) {
                    continue;
                }
                double distance = Math.hypot(location[j][0]-location[i][0], location[j][1]-location[i][1]);
                double max = 0;
                inner: for (int k = 0; k < N; k++) {
                    if (visited[k]!=visited[i]&&visited[k]!=visited[j]) {
                        continue;
                    }
                    for (int m = k+1; m < N; m++) {
                        if (visited[m]!=visited[i]&&visited[m]!=visited[j] || adjacencyMatrix[k][m] < max) {
                            continue;
                        }
                        if (visited[i]==visited[k]) {
                            max = Math.max(max, Math.min(adjacencyMatrix[k][m], distance+adjacencyMatrix[k][i]+adjacencyMatrix[j][m]));
                        } else {
                            max = Math.max(max, Math.min(adjacencyMatrix[k][m], distance+adjacencyMatrix[k][j]+adjacencyMatrix[i][m]));
                        }
                        if (max > min) {
                            break inner;
                        }
                    }
                }
                min = Math.min(min, max);
            }
        }

        String result = String.format("%.6f", min);
        while (result.length()-result.indexOf('.')<6) {
            result+="0";
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        out.println(result);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    private static void solve (int current, int number) {
        if (visited[current]!=-1) {
            return; //already visited
        }
        visited[current]=number;
        for (int i = 0; i < N; i++) {
            if (adjacent[current][i]==1) {
                solve(i, number); // try every connected one
            }
        }
    }
    public static double[][] floyd (double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    matrix[j][k]=Math.min(matrix[j][k], matrix[j][i]+matrix[i][k]);
                }
            }
        }
        return matrix;
    }
}