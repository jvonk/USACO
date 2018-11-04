/*
ID: 100021881
LANG: JAVA
PROG: inflate
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;

public class inflate {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("inflate.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(l.nextToken()); // contest minutes
        int N = Integer.parseInt(l.nextToken()); // number of problem classes
        

        TreeMap<Integer, Category> sorter = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            l = new StringTokenizer(br.readLine());
            sorter.put(i, new Category(Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())));
        }
        br.close();

        Category[] categories = new Category[N];
        
        Iterator<Category> it = sorter.values().iterator();
        int counter = 0;
        while(it.hasNext()) {
            categories[i]=it.next();
            i++;
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        out.println(categories.toString());
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    static class Category {
        int points;
        int minutes;
        double value;
        public Category (int p, int m) {
            points=p;
            minutes=m;
            value=p/m;
        }
    }
}