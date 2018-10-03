
/*
ID: 100021881
LANG: JAVA
PROG: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        int num = Integer.parseInt(br.readLine());
        List<Integer> starts = new ArrayList<Integer>();
        List<Integer> ends = new ArrayList<Integer>();
        String line = null;
        while ((line = br.readLine()) != null) {
            StringTokenizer l = new StringTokenizer(line);
            int start = Integer.parseInt(l.nextToken());
            int end = Integer.parseInt(l.nextToken());
            int ii = 0;
            for (ii = 0; ii < starts.size(); ii++) {
                if (starts.get(ii) > start) {
                    break;
                }
            }
            starts.add(ii, start);
            ends.add(ii, end);
        }
        for (int i = 0; i <= num+1; i++) {
            loop: for (int first = 0; first < starts.size(); first++) {
                for (int second = 0; second < starts.size(); second++) {
                    if (first != second) {
                        
                        if (starts.get(first) > starts.get(second)) {
                            if (starts.get(first) <= ends.get(second)) {
                                if (ends.get(first) >= ends.get(second)) {
                                    ends.set(second, ends.get(first));
                                }
                                starts.remove(first);
                                ends.remove(first);
                                break loop;
                            }
                        } else {
                            if (ends.get(first) >= starts.get(second)) {
                                if (ends.get(first) <= ends.get(second)) {
                                    ends.set(first, ends.get(second));
                                }
                                starts.remove(second);
                                ends.remove(second);
                                break loop;
                            }
                        }
                    }
                }
            }
        }
        int maxLength = 0;
        int maxNone = 0;
        for (int ii = 0; ii < starts.size(); ii++) {
            int length = ends.get(ii) - starts.get(ii);
            int none = 0;
            if (ii + 1 < starts.size()) {
                none = starts.get(ii + 1) - ends.get(ii);
            }
            maxLength = Math.max(maxLength, length);
            maxNone = Math.max(maxNone, none);
        }
        out.println(maxLength + " " + maxNone);
        out.close();
        br.close();
    }
}