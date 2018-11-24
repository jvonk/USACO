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
import java.util.Deque;
import java.util.LinkedList;

public class inflate {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("inflate.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(l.nextToken()); // contest minutes
        int N = Integer.parseInt(l.nextToken()); // number of problem classes
        

        TreeMap<Double, Category> sorter = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            l = new StringTokenizer(br.readLine());
            Category temp = new Category(Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()));
            double j = 0;
            while (sorter.containsKey(temp.value+j)) {
                j+=0.00001;
            }
            sorter.put(temp.value+j, temp);
        }
        br.close();
        Category[] categories = new Category[N];
        List<Category> toArr = new ArrayList<>();
        System.out.println("points\tminutes\tvalue");
        toArr.addAll(sorter.values());
        for (int i = 0; i < N; i++) {
            categories[i]=toArr.get(N-i-1);
            System.out.println(categories[i].points+"\t"+categories[i].minutes+"\t"+categories[i].value);
        }


        int total = 0;
        int points = 0;
        System.out.println("\ni\tj");
        for (int i = 0; i < N; i++) {
            int j;
            for (j = 0; total+categories[i].minutes*(j+1)<=M; j++) {}
            System.out.println(i+"\t"+j);
            total+=j*categories[i].minutes;
            points+=j*categories[i].points;
        }
        


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        out.println(points);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    static class Category {
        int points;
        int minutes;
        double value;
        int start;
        public Category (int p, int m) {
            points=p;
            minutes=m;
            value=1.0*p/m;
        }
        public Category (int p, int m, int s) {
            points=p;
            minutes=m;
            start = s;
        }
    }
}