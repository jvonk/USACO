
/*
ID: 100021881
LANG: JAVA
PROG: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        int N = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());
        StringTokenizer line3 = new StringTokenizer(br.readLine());
        StringTokenizer line4 = new StringTokenizer(br.readLine());
        List<Integer> on = new ArrayList<>();
        List<Integer> off = new ArrayList<>();
        while (line3.hasMoreTokens()) {
            int token = Integer.parseInt(line3.nextToken());
            if (token == -1)
                break;
            on.add(token);
        }
        while (line4.hasMoreTokens()) {
            int token = Integer.parseInt(line4.nextToken());
            if (token == -1)
                break;
            off.add(token);
        }

        /*
         * Button 1: When this button is pressed, all the lamps change their state.
         * Button 2: Changes the state of all the odd numbered lamps. Button 3: Changes
         * the state of all the even numbered lamps. Button 4: Changes the state of the
         * lamps whose number is of the form 3xK+1. 6-digit C%6!= buttons other buttons
         * 111111 1 0 123 000000 5 1 23 010101 5 2 13 101010 5 3 12 011011 2 4 123
         * 100100 1 14 234 110001 1 24 134 001110 1 34 124
         */
        TreeSet<String> works = new TreeSet<>();
        String[] solutions = new String[] { "111111", "000000", "010101", "101010", "011011", "100100", "110001",
                "001110" };
        if (C % 6 == 0) {
            works.add(solutions[0]);
            if (C > 0) {
                works.add(solutions[1]);
                works.add(solutions[2]);
                works.add(solutions[3]);
                works.add(solutions[4]);
                works.add(solutions[5]);
                works.add(solutions[6]);
                works.add(solutions[7]);
            }
        } else if (C % 6 == 1) {
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[4]);
        } else if (C % 6 == 2) {
            works.add(solutions[0]);
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        } else if (C % 6 == 5) {
            works.add(solutions[0]);
            works.add(solutions[4]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        } else {
            works.add(solutions[0]);
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[4]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        }
        List<String> results = new ArrayList<>();
        results.addAll(works);
        boolean isResult = false;
        for (int i = 0; i < results.size(); i++) {
            char[] temp = results.get(i).toCharArray();
            boolean good = true;
            System.out.println(temp);
            for (int test : on) {
                if (temp[(test - 1) % 6] != '1') {
                    good = false;
                    break;
                }
            }
            for (int test : off) {
                if (temp[(test - 1) % 6] != '0') {
                    good = false;
                    break;
                }
            }
            if (good) {
                isResult = true;
                for (int j = 0; j < N; j++) {
                    out.print(temp[j % 6]);
                }
                out.println();
            }
        }
        if (!isResult) {
            out.println("IMPOSSIBLE");
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
