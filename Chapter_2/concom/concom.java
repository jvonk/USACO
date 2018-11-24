
/*
ID: 100021881
LANG: JAVA
PROG: concom
*/

// if you want to turn all the debug on, just find and replace "// @DEBUG " with nothing

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

public class concom {
    public static int max = 100;
    public static int[][] owns = new int[max + 1][max + 1];
    public static boolean[][] controls = new boolean[max + 1][max + 1];

    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("concom.in"));

        // get N, the number of input triples to follow
        int N = Integer.parseInt(br.readLine());

        // DEBUG System.out.println(N);


        // i always controls itself
        for (int i = 0; i <= max; i++) {
            controls[i][i] = true;
        }
        
        
        for (int i = 0; i < N; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            addOwner(Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()));
        }
        // DEBUG for (int i = 0; i <= max; i++) {
        // DEBUG System.out.println(Arrays.toString(Arrays.copyOfRange(stakes[i], 0, max
        // + 1)));
        // DEBUG }
        br.close();

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

        for (int i = 0; i <= max; i++) {
            for (int j = 0; j <= max; j++) {
                if (i!=j && controls[i][j]) {
                    out.println(i + " " + j);
                }
            }
        }
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void addController(int i, int j) {
        // DEBUG System.out.println("addController: "+i+" "+j);
        // ignore if already controlling
        if (controls[i][j]) {
            return;
        }
        // set control
        controls[i][j] = true;
        // if i controls j, add all of i's stuff to j
        for (int k = 0; k <= max; k++) {
            owns[i][k] += owns[j][k];
        }
        // everything that controls i controls j
        for (int k = 0; k <= max; k++) {
            if (controls[k][i]) {
                addController(k, j);
            }
        }
        // call addController on everything i not holds
        for (int k = 0; k <= max; k++) {
            if (owns[i][k] > 50) {
                addController(i, k);
            }
        }
    }

    public static void addOwner(int i, int j, int percent) {
        // DEBUG System.out.println("addOwner: "+i+" "+j+" "+percent);
        // add percent amount of j to everything that controls i
        for (int k = 0; k <= max; k++) {
            if (controls[k][i]) {
                owns[k][j] += percent;
            }
        }
        // does anything now control j?
        for (int k = 0; k <= max; k++) {
            if (owns[k][j] > 50) {
                addController(k, j);
            }
        }
    }
}
