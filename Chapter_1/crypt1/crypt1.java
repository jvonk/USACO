
/*
ID: 100021881
LANG: JAVA
PROG: crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer line = new StringTokenizer(br.readLine());
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            digits.add(Integer.parseInt(line.nextToken()));
        }
        //Arrays.sort(digits);
        //out.println(digits);
        int[] a = new int[] {0, 0, 0};
        int[] b = new int[] {0, 0};
        int count = 0;
        for (int i = 0; i < digits.size(); i++) {
            a[0]=digits.get(i);
            for (int j = 0; j < digits.size(); j++) {
                a[1]=digits.get(j);
                for (int k = 0; k < digits.size(); k++) {
                    a[2]=digits.get(k);
                    for (int l = 0; l < digits.size(); l++) {
                        b[0]=digits.get(l);
                        for (int m = 0; m < digits.size(); m++) {
                            b[1]=digits.get(m);
                            //out.println();
                            //out.println();
                            //out.print("a: ");
                            for (int digit : a) {
                                //out.print(digit+"\t");
                            }
                            //out.println();
                            //out.print("b: \t\t");
                            for (int digit : b) {
                                //out.print(digit+"\t");
                            }
                            //out.println();
                            
                            int abc = 100*a[0]+10*a[1]+a[2];
                            int de = 10*b[0]+b[1];
                            //out.println(abc);
                            boolean works = true;
                            int multa = abc*b[1];
                            if (!(isIn(multa, digits)>0 && isIn(multa, digits)<4)) {
                                works=false;
                                //out.println(multa);
                            }
                            int multb = abc*b[0];
                            if (!(isIn(multb, digits)>0 && isIn(multb, digits)<4)) {
                                works=false;
                                //out.println(multb);
                            }
                            int sum = abc*de;
                            if (!(isIn(sum, digits)>0 && isIn(sum, digits)<5)) {
                                works=false;
                                //out.println(sum);
                            }
                            if (works) {
                                count++;
                                //out.println(abc+"\t"+de+"\t"+multa+"\t"+multb+"\t"+sum);
                            }
                        }
                    }   
                }
            }
        }
        out.println(count);
        out.close();
        br.close();
    }
    public static int isIn(int num, List<Integer> digits) {
        int n = String.valueOf(num).length();
        while (num > 0) {
            int temp = num%10;
            boolean in = false;
            for (int digit : digits) {
                if (temp==digit) in=true;
            }
            if(!in) {
                return 0;
            }
            num /= 10;
        }
        return n;
    }
}
