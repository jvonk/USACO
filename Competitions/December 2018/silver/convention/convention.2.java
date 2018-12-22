/*
ID: 100021881
LANG: JAVA
PROG: convention
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
 * convention
 */
public class convention {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));

        int[] line1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cows = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(cows);
        int N = line1[0];
        int M = line1[1];
        int C = line1[1];

        br.close();

        //split cows into groups with minimum distance total
        //longest distances between cows
        
        int[] distances = new int[cows.length];
        for (int i = 0; i < cows.length-1; i++) {
            distances[i]=cows[i+1]-cows[i];
        }
        distances[cows.length-1]=0;
        ArrayList<Integer> a = IntStream.range(0, cows.length).boxed().collect(Collectors.toList());
        ArrayList<Integer> b = Arrays.stream(distances).boxed().collect(Collectors.toList());
        ArrayList<ArrayList<Integer>> res = sortArray(new ArrayList<ArrayList<Integer>>(a, b));
        int[] order = res.get(0).toArray();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static ArrayList<ArrayList<Integer>> sortArray(ArrayList<ArrayList<Integer>> results) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            if (results.size() > 1) {
                for (int i = 1; i < results.size(); i++) {
                    if (results.get(i - 1).get(1) > results.get(i).get(1)) {
                        Collections.swap(results, i - 1, i);
                        swapped = true;
                    }
                }
            }
        }
        return results;
    }
}