/*
ID: 100021881
LANG: JAVA
PROG: lgame
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/**
 * lgame
 */
public class lgame {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader in = new BufferedReader(new FileReader("lgame.in"));
        String text = in.readLine();
        in.close();
        BufferedReader br = new BufferedReader(new FileReader("lgame.dict"));
        List<String> dict = new ArrayList<String>();
        List<String> possible = new ArrayList<String>();
        String line = "";
        int[] values = new int[] {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};
        while(!(line = br.readLine()).equals(".")) {
            if(checkWord(text, line, "")) {
                dict.add(line);
            }
        }
        br.close();
        int max = 0;
        for(String s1 : dict) {
            int t1 = 0;
            for(char c : s1.toCharArray()) {
                t1 += values[c - 'a'];
            }
            if(t1 > max) {
                possible.clear();
            }
            if(t1 >= max) {
                possible.add(s1);
                max = t1;
            }
            for(String s2 : dict) {
                int compare = s1.compareTo(s2);
                if(compare >= 0) {
                    continue;
                }
                if(checkWord(text, s1, s2)) {
                    int t2 = t1;
                    for(char c : s2.toCharArray()) {
                        t2 += values[c - 'a'];
                    }
                    if(t2 > max) {
                        possible.clear();
                    }
                    if(t2 >= max) {
                        possible.add(s1 + " " + s2);
                        max = Math.max(max,t2);
                    }
                }
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));
        out.println(max);
        for (String s : possible) {
            out.println(s);
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static boolean checkWord(String first, String second, String third) {
        int[] letters = new int[26];
        for(char c : first.toCharArray()) {
            letters[c - 'a']++;
        }
        for(char c : second.toCharArray()) {
            if(--letters[c - 'a'] < 0) {
                return false;
            }
        }
        for(char c : third.toCharArray()) {
            if(--letters[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
