
/*
ID: 100021881
LANG: JAVA
PROG: namenum
*/

import java.io.*;
import java.util.*;

public class namenum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader dic = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        long tag = Long.parseLong(br.readLine());
        String number = String.valueOf(tag);
        List<String> touchtone = new ArrayList<String>();
        touchtone.add("ABC");
        touchtone.add("DEF");
        touchtone.add("GHI");
        touchtone.add("JKL");
        touchtone.add("MNO");
        touchtone.add("PRS");
        touchtone.add("TUV");
        touchtone.add("WXY");

        List<Integer> digits = new ArrayList<Integer>();
        List<String> names = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < number.length(); i++) {
            digits.add(Integer.parseInt(String.valueOf(number.charAt(i))));
        }
        for (int i : digits) {
            temp.add(touchtone.get(i - 2));
        }
        String line;
        while ((line = dic.readLine()) != null) {
            boolean same = true;
            if (temp.size() == line.length()) {
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).indexOf(line.charAt(i)) == -1) {
                        same = false;
                        break;
                    }
                }
                if (same)
                    names.add(line);
            }
        }
        if (names.size() > 0) {
            for (String name : names) {
                out.println(name);
            }
        } else {
            out.println("NONE");
        }
        dic.close();
        out.close();
        br.close();
    }
}