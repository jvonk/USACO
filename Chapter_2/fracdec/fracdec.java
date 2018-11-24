
/*
ID: 100021881
LANG: JAVA
PROG: fracdec
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class fracdec {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("fracdec.in"));

        // read first line with V and N
        StringTokenizer l = new StringTokenizer(br.readLine());

        // get V, the number of coins in the system
        int N = Integer.parseInt(l.nextToken());

        // get N, the amount fracdec to construct
        int D = Integer.parseInt(l.nextToken());

        br.close();



        int remainderN = N % D;
        int wholeN = N - remainderN;
        StringBuilder str = new StringBuilder();
        str.append(String.valueOf(wholeN / D));
        int beginning = str.length();
        System.out.println(str + "\t" + remainderN);
        str.append('.');
        for (int i = 0; i < 20000; i++) {
            wholeN = remainderN * 10;
            remainderN = wholeN % D;
            str.append((wholeN - remainderN) / D);
        }
        String s = str.toString();
        System.out.println(s.substring(0, 100));
        int i = -1;
        int j = -1;
        loop: for (i = beginning+1; i < s.length(); i++) {
            for (j = 1; j < s.length()-i; j++) {
                boolean works = (s.length()-i)/j-2>=2;
                if (!works) break;
                for (int k = i; k < s.length()-2*j; k+=j) {
                    //OLD s.substring(i+j*(k-1), i+j*k).equals(s.substring(i+j*k+1, i+j*(k+1)+1))
                    int temp = s.indexOf(s.substring(k, k+j), k+j+1);
                    if (temp!=k+j+1) {
                        //j=s.indexOf(s.substring(k, k+j), k+j+1)-2;
                        works = false;
                        System.out.println(j);
                        break;
                    }                           
                }
                if (works) {
                    break loop;
                }
            }
        }
        System.out.println(i+"\t"+beginning);
        String res = "";
        res+=s.substring(0, i);
        if (!(j==1&&s.charAt(i+1)=='0')) {
            res+="("+s.substring(i, i+j+1)+")";
        } else if (i==beginning+1) {
            res+="0";
        }
        System.out.println(res);


        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        out.println(res);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
