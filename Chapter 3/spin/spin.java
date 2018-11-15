/*
ID: 100021881
LANG: JAVA
PROG: spin
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
 * spin
 */
public class spin {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("spin.in"));

        Wheel[] wheels = new Wheel[5];
        for (int i = 0; i < 5; i++) {    
            StringTokenizer l = new StringTokenizer(br.readLine());
            int speed = Integer.parseInt(l.nextToken());
            int num = Integer.parseInt(l.nextToken());
            Wedge[] wedges = new Wedge[num];
            for (int j = 0; j < num; j++) {
                wedges[num]=new Wedge(Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()));
            }
            wheels[i] = new Wheel(speed, wedges);
        }

        br.close();






        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }


    static class Wedge {
        int angle;
        int extent;
        public Wedge(int ang, int ext) {
            angle = ang;
            extent = ext;
        }
    }
    static class Wheel {
        int speed;
        Wedge[] wedges;
        public Wheel(int spd, Wedge[] ws) {
            speed = spd;
            wedges = ws;
        }
    }
}