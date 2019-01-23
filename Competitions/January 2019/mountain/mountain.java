/*
ID: 100021881
LANG: JAVA
PROG: mountains
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * mountains
 */
public class mountains {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();
        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        int N = Integer.parseInt(br.readLine());

        List<List<Long>> peaks = new ArrayList<List<Long>>();
        String line = "";
        while((line = br.readLine())!=null) {
            StringTokenizer l = new StringTokenizer(line);
            long x = Long.parseLong(l.nextToken());
            long y = Long.parseLong(l.nextToken());
            ArrayList<Long> p = new ArrayList<Long>();
            p.add(x+y);
            p.add(y-x);
            boolean change = x<0 || y<0;
            for(int k = 0; k < peaks.size(); k++) {
                if(peaks.get(k).get(0) >= p.get(0) && peaks.get(k).get(1) >= p.get(1)) {
                    change = true;
                    break;
                } else if(peaks.get(k).get(0) <= p.get(0) && peaks.get(k).get(1) <= p.get(1)) {
                    peaks.remove(k);
                    k=-1;
                }
            }
            if(!change) {
                peaks.add(p);
            }
            //https://math.stackexchange.com/questions/383321/rotating-x-y-points-45-degrees
        }
        br.close();


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        out.println(peaks.size());
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}
