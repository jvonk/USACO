
/*
ID: 100021881
LANG: JAVA
PROG: sort3
*/

import java.io.*;
import java.util.*;

public class sort3 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        int N = Integer.parseInt(br.readLine());
        List<Integer> input = new ArrayList<Integer>();
        int[] counter = new int[3];
        for (int i = 0; i < N; i++) {
            input.add(Integer.parseInt(br.readLine()));
            counter[input.get(i) - 1]++;
        }
        int[] switches = new int[6];
        for (int i = 0; i < N; i++) {
            if (i < counter[0]) {
                if (input.get(i) == 2) {
                    switches[0]++;
                } else if (input.get(i) == 3) {
                    switches[1]++;
                }
            } else if (i < counter[0] + counter[1]) {
                if (input.get(i) == 1) {
                    switches[2]++;
                } else if (input.get(i) == 3) {
                    switches[3]++;
                }
            } else {
                if (input.get(i) == 1) {
                    switches[4]++;
                } else if (input.get(i) == 2) {
                    switches[5]++;
                }
            }
        }
        for (int i = 0; i < switches.length; i++) {
            System.out.print(switches[i]+" ");
        }
        System.out.println();
        System.out.println(input);
        input.sort(Comparator.naturalOrder());
        System.out.println(input);
        int swaps=0;
        if(switches[0]>0) {
            if (switches[0]<=switches[2]) {
                swaps+=switches[0];
                switches[2]-=switches[0];
                switches[0]=0;
            } else {
                swaps+=switches[2];
                switches[0]-=switches[2];
                switches[2]=0;
            }
            System.out.println(swaps);
            if (switches[0]>0) {
                swaps+=2*switches[0];
                switches[4]-=switches[0];
                switches[3]-=switches[0];
                switches[0]=0;
            }
        }
        for (int i = 0; i < switches.length; i++) {
            System.out.print(switches[i]+" ");
        }
        System.out.println();
        System.out.println(swaps);
        if(switches[1]>0) {
            if (switches[1]<=switches[4]) {
                swaps+=switches[1];
                switches[4]-=switches[1];
                switches[1]=0;
            } else {
                swaps+=switches[4];
                switches[1]-=switches[4];
                switches[4]=0;
            }
            System.out.println(swaps);
            if (switches[1]>0) {
                swaps+=2*switches[1];
                switches[2]-=switches[1];
                switches[1]=0;
            }
        }for (int i = 0; i < switches.length; i++) {
            System.out.print(switches[i]+" ");
        }
        System.out.println();
        System.out.println(swaps);
        if (switches[3]>0) {
            swaps+=switches[3];
        }for (int i = 0; i < switches.length; i++) {
            System.out.print(switches[i]+" ");
        }
        System.out.println();
        System.out.println(swaps);
        out.println(swaps);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
