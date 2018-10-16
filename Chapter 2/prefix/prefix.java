
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
            while (l.hasMoreTokens())
                primitives.add(l.nextToken());
        }
/*        
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < primitives.size(); i++) {
                for (int j = 0; j < primitives.size(); j++) {
                    if (i != j && primitives.get(i).length()==1) {
                        String first = primitives.get(i);
                        String original = primitives.get(j);
                        String second = original;
                        while (first.length() < second.length() && second.length() > 1) {
                            // remove first from the beginning of second
                            if (!same(first, second))
                                break;
                            StringBuilder s = new StringBuilder(second);
                            second = s.substring(first.length(), second.length());
                        }
                        if (!second.equals(original)) {
                            changed = true;
                            primitives.set(j, second);
                            //System.out.println(primitives);
                        }
                        first = primitives.get(i);
                        original = primitives.get(j);
                        second = original;
                        while (first.length() < second.length() && second.length() > 1) {
                            // remove first from the beginning of second
                            if (!sameReverse(first, second))
                                break;
                            StringBuilder s = new StringBuilder(second);
                            second = s.substring(0, second.length() - first.length());
                        }
                        if (!second.equals(original)) {
                            changed = true;
                            primitives.set(j, second);
                            //System.out.println(primitives);
                        }
                    }
                }
            }
        }
        */
        HashSet<String> removeDuplicates = new HashSet<>();
        removeDuplicates.addAll(primitives);
        primitives = new ArrayList<String>();
        primitives.addAll(removeDuplicates);
        System.out.println(primitives);
        int maxL = 0;
        System.out.println(System.currentTimeMillis() - startTime);
        for (String primitive : primitives)
            if (primitive.length() > maxL)
                maxL = primitive.length();

        String S = "";
        int total = 0;
        boolean over = false;
        while ((line = br.readLine()) != null) {
            S += line;
        }
        
            int maxLength = 0;
            Deque<String> deque = new LinkedList<String>();
            deque.add("");
            while (!deque.isEmpty()) {
                String partial = deque.remove();
                for (String primitive : primitives) {
                    String temp = partial+primitive;
                        if (sameRange(temp, S, partial.length(), temp.length()))
                    if (temp.length() > maxLength) {
                        maxLength = temp.length();
                        if (maxLength % 100 == 0)
                            System.out.println(maxLength);
                        deque.add(temp);
                        break;
                    }
                }
            }
            out.println(maxLength);
        
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }

    public static boolean same(String a, String b) {
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i % b.length()))
                return false;
        return true;
    }

    public static boolean sameReverse(String a, String b) {
        for (int i = a.length() - 1; i >= 0; i--)
            if (a.charAt(i) != b.charAt(b.length() - a.length() + i))
                return false;
        return true;
    }

    public static boolean sameRange(String a, String b, int start, int end) {
        for (int i = start; i < end; i++)
            if (a.charAt(i) != b.charAt(i % b.length()))
                return false;
        return true;
    }
}
