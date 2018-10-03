
/*
ID: 100021881
LANG: JAVA
PROG: combo
*/

import java.io.*;
import java.util.*;

public class combo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer line1 = new StringTokenizer(br.readLine());
        StringTokenizer line2 = new StringTokenizer(br.readLine());
        List<Integer> farmer = new ArrayList<Integer>();
        List<Integer> master = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            farmer.add(Integer.parseInt(line1.nextToken()));
            master.add(Integer.parseInt(line2.nextToken()));
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if ( (dist(i, farmer.get(0), N) && dist(j, farmer.get(1), N) && dist(k, farmer.get(2), N)) || (dist(i, master.get(0), N) && dist(j, master.get(1), N) && dist(k, master.get(2), N)) ) {
                        //out.println(i+","+j+","+k);
                        count++;
                    }   
                }
            }
        }
        out.println(count);
        out.close();
        br.close();
    }
    public static boolean dist(int a, int b, int N) {
        return (Math.min(Math.min(Math.abs(a-b), Math.abs((a-b)+N)), Math.abs((a-b)-N))<=2);
    }
}
