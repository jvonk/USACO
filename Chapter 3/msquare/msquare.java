/*
ID: 100021881
LANG: JAVA
PROG: msquare
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
 * msquare
 */
public class msquare {
    static int[] target;
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("msquare.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        
        target = new int [] {Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())};

        br.close();


        
        

        

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        out.println(digit);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    //0  1  2  3
    //7  6  5  4
    public static void a() {
        for (int i = 0; i < 4; i++) {
            int temp = target[i];
            target[i]=target[7-i];
            target[7-i]=temp;
        }
    }
    public static void b() {
        int temp = target[4];
        target[4]=target[5];
        target[5]=target[6];
        target[6]=target[7];
        target[7]=temp;
        temp = target[3];
        target[3]=target[2];
        target[2]=target[1];
        target[1]=target[0];
        target[0]=temp;
    }
    public static void c() {
        int temp = target[1];
        target[1]=target[6];
        target[6]=target[5];
        target[5]=target[2];
        target[2]=temp;
    }
}