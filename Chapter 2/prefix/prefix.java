
/*
ID: 100021881
LANG: JAVA
PROG: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        List<String> primitives = new ArrayList<>();
        String line = "";
        int maxPrim = 0;
        while ((line = br.readLine()) != null) {
            if (line.equals("."))
                break;
            StringTokenizer l = new StringTokenizer(line);
            while (l.hasMoreTokens()) {
                String temp = l.nextToken();
                if (solve(temp, primitives)<temp.length()) {
                    primitives.add(temp);
                }
            }
        }
        /*
         * boolean changed = true; while (changed) { changed = false; for (int i = 0; i
         * < primitives.size(); i++) { for (int j = 0; j < primitives.size(); j++) { if
         * (i != j && primitives.get(i).length()==1) { String first = primitives.get(i);
         * String original = primitives.get(j); String second = original; while
         * (first.length() < second.length() && second.length() > 1) { // remove first
         * from the beginning of second if (!same(first, second)) break; StringBuilder s
         * = new StringBuilder(second); second = s.substring(first.length(),
         * second.length()); } if (!second.equals(original)) { changed = true;
         * primitives.set(j, second); //System.out.println(primitives); } first =
         * primitives.get(i); original = primitives.get(j); second = original; while
         * (first.length() < second.length() && second.length() > 1) { // remove first
         * from the beginning of second if (!sameReverse(first, second)) break;
         * StringBuilder s = new StringBuilder(second); second = s.substring(0,
         * second.length() - first.length()); } if (!second.equals(original)) { changed
         * = true; primitives.set(j, second); //System.out.println(primitives); } } } }
         * }
         */
        System.out.println(primitives);
        System.out.println(System.currentTimeMillis() - startTime);
        StringBuilder S = new StringBuilder();
        while ((line = br.readLine()) != null) {
            S.append(line);
        }
        System.out.println(System.currentTimeMillis() - startTime);
        out.println(solve(S.toString(), primitives));
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }

    public static int solve(String S, List<String> primitives) {
        int len = S.toCharArray().length;
        int maxLength = 0;
        boolean[] available = new boolean[200010];
        available[0]=true;
        for (int i = 0; i < len; i++) 
            if (available[i])
                for (String primitive : primitives) 
                        if (i+primitive.length()<=len && S.substring(i, i+primitive.length()).equals(primitive)) {
                            maxLength = Math.max(maxLength, i+primitive.length());
                            available[i+primitive.length()]=true;
                        }
        return maxLength;
    }
}
