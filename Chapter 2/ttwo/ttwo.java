
/*
ID: 100021881
LANG: JAVA
PROG: ttwo
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

public class ttwo {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
        char[][] grid = new char[10][10];
        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            grid[count] = line.toCharArray();
            count++;
        }
        br.close();
        int[] dirX = new int[] { 1, 0, -1, 0 };
        int[] dirY = new int[] { 0, 1, 0, -1 };

        int farmerX = -1;
        int farmerY = -1;
        int farmerDir = 3;

        List<Integer> cowXs = new ArrayList<>();
        List<Integer> cowYs = new ArrayList<>();
        List<Integer> cowDirs = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'F') {
                    farmerY = i;
                    farmerX = j;
                    grid[i][j] = '.';
                } else if (grid[i][j] == 'C') {
                    cowXs.add(j);
                    cowYs.add(i);
                    cowDirs.add(3);
                    grid[i][j] = '.';
                }// DEBUG  else if (grid[i][j] != '.' && grid[i][j] != '*') {
                // DEBUG     System.out.println(i + " " + j + " " + grid[i][j]);
                // DEBUG }
            }
            // DEBUG System.out.println(grid[i]);
        }
        boolean works = false;
        int minutes = 0;
        while (!works) {
            int x = farmerX + dirX[farmerDir];
            int y = farmerY + dirY[farmerDir];
            if (x >= 10 || x < 0 || y >= 10 || y < 0 || grid[y][x] == '*') {
                farmerDir++;
                farmerDir %= 4;
            } else {
                farmerX = x;
                farmerY = y;
            }
            for (int i = 0; i < cowXs.size(); i++) {
                x = cowXs.get(i) + dirX[cowDirs.get(i)];
                y = cowYs.get(i) + dirY[cowDirs.get(i)];
                if (x >= 10 || x < 0 || y >= 10 || y < 0 || grid[y][x] == '*' || grid[y][x] == 'C') {
                    cowDirs.set(i, (cowDirs.get(i) + 1) % 4);
                } else {
                    cowXs.set(i, x);
                    cowYs.set(i, y);
                }
                if (cowXs.get(i).equals(farmerX) && cowYs.get(i).equals(farmerY)) {
                    works = true;
                }
            }
            if (minutes > 100000) {
                break;
            }
            minutes++;
            // DEBUG System.out.println(minutes);
        }

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        if (works)
            out.println(minutes);
        else
            out.println(0);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}