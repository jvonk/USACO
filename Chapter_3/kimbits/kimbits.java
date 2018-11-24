
/*
ID: 100021881
LANG: JAVA
PROG: kimbits
*/

import java.io.*;
import java.util.*;

public class kimbits {
    public static void main(String[] args) throws Exception {
        //measure beginning so you can find how long it took
        long startTime = System.currentTimeMillis();

        //input
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        //output
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
 
        //split line with spaces and assign to variables
        String[] l = (br.readLine()).split("\\s");
        int N = Integer.parseInt(l[0]); //number of bits in result
        int L = Integer.parseInt(l[1]); //maximum number of bits set to 1
        long I = Long.parseLong(l[2]);  //Ith number that works is the result 

        long num = 0; //counter of number of possible results found
        long i;       //number to try
        for (i = 0; i <= 1L<<N; i++) { //from 0 to N bits, 1L<<N shifts 1 N times to get 2^N
            int c = 0;  //number of matching digits
            long t = i; //a copy of i to count number of digits
            if (Long.bitCount(i)>L) {
                for(c=0; (t&1)==0 && t!=0; c++) // while t has only 0s but is not 0
                    t>>=1;
                i+=(1<<c)-1;
                continue;
            }
            if (++num == I)
                break;
            for (t=i, c=0; (t&1)==1; c++) //while t has only 1s, c++, so you count the number of cows
                t>>=1;
            if (I-num+1>1L<<c) {
                i+=(1L<<c)-1;
                num+=(1L<<c)-1;
            }
        }
        String result = Long.toBinaryString(i);
        while (result.length()<N) {
            result="0"+result;
        }
        out.println(result);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}