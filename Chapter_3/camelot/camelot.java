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
        int[] changeRow = new int[] { -2, -2, -1, 1, 2, 2, 1, -1};
        int[] changeCol = new int[] { -1, 1, 2, 2, 1, -1, -2, -2};
        int[][][][] mem = new int[32][32][32][32];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                for (int k = 0; k < mem[i][j].length; k++) {
                    Arrays.fill(mem[i][j][k], 0x01010101);
                }
            }
        }
        String[] str = br.readLine().split(" ");
        Piece king = new Piece(str[0].charAt(0) - 'A' + 1, str[1].charAt(0) - '1' + 1);
        Piece[] knights = new Piece[1001];
        for (int i = 0; i < knights.length; i++) {
            knights[i] = new Piece();
        }
        String line;
        while ((line = br.readLine()) != null) {
            str = line.split(" ");
            for (int i = 0; i < str.length; i += 2) {
                knights[++numKnights] = new Piece(str[i].charAt(0) - 'A' + 1, str[i+1].charAt(0) - '1' + 1);
            }
        }
        br.close();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                Queue<Piece> queue = new LinkedList<Piece>();
                queue.add(new Piece(i, j));
                mem[i][j][i][j] = 0;
                while (!queue.isEmpty()) {
                    Piece piece = queue.poll();
                    for (int stepType = 0; stepType < changeRow.length; stepType++) {
                        int newRow = piece.row + changeRow[stepType];
                        int newCol = piece.col + changeCol[stepType];
                        if (newRow >= 1 && newCol >= 1 && newRow <= R && newCol <= C && mem[i][j][newRow][newCol] == 0x01010101) {
                            mem[i][j][newRow][newCol] = mem[i][j][piece.row][piece.col] + 1;
                            queue.add(new Piece(newRow, newCol));
                        }
                    }
                }
            }
        }
        int l = 0;
        int kingStartRow = Math.max(1, king.row - l);
        int kingStartCol = Math.max(1, king.col - l);
        int kingEndRow = Math.min(R, king.row + l);
        int kingEndCol = Math.min(C, king.col + l);
        int minstep = 1 << 25;
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                int step = 0;
                for (int i = 1; i <= numKnights; i++) {
                    step += mem[knights[i].row][knights[i].col][row][col];
                }
                int min = Math.max(Math.abs(king.row - row), Math.abs(king.col - col));
                for (int kingRow = kingStartRow; kingRow <= kingEndRow; kingRow++) {
                    for (int kingCol = kingStartCol; kingCol <= kingEndCol; kingCol++) {
                        for (int i = 1; i <= numKnights; i++) {
                            int temp = Math.max(Math.abs(king.row - kingRow), Math.abs(king.col - kingCol))
                                    - mem[knights[i].row][knights[i].col][row][col] + mem[kingRow][kingCol][row][col]
                                    + mem[knights[i].row][knights[i].col][kingRow][kingCol];
                            min = Math.min(temp, min);
                        }
                    }
                }
                if (min+step<=minstep)System.out.println(row+","+col+"\t"+min+"+"+step+" "+minstep);
                minstep = Math.min(min + step, minstep);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        out.println(minstep);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }

    static class Piece {
        public int row, col;

        public Piece(int a, int b) {
            row = a;
            col = b;
        }

        public Piece() {
            row = 0;
            col = 0;
        }
    }
}