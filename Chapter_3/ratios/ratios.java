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
        
        int[][] feeds = new int[4][3];

        for (int i = 0; i < 4; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            feeds[i]= new int[] {Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())};
        }

        br.close();


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
        boolean works = false;
        loop: for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    double first = 1.0*(i*feeds[1][0]+j*feeds[2][0]+k*feeds[3][0])/feeds[0][0];
                    double second = 1.0*(i*feeds[1][1]+j*feeds[2][1]+k*feeds[3][1])/feeds[0][1];
                    double third = 1.0*(i*feeds[1][2]+j*feeds[2][2]+k*feeds[3][2])/feeds[0][2];
                    if (i==0&&j==38&&k==7) {
                        System.out.println("first: "+first);
                        System.out.println("second: "+second);
                        System.out.println("third: "+third);
                    }
                    if (first!=0) {
                        if ((first==second || feeds[0][0] == 0 || feeds[0][1] == 0) && (second==third || feeds[0][1] == 0 || feeds[0][2] == 0)) {
                            if (first==(int)(first)) {
                                out.println(i+" "+j+" "+k+" "+((int)first));
                                works = true;
                                break loop;
                            }
                        }
                    }
                }
            }
        }

        if (!works) {
            out.println("NONE");
        }

        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}