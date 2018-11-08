/*
ID: 100021881
LANG: JAVA
PROG: ratios
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Deque;
import java.util.LinkedList;

/*
 * ratios
 */
public class ratios {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("ratios.in"));
        
        int[] feeds = new int[4][3];

        for (int i = 0; i < 4; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            feeds[i]= new int[] {Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())};
        }

        br.close();


        


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
        out.println(digit);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}