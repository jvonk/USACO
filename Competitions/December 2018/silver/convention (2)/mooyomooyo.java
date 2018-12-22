/*
ID: 100021881
LANG: JAVA
PROG: mooyomooyo
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * mooyomooyo
 */
public class mooyomooyo {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));

        int[] line1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = line1[0];
        int K = line1[1];
        int[][] board = new int[10][N];
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < 10; j++) {
                board[j][i]=Integer.parseInt(String.valueOf(arr[j]));
            }
        }
        br.close();
        boolean change = true;
        while(change) {
            change=false;
            boolean[][] connections = new boolean[N*10][N*10];
            for (int h = 0; h < N; h++) {
                for (int w = 0; w < 10; w++) {
                    int pos = h * 10 + w;
                    if (h+1<N && board[w][h+1]==board[w][h]) {
                        int south = pos + 10;
                        connections[pos][south] = true;
                    }
                    if (w+1 < 10 && board[w+1][h]==board[w][h]) {
                        int east = pos + 1;
                        connections[pos][east] = true;
                    }
                    if (h-1>=0 && board[w][h-1]==board[w][h]) {
                        int north = pos - 10;
                        connections[pos][north] = true;
                    }
                    if (w-1>=0 && board[w-1][h]==board[w][h]) {
                        int west = pos - 1;
                        connections[pos][west] = true;
                    }
                }
            }
            List<Integer> component = new ArrayList<Integer>();
            component = findComponents(connections, component);
            
            int[] components = new int[component.remove(component.size() - 1)];
            System.out.println();
            System.out.println();
            //System.out.println(component);
            for (int i = 0; i < component.size(); i++) {
                components[component.get(i)]++;
            }
            for (int i = 0; i < component.size(); i++) {
                if (components[component.get(i)]>=K) {
                    int x = i % 10;
                    int y = i / 10;
                    if (board[x][y]!=0) {
                        board[x][y]=0;
                        change=true;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[j][i]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j]==0) {
                        for (int k = j-1; k >=0; k--) {
                            if (board[i][k+1]!=board[i][k]) {
                                board[i][k+1]=board[i][k];
                                change=true;
                            }
                        }
                        board[i][0]=0;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[j][i]);
                }
                System.out.println();
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                out.print(board[j][i]);
            }
            out.println();
        }
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static List<Integer> floodFill(boolean[][] connections, List<Integer> component, int newComponent) {
        int num_visited = 0;
        do {
            num_visited = 0;
            for (int i = 0; i < connections.length; i++) {
                if (component.get(i) == -2) {
                    num_visited++;
                    component.set(i, newComponent);
                    for (int j = 0; j < connections.length; j++) {
                        if (component.get(j) == -1 && connections[i][j]) {
                            component.set(j, -2);
                        }
                    }
                }
            }
        } while (num_visited > 0);
        return component;
    }

    public static List<Integer> findComponents(boolean[][] connections, List<Integer> component) {
        int numComponents = 0;
        for (int i = 0; i < connections.length; i++) {
            component.add(-1);
        }
        for (int i = 0; i < connections.length; i++) {
            if (component.get(i) == -1) {
                numComponents++;
                component.set(i, -2);
                component = floodFill(connections, component, numComponents - 1);
            }
        }
        component.add(numComponents);
        return component;
    }

}