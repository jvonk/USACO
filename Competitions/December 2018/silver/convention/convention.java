/*
ID: 100021881
LANG: JAVA
PROG: convention
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

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
        
        
        List<Integer> cuts = new ArrayList<Integer>();
        cuts.add(-1);
        while(cuts.size()<M) {
            int max = Integer.MAX_VALUE;
            int maxi = -1;
            for (int i = 1; i < N-1; i++) {
                
                if (!cuts.contains(i)) {
                    cuts.add(i);
                    if ((cuts.indexOf(i)>=cuts.size()-1?cows.length:cuts.get(cuts.indexOf(i)+1))-i>C) {
                        continue;
                    }
                    int sum=0;
                    for (int k = 0; k < cuts.size(); k++) {
                        for (int j = cuts.get(k)+1; j <= (k+1<cuts.size()?cuts.get(k+1):cows.length-1); j++) {
                            sum+=cows[j]-cows[cuts.get(k)+1];
                        }
                    }
                    if (sum < max) {
                        max=sum;
                        maxi=i;
                    }
                    cuts.remove(cuts.indexOf(i));
                }
            } //https://stackoverflow.com/questions/2895342/java-how-can-i-split-an-arraylist-in-multiple-small-arraylists
            cuts.add(maxi);
        }
        System.out.println(System.currentTimeMillis() - startTime);

        int sum=0;
        for (int k = 0; k < cuts.size(); k++) {
            System.out.println(cuts.get(k));
            for (int j = cuts.get(k)+1; j <= (k+1<cuts.size()?cuts.get(k+1):cows.length-1); j++) {
                sum+=cows[j]-cows[cuts.get(k)+1];
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        out.println(sum);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}