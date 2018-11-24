
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
        ArrayList<Integer> changes = new ArrayList<Integer>();
        ArrayList<Integer> sum = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            hills.add(Integer.parseInt(br.readLine()));
            changes.add(0);
        }
        hills.sort(Comparator.naturalOrder());
        //out.println(hills);
        //out.println(changes);
        int ii = 0;
        while(findMinMax(hills, changes)>17 && ii<100000) {
            if (hills.get(N-1)+changes.get(N-1)-hills.get(0)-changes.get(0) >= findMinMax(hills, changes)) {
                if(changes.get(0)>-1*changes.get(N-1)) {
                    changes.set(N-1, changes.get(N-1)-1);
                } else {
                    changes.set(0, changes.get(0)+1);
                }
            }
            ArrayList<ArrayList<Integer>> temp = sortArray(hills, changes);
            hills=temp.get(0);
            changes=temp.get(1);
            ii++;
        }                
        //out.println(ii);
        //out.println(hills);
        out.println(changes);
        for (int i = 0; i < hills.size(); i++) {
            sum.add(hills.get(i)+changes.get(i));
        }
        out.println(sum);
        int total = 0;
        for (int change : changes) {
            total+=change*change;
        }
        out.println(total);
        out.close();
        br.close();
    }
    public static ArrayList<ArrayList<Integer>> sortArray(ArrayList<Integer> x, ArrayList<Integer> y) {
        boolean swapped = true;
        while (swapped) {
           swapped = false;
           for(int i=1; i<x.size(); i++) {
                if(x.get(i-1)+y.get(i-1) > x.get(i)+y.get(i)) {
                    Collections.swap(x, i-1, i);
                    Collections.swap(y, i-1, i);
                    swapped = true;
                }
            }
        }
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        temp.add(x);
        temp.add(y);
        return temp;
    }
    public static int findMinMax(ArrayList<Integer> x, ArrayList<Integer> y) {
        int max = 0;
        int min = x.get(0)+y.get(0);
        for (int i = 0; i < x.size(); i++) {
            if (max < x.get(i)+y.get(i)) {
                max = x.get(i)+y.get(i);
            }
            if (min > x.get(i)+y.get(i)) {
                min = x.get(i)+y.get(i);
            }
        }
        return max-min;
    }
    
}
