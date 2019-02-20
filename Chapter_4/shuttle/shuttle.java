/*
ID: 100021881
LANG: JAVA
PROG: shuttle
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/**
 * shuttle
 */
public class shuttle {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("shuttle.in"));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int maxMoves = N * (N + 2);
        int free = N + 1;
        int halfDone = maxMoves / 2;
        int direction = -1;
        int size = 2 * N + 1;
        char[] current = new char[size + 1];

        for(int i = 1; i < size + 1; i++) {
            if(i < free) {
                current[i] = 'W';
            } else if(i > free) {
                current[i] = 'B';
            } else {
                current[i] = ' ';
            }
        }
        String reverse = new StringBuilder(new String(current)).reverse().toString();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
        boolean slide = true;
        for(int i = 1; i <= maxMoves; i++) {
            if(slide) {
                if(i <= halfDone) {
                    //swap free and free+direction;
                    current=swap(current, free, free+direction);
                    free += direction;
                    direction *= -1;
                } else {
                    direction *= -1;
                    //swap free and free+direction;
                    current=swap(current, free, free+direction);
                    free += direction;
                }
                slide = false;
            } else {
                //swap free and free+2*direction;
                current=swap(current, free, free+2*direction);
                free += 2*direction;
                slide = free+2*direction<1||free+2*direction>size||current[free+2*direction]==current[free+direction];
            }
            out.print(free);
            if (i<maxMoves&&i%20!=0) {
                out.print(" ");
            } else {
                out.println();
            }
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static char[] swap (char[] in, int i, int j) {
        char temp = in[i];
        in[i]=in[j];
        in[j]=temp;
        return in;
    }
}
