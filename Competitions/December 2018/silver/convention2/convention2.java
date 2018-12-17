/*
ID: 100021881
LANG: JAVA
PROG: convention2
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing


import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.util.Arrays;
/*
 * convention2
 */
public class convention2 {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("convention2.in"));

        int N = Integer.parseInt(br.readLine());
        List<int[]> cows = new ArrayList<int[]>();
        for (int i = 0; i < N; i++) {
            cows.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        br.close();
        int t = 0;
        int min = Integer.MAX_VALUE;
        int mini = 0;
        System.out.println(System.currentTimeMillis() - startTime);

        int[] waits = new int[cows.size()];
        for (int i = 0; i < N; i++) {
            if (cows.get(i)[0]<min) {
                min = cows.get(i)[0];
                mini=i;
            }
        }
        t=cows.get(mini)[0];
        int max = 0;

        while (true) {
            System.out.println(t+"\t"+(cows.get(mini)[1]+t)+"\t"+cows.get(mini)[0]);
            t+=cows.get(mini)[1];
            cows.remove(mini);
            int next = -1;
            int mi = Integer.MAX_VALUE;
            int mii = -1;
            for (int i = 0; i < cows.size(); i++) {
                if (cows.get(i)[0]<=t && next==-1) {
                    next=i;
                }
                if (cows.get(i)[0]<mi) {
                    mi=cows.get(i)[0];
                    mii=i;
                }
                max=Math.max(max, t-cows.get(i)[0]);
            }
            if (next==-1) {
                if (mii!=-1) {
                    t=mi;
                    mini=mii;
                } else break;
            } else {
                mini=next;
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
        out.println(max);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }

}