
/*
ID: 100021881
LANG: JAVA
PROG: ariprog
*/

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList; 
import java.util.*;

public class ariprog {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        int N = Integer.parseInt(br.readLine()); // length of arithmetic progressions to search. All arethmetic
                                                 // progressions must be of this length
        int M = Integer.parseInt(br.readLine()); // upper bound for bisquares (p^2+q^2) with p and q being less than M
        ArrayList<Integer> bisquares = new ArrayList<Integer>();
        for (int p = 0; p <= M; p++) {
            for (int q = 0; q <= M; q++) {
                bisquares.add(p * p + q * q);
            }
        }
        TreeSet<Integer> hs = new TreeSet<>();
        hs.addAll(bisquares);
        bisquares.clear();
        bisquares.addAll(hs);
        // output of the form "a b" with a being the start of the arithmetic progression
        // and b being the distance between each one
        ArrayList<ArrayList<Integer>> result = solve(bisquares, N);
        if (result.get(0).size() > 0) {
            for (int i = 0; i < result.get(0).size(); i++) {
                out.println(result.get(1).get(i) + " " + result.get(0).get(i));
            }
        } else {
            out.println("NONE");
        }
        out.close();
        br.close();
    }

    public static ArrayList<ArrayList<Integer>> sortArray(ArrayList<Integer> a, ArrayList<Integer> b, boolean first) {
        ArrayList<Integer> x;
        ArrayList<Integer> y;
        if (first) {
            x=a;
            y=b;
        } else {
            x=b;
            y=a;
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < x.size(); i++) {
                if (x.get(i - 1) > x.get(i)) {
                    Collections.swap(x, i - 1, i);
                    Collections.swap(y, i - 1, i);
                    swapped = true;
                } else if (x.get(i - 1) == x.get(i) && y.get(i - 1) > y.get(i)) {
                    Collections.swap(x, i - 1, i);
                    Collections.swap(y, i - 1, i);
                    swapped = true;
                }
            }
        }
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        if (first) {
            temp.add(x);
            temp.add(y);    
        } else {
            temp.add(y);
            temp.add(x);   
        }
        return temp;
    }

    public static ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> bisquares, int N) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        ArrayList<Integer> listb = new ArrayList<Integer>();
        HashSet<Integer> js = new HashSet<Integer>();
        for (int j = 1; j < 600; j++) {
            if (!js.contains(j)) {
                boolean everworks = false;
                for (int i = 0; i < bisquares.size(); i++) {
                    boolean works = true;
                    int w = 0;
                    for (int k = 0; k < N; k++) {
                        if (!bisquares.contains(bisquares.get(i) + k * j)) {
                            works = false;
                            break;
                        } else {
                            w=k*j;
                            everworks=true;
                        }
                    }
                    if (works) {
                        lista.add(bisquares.get(i));
                        listb.add(j);
                    }
                }
                js.add(j);
                if(!everworks && j>1) {
                    HashSet<Integer> temp = new HashSet<Integer>();
                    for (int jl : js) {
                        if(!js.contains(jl*j) && jl*j < 1000 && j > 1 ) {
                            temp.add(jl*j);
                        }
                    }
                    System.out.println(j+"\t"+temp);
                    js.addAll(temp);
                }
            }
        }
        System.out.println(js);
        return sortArray(listb, lista, false);
    }

}
