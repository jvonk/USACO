/*
ID: 100021881
LANG: JAVA
PROG: camelot
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class camelot {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("camelot.in"));
        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = firstLine[0];
        int C = firstLine[1];
        int numKnights = 0;
        int[] changeRow = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] changeCol = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
        int[][][][] mem = new int[32][32][32][32];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                for (int k = 0; k < mem[i][j].length; k++) {
                    Arrays.fill(mem[i][j][k], 0x01010101);
                }
            }
        }
        String[] str = br.readLine().split(" ");
        Player king = new Player(str[0].charAt(0)-'A'+1, str[1].charAt(0)-'1'+1);
        Player[] knights = new Player[1001];
        for (int i = 0; i < knights.length; i++) {
            knights[i] = new Player();
        }
        String line;
        while ((line=br.readLine())!= null) {
            str = line.split(" ");
            for (int i = 0; i < str.length; i+=2) {
                knights[++numKnights] = new Player(str[0].charAt(0)-'A'+1, str[1].charAt(0)-'1'+1);
            }
        }
        br.close();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                Queue<Player> queue = new LinkedList<Player>();
                queue.add(new Player(i, j));
                mem[i][j][i][j]=0;
                while(!queue.isEmpty()) {
                    Player player = queue.poll();
                    for (int stepType = 0; stepType < 8; stepType++) {
                        int newRow = player.row + changeRow[stepType];
                        int newCol = player.col + changeCol[stepType];
                        if (newRow >= 0 && newCol >=0 && newRow <= R && newCol <=C && mem[i][j][newRow][newCol]==0x01010101) {
                            mem[i][j][newRow][newCol] = mem[i][j][player.row][player.col]+1;
                            queue.add(new Player(newRow, newCol));
                        }
                    }
                }
            }
        }
        int l = 2;
        int kingStartRow = Math.max(1, king.row-l);
        int kingStartCol = Math.max(1, king.col-l);
        int kingEndRow = Math.min(R, king.row+l);
        int kingEndCol = Math.min(C, king.col+l);
        int minstep = 1<<25;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                int step = 0;
                for(int k = 1; k <= numKnights; k++) {
                    step+=mem[knights[k].row][knights[k].col][i][j];
                }
                int min = Math.max(Math.abs(king.row-i), Math.abs(king.col-j));
                for (int pi = kingStartRow; pi <= kingEndRow; pi++)
                for (int pj = kingStartCol; pj <= kingEndCol; pj++)
                for(int k = 1; k <= numKnights; k++) {
                    int temp = Math.max(Math.abs(king.row-pi), Math.abs(king.col-pj))-mem[knights[k].row][knights[k].col][i][j]+mem[pi][pj][i][j]+mem[knights[k].row][knights[k].col][pi][pj];
                    min=Math.min(temp, min);
                }
                minstep = Math.min(min+step, minstep);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        out.println(minstep);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    static class Player {
        public int row, col;
        public Player (int a, int b) {
            row = a;
            col = b;
        }
        public Player() {
            row=0;
            col=0;
        }
    }
}