/*
ID: 100021881
LANG: JAVA
PROG: contact
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class contact {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("contact.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(l.nextToken()); //minimum pattern length
        int B = Integer.parseInt(l.nextToken()); //maximum pattern length
        int N = Integer.parseInt(l.nextToken()); //number of patterns to find

        StringBuilder build = new StringBuilder();
        String line;

        while ((line = br.readLine())!=null) {
            build.append(line);
        }

        br.close();

        int len = build.length();
        int[] totals = new int[(int)Math.pow(2, B+1)];

        System.out.println("len: "+len);
        System.out.println("totals.length: "+totals.length);
        for (int i = A; i <= B; i++) { // length of i
            for (int j = 0; j < i; j++) { // offset of j
                for (int k = j; k <= len-i; k+=i) { // start at k
                    int range = Integer.parseInt("1"+build.substring(k, k+i), 2);
                    // DEBUG System.out.println(k+"\t"+i+"\t"+build.substring(k, k+i));
                    totals[range]++;
                }
            }
        }
        int max = 0;
        for (int i = 2; i < totals.length; i++) {
            if (totals[i]>max) {
                max=totals[i];
            }
        }

        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            results.add(new ArrayList<>());
        }
        for (int i = (int)Math.max(2, Math.pow(2, A)); i < totals.length; i++) {
            results.get(totals[i]).add(i);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        int totalPrinted = 0;
        for (int i = max; i > 0; i--) {
            if (totalPrinted>=N) {
                break;
            }
            if (results.get(i).size()==0) {
                continue;
            }

            totalPrinted++;
            out.println(i);
            for (int j = 0; j < results.get(i).size(); j++) {
                out.print(Integer.toString(results.get(i).get(j), 2).substring(1));
                if (j%6==5 && j+1 != results.get(i).size()) {
                    out.println();
                    continue;
                }
                if (j < results.get(i).size()-1) {
                    out.print(" ");
                }
            }
            out.println();
        }
        out.close();

        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}