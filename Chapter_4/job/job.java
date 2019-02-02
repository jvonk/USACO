/*
ID: 100021881
LANG: JAVA
PROG: job
*/

import java.io.*;
import java.util.*;

/**
 * job
 */
public class job {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("job.in"));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M1 = Integer.parseInt(line[1]);
        int M2 = Integer.parseInt(line[2]);
        int[] M1Jobs = new int[M1];
        int[] M2Jobs = new int[M2];
        int count = 0;
        String str;

        while((str = br.readLine()) != null) {
            line = str.split(" ");
            for(int i = 0; i < line.length; i++) {
                if(count < M1) {
                    M1Jobs[count] = Integer.parseInt(line[i]);
                } else {
                    M2Jobs[count - M1] = Integer.parseInt(line[i]);
                }
                count++;
            }
        }
        br.close();
        Arrays.sort(M1Jobs);
        Arrays.sort(M2Jobs);
        int[] M1Add = new int[M1];
        int[] M2Add = new int[M2];
        int[] M1Time = new int[N];
        int[] M2Time = new int[N];
        for (int i = 0; i < N; i++) {
            int index = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < M1; j++) {
                if (M1Jobs[j]+M1Add[j]<min) {
                    min = M1Jobs[j]+M1Add[j];
                    index = j;
                }
            }
            M1Add[index]+=M1Jobs[index];
            M1Time[i] = M1Add[index];
        }
        for (int i = 0; i < N; i++) {
            int index = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < M2; j++) {
                if (M2Jobs[j]+M2Add[j]<min) {
                    min = M2Jobs[j]+M2Add[j];
                    index = j;
                }
            }
            M2Add[index]+=M2Jobs[index];
            M2Time[i] = M2Add[index];
        }
        int M1Max = 0;
        int M2Max = 0;
        for (int i = 0; i < N; i++) {
            M1Max=Math.max(M1Max, M1Time[i]);
            M2Max=Math.max(M2Max, M1Time[i]+M2Time[N-1-i]);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        out.println(M1Max+" "+M2Max);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}
