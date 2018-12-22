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
        int C = line1[2];

        br.close();


        List<int[]> boards = new ArrayList<int[]>();
        int[] temp = new int[] {0, cows.length-1};
        boards.add(temp);
        loop: for (int i = 0; i < M-1; i++) {
            int[] s = new int[] {0,0};
            int jj = 0;
            for (int j = 0; j < boards.size(); j++) {
                if (boards.size()>=M) break loop;
                int[] split = split(C, s[0], boards, cows, boards.get(j)[0], boards.get(j)[1], j);
                if(split[0] > s[0]) {
                    s=split;
                    jj=j;
                }
            }
            int[] board1 = new int[] {boards.get(jj)[0], s[1]};
            int[] board2 = new int[] {s[1]+1, boards.get(jj)[1]};
            boards.remove(jj);
            boards.add(jj, board2);
            boards.add(jj, board1);
        }
        for (int i = 0; i < boards.size(); i++) {
            for (int j = i + 1; j < boards.size(); j++) {
                if( (boards.get(i)[0] == boards.get(j)[0] && boards.get(i)[1] == boards.get(j)[1]) || boards.get(j)[1] < boards.get(j)[0]){
                    boards.remove(j);
                    j--;
                }
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        int sum=0;
        for (int k = 0; k < boards.size(); k++) {
            System.out.println(boards.get(k)[0]+"\t"+boards.get(k)[1]);
            for (int j = boards.get(k)[0]; j <= boards.get(k)[1]; j++) {
                sum+=cows[j]-cows[boards.get(k)[0]];
            }
        }
        out.println(sum-1);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static int[] split(int C, int max, List<int[]> boards, int[] nums, int start, int end, int other) {
        int maxi = 0;
        List<int[]> old = new ArrayList<int[]>();
        old.addAll(boards);
        for (int i = start; i < end; i++) {
            int sum=0;
            if (boards.get(other)[0]-i>C-1 || boards.get(other)[1]-i>C) continue;
            int[] board1 = new int[] {boards.get(other)[0],i};
            int[] board2 = new int[] {i+1, boards.get(other)[1]};
            boards.remove(other);
            boards.add(board1);
            boards.add(board2);
            for (int k = 0; k < boards.size(); k++) {
                //System.out.println(boards.get(k)[0]+"\t"+boards.get(k)[1]);
                for (int j = boards.get(k)[0]; j <= boards.get(k)[1]; j++) {
                    sum+=nums[j]-nums[boards.get(k)[0]];
                }
            }
            boards=new ArrayList<int[]>();
            boards.addAll(old);
            //int diff = nums[i+1]-nums[i];
            if (sum < max) {
                maxi = i;
                max = sum;
            }
        }
        System.out.println(maxi);
        int [] temp = new int[] {max, maxi};
        return temp;
    }
}