/*
ID: 100021881
LANG: JAVA
PROG: snail
*/

import java.io.*;

public class snail {
    private static int N, max;
    private static boolean[][] visited, blocked;
    private static int[][] directions =  new int[][] {new int[] {0, 1}, new int[] {1, 0}, new int[] {0, -1}, new int[] {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("snail.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        visited=new boolean[N][N];
        blocked=new boolean[N][N];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
            String line = br.readLine();
            blocked[line.charAt(0)-'A'][Integer.parseInt(line.substring(1))-1] = true;
        }
        visited[0][0]=true;
        dfs(0,0,1,0);
        dfs(0,0,1,1);
        out.println(max);
        br.close();
        out.close();
    }
    private static void dfs(int x, int y, int count, int direction) {
        max = Math.max(max, count);
        int newX = x+directions[direction][0];
        int newY = y+directions[direction][1];
        if (newX>=0&&newY>=0&&newX<N&&newY<N&&!blocked[newX][newY]) {
            if (!visited[newX][newY]) {
                visited[newX][newY]=true;
                dfs(newX,newY,count+1,direction);
                visited[newX][newY]=false;
            }
        } else {
            for (int i : direction%2==0?new int[] {3,1}:new int[] {0,2}) {
                newX=x+directions[i][0];
                newY=y+directions[i][1];
                if (newX>=0&&newY>=0&&newX<N&&newY<N&&!blocked[newX][newY]&&!visited[newX][newY]) {
                    visited[newX][newY]=true;
                    dfs(newX,newY,count+1,i);
                    visited[newX][newY]=false;
                }
            }
        }
    }
}