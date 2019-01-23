/*
ID: 100021881
LANG: JAVA
PROG: perimeter
*/

import java.io.*;
import java.util.*;

public class perimeter {
    static boolean[][] graph;
    static int[][] component;
    static int numComponents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
        int N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                graph[i][j] = arr[j]=='#';
            }
        }
        br.close();

        component = new int[N][N];
        findComponents();
        int[] num = new int[numComponents];
        for(int i = 0; i < component.length; i++) {
            for(int j = 0; j < component.length; j++) {
                if(graph[i][j]) {
                    num[component[i][j]]++;
                }
            }
        }
        int max = 0;
        for(int i = 0; i < num.length; i++) {
            if(num[i]>max) {
                max = num[i];
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        int t = Integer.MAX_VALUE;
        for(int c = 0; c < num.length; c++) {
            if(num[c]!=max) {
                continue;
            }
            int p = 0;
        loop:
            for(int i = 0; i < component.length; i++) {
                for(int j = 0; j < component.length; j++) {
                    if(p >= t) {
                        break loop;
                    }
                    if(component[i][j]!=c) {
                        continue;
                    }
                    if(i+1 < component.length) {
                        if(!graph[i+1][j]) {
                            p++;
                        }
                    } else {
                        p++;
                    }
                    if(i - 1 >=0) {
                        if(!graph[i-1][j]) {
                            p++;
                        }
                    } else {
                        p++;
                    }
                    if(j + 1 < component.length) {
                        if(!graph[i][j+1]) {
                            p++;
                        }
                    } else {
                        p++;
                    }
                    if(j - 1 >=0) {
                        if(!graph[i][j-1]) {
                            p++;
                        }
                    } else {
                        p++;
                    }
                }
            }
            t = Math.min(t, p);
        }
        out.println(max+" "+t);
        out.close();
        System.exit(0);
    }
    public static void floodFill(int newComponent) {
        int num_visited = 0;
        do {
            num_visited = 0;
            for(int i = 0; i < component.length; i++) {
                for(int j = 0; j < component.length; j++) {
                    if(component[i][j] == -2) {
                        num_visited++;
                        component[i][j] = newComponent;
                        if(i + 1 < component.length && graph[i+1][j] && component[i+1][j]==-1) {
                            component[i+1][j]=-2;
                        }
                        if(i - 1 >= 0 && graph[i-1][j] && component[i-1][j]==-1) {
                            component[i-1][j]=-2;
                        }
                        if(j + 1 < component.length && graph[i][j+1] && component[i][j+1]==-1) {
                            component[i][j+1]=-2;
                        }
                        if(j - 1 >= 0 && graph[i][j-1] && component[i][j-1]==-1) {
                            component[i][j-1]=-2;
                        }
                    }
                }
            }
        } while(num_visited > 0);
    }

    public static void findComponents() {
        numComponents = 0;
        for(int i = 0; i < component.length; i++) {
            Arrays.fill(component[i], -1);
        }
        for(int i = 0; i < component.length; i++) {
            for(int j = 0; j < component.length; j++) {
                if(component[i][j] == -1 && graph[i][j]) {
                    numComponents++;
                    component[i][j] = -2;
                    floodFill(numComponents - 1);
                }
            }
        }
    }
}
