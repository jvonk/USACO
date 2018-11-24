
/*
ID: 100021881
LANG: JAVA
PROG: barn1
*/

import java.io.*;
import java.util.*;

public class barn1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int numBoards = Integer.parseInt(line.nextToken());
        int numStalls = Integer.parseInt(line.nextToken());
        int numCows = Integer.parseInt(line.nextToken());
        int[] nums = new int[numCows];
        List<int[]> boards = new ArrayList<int[]>();
        for (int i = 0; i < numCows; i++) {
            nums[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);
        int[] temp = new int[] {0, nums.length-1};
        boards.add(temp);
        for (int i = 0; i < numBoards-1; i++) {
            int[] s = new int[] {0,0};
            int jj = 0;
            for (int j = 0; j < boards.size(); j++) {
                int[] split = split(nums, boards.get(j)[0], boards.get(j)[1]);
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
        for(int i = 0; i < boards.size(); i++) {
            for(int j = i + 1; j < boards.size(); j++) {
                if( (boards.get(i)[0] == boards.get(j)[0] && boards.get(i)[1] == boards.get(j)[1]) || boards.get(j)[1] < boards.get(j)[0]){
                    boards.remove(j);
                    j--;
                }
            }
        }
        int diff = 0;
        for (int i = 0; i < boards.size(); i++) {
            diff += nums[boards.get(i)[1]]-nums[boards.get(i)[0]]+1;
        }
        out.println(diff);
        out.close();
        br.close();
    }
    public static int[] split(int[] nums, int start, int end) {
        int max = 0;
        int maxi = 0;
        for (int i = start; i < end; i++) {
            int diff = nums[i+1]-nums[i];
            if (diff > max) {
                maxi = i;
                max = diff;
            }
        }
        int [] temp = new int[] {max, maxi};
        return temp;
    }
}
