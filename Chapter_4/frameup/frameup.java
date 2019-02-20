/*
ID: 100021881
LANG: JAVA
PROG: frameup
*/

import java.io.*;
import java.util.*;

/**
 * frameup
 */
public class frameup {
    public static int N, M, numChars;
    public static List<List<Integer>> frames = new ArrayList<List<Integer>>();
    public static boolean[] chars = new boolean[26], visited = new boolean[26];
    public static int[] totalChar = new int[26];
    public static Set<String> answers = new TreeSet<String>();
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("frameup.in"));
        String[] first = br.readLine().split(" ");
        N = Integer.parseInt(first[0]);
        M = Integer.parseInt(first[1]);
        List<String> x = new ArrayList<String>();
        for(int i = 0; i < N; i++) {
            String p = br.readLine();
            x.add(p);
            for(int j = 0; j < M; j++) {
                if(p.charAt(j) != '.') {
                    chars[p.charAt(j) - 'A'] = true;
                }
            }
        }
        br.close();
        for(int i = 0; i < 26; i++) {
            frames.add(new ArrayList<Integer>());
            if(chars[i]) {
                numChars++;
            }
        }
        for(int i = 0; i < 26; i++) {
            char c = (char)('A' + i);
            if(!chars[i]) {
                continue;
            }
            int left = M, right = -1, bottom = N, top = -1;
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(x.get(j).charAt(k) == c) {
                        left = Math.min(left, k);
                        right = Math.max(right, k);
                        top = Math.max(top, j);
                        bottom = Math.min(bottom, j);
                    }
                }
            }
            for(int j = bottom; j <= top; j++) {
                if(x.get(j).charAt(left) != c) {
                    frames.get(x.get(j).charAt(left) - 'A').add(i);
                }
                if(x.get(j).charAt(right) != c) {
                    frames.get(x.get(j).charAt(right) - 'A').add(i);
                }
            }
            for(int j = left + 1; j < right; j++) {
                if(x.get(top).charAt(j) != c) {
                    frames.get(x.get(top).charAt(j) - 'A').add(i);
                }
                if(x.get(bottom).charAt(j) != c) {
                    frames.get(x.get(bottom).charAt(j) - 'A').add(i);
                }
            }
        }
        for(int i = 0; i < 26; i++) {
            if(chars[i]) {
                Collections.sort(frames.get(i));
                for(int j = 0; j < frames.get(i).size(); j++) {
                    totalChar[frames.get(i).get(j)]++;
                }
            }
        }
        dfs(0, "");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
        for(String answer : answers) {
            out.println(answer);
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static void dfs(int level, String ans) {
        if(level == numChars) {
            answers.add(new StringBuilder(ans).reverse().toString());
            return;
        }
        for(int i = 0; i < 26; i++) {
            if(chars[i] && totalChar[i] == 0 && !visited[i]) {
                visited[i] = true;
                int[] old = totalChar.clone();
                for(int j = 0; j < frames.get(i).size(); j++) {
                    totalChar[frames.get(i).get(j)]--;
                }
                dfs(level + 1, ans + (char)(i + 'A'));
                totalChar = old;
                visited[i] = false;
            }
        }
    }
}
