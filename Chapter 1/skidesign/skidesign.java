
/*
ID: 100021881
LANG: JAVA
PROG: skidesign
*/

import java.io.*;
import java.util.*;

public class skidesign {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> hills = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            hills.add(Integer.parseInt(br.readLine()));
        }
        //out.println(hills);
        int total = 2147483647;
		for(int i = 0; i <= 83; i++) {
            int cost = 0;
            int diff = 0;
            for (int j = 0; j < N; j++) {
                if (i>hills.get(j)) {
                    diff=i-hills.get(j);
                } else if (i<hills.get(j)-17) {
                    diff=hills.get(j)-i-17;
                } else {
                    diff = 0;
                }
                cost+=diff*diff;
            }
            total=Math.min(cost, total);
        }
        out.println(total);
        out.close();
        br.close();
    }    
}
