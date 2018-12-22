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

/*
 * camelot
 */
public class camelot {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("camelot.in"));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = line[0];
        int C = line[1];
        String[] str = br.readLine().split(" ");
        int column = str[0].charAt(0)-'A';
        int row = str[1].charAt(0)-'1';
        while ((str=br.readLine().split(" ")).length>0) {
            for (int i = 0; i < str.length; i+=2) {
                column = str[i].charAt(0)-'A';
                row = str[i+1].charAt(0)-'1';
            }
        }
        br.close();



        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}