
/*
ID: 100021881
LANG: JAVA
PROG: sort3
*/

import java.io.*;
import java.util.*;


public class sort3 {
    public static void main(String[] args) throws Exception {
        // long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        int N = Integer.parseInt(br.readLine());
        List<Integer> input = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            input.add(Integer.parseInt(br.readLine()));
        }
        //int[][] orders = new int[][] { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };
        int[][] orders = new int[][] { { 0, 1, 2 }, { 2, 1, 0 } };
        int min = 2147483647;
        for (int i = 0; i < orders.length; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(input);
            List<List<Integer>> switches = sortArray(temp, orders[i], 0, 0, false);
            // out.println(switches);
            if (switches.get(1).get(0) < min) {
                min = switches.get(1).get(0);
            }
        }
        out.println(min);
        br.close();
        out.close();
        // System.out.println(System.currentTimeMillis() - startTime);
    }

    public static List<List<Integer>> sortArray(List<Integer> results, int[] order, int starti, int startj, boolean skip) {
        
        int swaps = 0;

        // System.out.println(results);
        boolean swapped=false;
        List<Integer> r = new ArrayList<Integer>();
        r.addAll(results);
        r.sort(Comparator.naturalOrder());
        int times=0;
        while (!r.equals(results) && times<100) {
            if (results.size() > 1) {
                for (int i = starti; i < results.size(); i++) {
                    for (int j = Math.max(startj, i+1); j < results.size(); j++) {
                        if (order[results.get(j) - 1] > order[results.get(i) - 1]) {
                            if (!(i==starti && j==startj)) {
                                List<List<Integer>> t = sortArray(results, order, i, j, true);
                                List<List<Integer>> f = sortArray(results, order, i, j, false);
                                if(t.get(1).get(0)>f.get(1).get(0)) {
                                    results=t.get(0);
                                    swaps+=t.get(1).get(0);
                                } else {
                                    results=f.get(0);
                                    swaps+=f.get(1).get(0);
                                }
                                swapped=true;
                            } else if (!skip) {
                                Collections.swap(results, j, i);
                                System.out.println(j+" "+i);
                                swaps++;
                                swapped=true;
                            }
                        }

                    }
                }
            }
            times++;
        }
        if (times==100) {
            swaps+=100000; 
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(results);
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(swaps);
        res.add(temp);
        return res;
    }
}
